package com.client.service.impl;

import com.client.service.ClockingService;
import com.cms.CheckConstant;
import com.cms.dao.*;
import com.cms.domain.*;
import com.cms.dto.ClockingDTO;
import com.cms.dto.CourseClockingDTO;
import com.cms.dto.StudentClockingDtlDTO;
import com.cms.mapper.ClockingMapper;
import com.cms.service.impl.BaseServiceImpl;
import com.cms.util.BeanUtil;
import com.cms.vo.BasePageParameter;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.exception.MyException;
import com.core.page.Pagination;
import com.core.util.CalcUtil;
import com.core.util.enums.ConstantEnum;
import com.core.util.enums.StateEnum;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/25 10:52
 */
@Service
public class ClockingServiceImpl extends BaseServiceImpl<ClockingInfo, ClockingDTO, ClockingMapper, ClockingDao> implements ClockingService {

    @Resource
    ClockingDtlDao clockingDtlDao;

    @Resource
    CourseDtlDao courseDtlDao;

    @Resource
    CourseCodeDao courseCodeDao;

    @Resource
    StudentDao studentDao;

    @Override
    public void startClocking(String teacherAccount, Long courseDtlId, Double longitude, Double latitude) throws MyException {
        CourseDtlInfo info = courseDtlDao.findOne(teacherAccount, courseDtlId);
        if (null == info)
            throw new MyException(ConstantEnum.OPERATE_NOT_MATCHING);
        CourseCodeInfo courseCodeInfo = courseCodeDao.findOne(info.getCourseCode(), info.getTeacherAccount());
        if (null == courseCodeInfo)
            throw new MyException(ConstantEnum.TARGET_NULL);
        ClockingInfo c = dao.findOneByTeacherAndCoursesDtl(teacherAccount, courseDtlId);
        if (null != c){
            c.setSubmitTime(new Date());
            c.setLongitude(longitude);
            c.setLatitude(latitude);
            dao.update(c);

            ClockingDtlInfo repeat = new ClockingDtlInfo();
            repeat.setState(StateEnum.STUDENT_SUBMIT_DEFAULT.getCode());
            repeat.setClockingId(c.getId());
            clockingDtlDao.updateRepeat(repeat);
            return;
            //throw new MyException(ConstantEnum.CLOCKING_REPEAT);
        }

        String[] classIds = courseCodeInfo.getClassIds().split(",");
        List<StudentInfo> listDefault = new ArrayList<>();
        for (String s : classIds){
            if (StringUtils.isNotBlank(s)) {
                List<StudentInfo> temp = studentDao.findByClassId(Long.valueOf(s));
                if (CollectionUtils.isNotEmpty(temp))
                    listDefault.addAll(temp);
            }
        }
        if (CollectionUtils.isEmpty(listDefault))
            throw new MyException(ConstantEnum.CLOCKING_LACK_STUDENT);

        // TODO 加入时间检测 考勤时间应在 上课时间
        ClockingInfo clockingInfo = new ClockingInfo();
        clockingInfo.setTeacherAccount(teacherAccount);
        clockingInfo.setCourseDtlId(courseDtlId);
        clockingInfo.setCourseCode(courseCodeInfo.getCourseCode());
        clockingInfo.setSubmitTime(new Date());
        clockingInfo.setLongitude(longitude);
        clockingInfo.setLatitude(latitude);
        dao.insert(clockingInfo);

        String[] exports = courseCodeInfo.getExports().split(",");
        String[] contains = courseCodeInfo.getContains().split(",");

        Iterator<StudentInfo> it = listDefault.iterator();
        while(it.hasNext()){
            StudentInfo ele = it.next();
            for (String studentAccount : exports){
                if (ele.getAccount().equals(studentAccount))
                    it.remove();
            }
        }

        for (String s : contains) {
            if (StringUtils.isNotBlank(s)) {
                StudentInfo stu = new StudentInfo();
                stu.setAccount(s);
                listDefault.add(stu);
            }
        }

        List<ClockingDtlInfo> clockingDtlInfos = new ArrayList<>();
        for (StudentInfo stu : listDefault){
            ClockingDtlInfo clocking = new ClockingDtlInfo();
            clocking.setClockingId(clockingInfo.getId());
            clocking.setCourseDtlId(courseDtlId);
            clocking.setCourseCode(courseCodeInfo.getCourseCode());
            clocking.setStudentAccount(stu.getAccount());
            clocking.setState(StateEnum.STUDENT_SUBMIT_DEFAULT.getCode());
            clockingDtlInfos.add(clocking);
        }

        // 查询学生集合 组合 clockingDtl 链表
        clockingDtlDao.insertBatch(clockingDtlInfos);
    }

    @Override
    public boolean responseClocking(String studentAccount, Long courseDtlId, Double longitude, Double latitude, String mac) throws MyException {

        Long currTime = System.currentTimeMillis();
        ClockingDtlInfo clockingDtlInfo = clockingDtlDao.findOne(courseDtlId, studentAccount);
        if (null == clockingDtlInfo)
            throw new MyException(ConstantEnum.CLOCKING_DTL_NULL);

        List<ClockingDtlInfo> infos = clockingDtlDao.getListByCourseDtlId(courseDtlId);

        for (ClockingDtlInfo c : infos){
            if (mac.equals(c.getMac()))
                throw new MyException(ConstantEnum.CLOCKING_MAC_MULTIPLE);
        }

        ClockingInfo clockingInfo = dao.findOne(clockingDtlInfo.getClockingId());
        double distance = CalcUtil.getDistance(clockingInfo.getLongitude(), clockingInfo.getLatitude(), longitude, latitude);

        if (distance > CheckConstant.STANDARD_DISTANCE)
            throw new MyException(ConstantEnum.CLOCKING_DISTANCE_FAR);

        Long timeGap = CalcUtil.getTimeGap(currTime, clockingInfo.getSubmitTime().getTime());

        clockingDtlInfo.setSubmitTime(new Date(currTime));
        clockingDtlInfo.setMac(mac);
        clockingDtlInfo.setLongitude(longitude);
        clockingDtlInfo.setLatitude(latitude);

        if (timeGap < CheckConstant.STANDARD_NORMAL_TIME_GAP){//  正常 提交
            clockingDtlInfo.setState(StateEnum.STUDENT_SUBMIT_NORMAL.getCode());
            clockingDtlDao.update(clockingDtlInfo);
            return true;
        }else if(timeGap < CheckConstant.STANDARD_LATE_TIME_GAP){// 迟到 提交
            clockingDtlInfo.setState(StateEnum.STUDENT_SUBMIT_LATE.getCode());
            clockingDtlDao.update(clockingDtlInfo);
            return false;
        }else // 超过时间限制 不允许提交
            throw new MyException(ConstantEnum.CLOCKING_TIME_FAR);
    }

    @Override
    public List<CourseDtlInfo> getStudentClockingIng(String studentAccount) throws MyException {

        Long currTime = System.currentTimeMillis();

        List<Long> clockingDtlIdList = clockingDtlDao.getClockingDtlIngListByStudentAccount(studentAccount, currTime - CheckConstant.STANDARD_LATE_TIME_GAP);

        if (CollectionUtils.isEmpty(clockingDtlIdList))
            return Lists.newArrayList();

        List<CourseDtlInfo> targets = courseDtlDao.getListByCourseDtlId(clockingDtlIdList);

        for (CourseDtlInfo info : targets){
            info.setState(clockingDtlDao.findOne(info.getId(), studentAccount).getState());
        }

        return targets;
    }

    /**
     * 老师修改某节课中某个学生的签到状态
     * @param teacherAccount
     * @param courseDtlId
     * @param studentAccount
     * @param state
     * @throws MyException
     */
    @Override
    public void modifyStudentRespState(String teacherAccount, Long courseDtlId, String studentAccount, Integer state) throws MyException {
        ClockingDtlInfo clockingDtlInfo = clockingDtlDao.findOne(courseDtlId, studentAccount);
        if (clockingDtlInfo == null)
            throw new MyException(ConstantEnum.TARGET_NULL);
        ClockingInfo clockingInfo = dao.findOne(clockingDtlInfo.getClockingId(), teacherAccount);
        if (clockingInfo == null)
            throw new MyException(ConstantEnum.OPERATE_NOT_MATCHING);

        if (clockingDtlInfo.getState().equals(state))
            throw new MyException(ConstantEnum.CLOCKING_MODIFY_STATE_SAME);
        clockingDtlInfo.setState(state);
        ClockingDtlInfo c = BeanUtil.copyProperties(clockingDtlInfo, ClockingDtlInfo.class);
        clockingDtlDao.update(c);
    }

    /**
     * 学生获取某节课的签到结果
     * @param studentAccount
     * @param courseDtlId
     * @return
     * @throws MyException
     */
    @Override
    public Integer getClockingState(String studentAccount, Long courseDtlId) throws MyException {
        ClockingDtlInfo clockingDtlInfo =  clockingDtlDao.findOne(courseDtlId, studentAccount);
        return null == clockingDtlInfo ? null : clockingDtlInfo.getState();
    }

    /**
     * 学生获取某门课程的签到结果
     * @param studentAccount
     * @param courseCode
     * @return
     * @throws MyException
     */
    @Override
    public List<CourseClockingDTO> getCourseClockingDtl(String studentAccount, Long courseCode) throws MyException {

        StudentInfo student = studentDao.findOneByAccount(studentAccount);
        if (null == student)
            throw new MyException(ConstantEnum.STUDENT_NULL);

        CourseCodeInfo code = courseCodeDao.findOne(courseCode);
        if (null == code)
            throw new MyException(ConstantEnum.COURSE_NULL);
        String[] classIds = code.getClassIds().split(",");
        boolean flag = false;
        for (String s : classIds){
            if (StringUtils.isNotBlank(s) && String.valueOf(student.getClassId()).equals(s)) {
                flag = true;
                break;
            }
        }
        if (code.getExports().contains(studentAccount))
            flag = false;
        if (code.getContains().contains(studentAccount))
            flag = true;
        if (!flag)
            throw new MyException(ConstantEnum.STUDENT_NOT_IN_COURSE);

        List<CourseDtlInfo> courseSource = courseDtlDao.getListByCourseCode(courseCode);
        List<CourseClockingDTO> target =  BeanUtil.copyBeans(courseSource, CourseClockingDTO.class);

        List<ClockingDtlInfo> clockingSource = clockingDtlDao.getListByStudentAccAndCourseCode(studentAccount, courseCode);
        List<Long> courseDtlIds = Lists.newArrayList();
        for (ClockingDtlInfo c : clockingSource){
            courseDtlIds.add(c.getCourseDtlId());
        }

        for (CourseClockingDTO dto : target){
            if (courseDtlIds.contains(dto.getId()))
                dto.setClockingState(clockingSource.get(courseDtlIds.indexOf(dto.getId())).getState());
            else
                dto.setClockingState(StateEnum.COURSE_CLOCKING_STATE_NOTHING.getCode());
        }

        return target;
    }

    /**
     * 老师获取某节课的考勤结果
     * @param teacherAccount
     * @param courseDtlId
     * @return
     * @throws MyException
     */
    @Override
    public List<StudentClockingDtlDTO> getClockingDtlList(String teacherAccount, Long courseDtlId) throws MyException {
        ClockingInfo clocking = dao.findOneByTeacherAndCoursesDtl(teacherAccount, courseDtlId);
        if (null == clocking)
            throw new MyException(ConstantEnum.CLOCKING_QUERY);
        List<StudentClockingDtlDTO> source = clockingDtlDao.getListByClockingId(clocking.getId());
        return source;
    }

    /**
     * 老师获取某门课程的考勤结果
     * @param teacherAccount
     * @param courseCode
     * @return
     * @throws MyException
     */
    @Override
    public List<ClockingDTO> getCourseCodeClockingDtlList(String teacherAccount, Long courseCode) throws MyException {
        ClockingInfo clocking = dao.findOneByTeacherAndCourseCode(teacherAccount, courseCode);
        if (null == clocking)
            throw new MyException(ConstantEnum.CLOCKING_QUERY);

        List<ClockingDTO> source = dao.getCourseCodeClocking(courseCode);

        for (ClockingDTO c : source) {
            Integer countNormal = clockingDtlDao.countByClockingIdAndState(c.getId(), StateEnum.STUDENT_SUBMIT_NORMAL.getCode());
            Integer countLate = clockingDtlDao.countByClockingIdAndState(c.getId(), StateEnum.STUDENT_SUBMIT_LATE.getCode());
            Integer countDefault = clockingDtlDao.countByClockingIdAndState(c.getId(), StateEnum.STUDENT_SUBMIT_DEFAULT.getCode());

            c.setStateNormalCount(countNormal);
            c.setStateLateCount(countLate);
            c.setStateDefaultCount(countDefault);
        }
        return source;
    }

    @Override
    public Pagination<ClockingDTO> pageClockingByTeacher(String teacherAccount, BasePageParameter basePageParameter) {
        FilterMap map=new FilterMap();
        if(StringUtils.isNotBlank(teacherAccount)){
            map.eq("t_clocking.teacher_account", teacherAccount);
        }
        Long currTime = System.currentTimeMillis();
        //map.le("t_clocking.submit_time", new Date(System.currentTimeMillis() - CheckConstant.STANDARD_LATE_TIME_GAP));
        OrderMap orderMap=new OrderMap();
        orderMap.desc("id");
        Pagination<ClockingDTO> page  =dao.pageClockingByTeacher(map, orderMap,
                basePageParameter.getPageNum(),
                basePageParameter.getPageSize());

        for (ClockingDTO c : page.getItems()) {
            Integer countNormal = clockingDtlDao.countByClockingIdAndState(c.getId(), StateEnum.STUDENT_SUBMIT_NORMAL.getCode());
            Integer countLate = clockingDtlDao.countByClockingIdAndState(c.getId(), StateEnum.STUDENT_SUBMIT_LATE.getCode());
            Integer countDefault = clockingDtlDao.countByClockingIdAndState(c.getId(), StateEnum.STUDENT_SUBMIT_DEFAULT.getCode());

            c.setStateNormalCount(countNormal);
            c.setStateLateCount(countLate);
            c.setStateDefaultCount(countDefault);

            c.setIng(c.getSubmitTime().getTime() > currTime - CheckConstant.STANDARD_LATE_TIME_GAP);
        }

        return page;
    }

    @Override
    public Pagination<StudentClockingDtlDTO> pageClockingByStudent(String studentAccount, BasePageParameter basePageParameter) {

        FilterMap map=new FilterMap();
        if(StringUtils.isNotBlank(studentAccount)){
            map.eq("t_clocking_dtl.student_account", studentAccount);
        }
        map.le("t_clocking.submit_time", new Date(System.currentTimeMillis() - CheckConstant.STANDARD_LATE_TIME_GAP));
        OrderMap orderMap=new OrderMap();
        orderMap.desc("t_clocking_dtl.id");
        Pagination<StudentClockingDtlDTO> page = clockingDtlDao.pageClockingByStudent(map, orderMap,
                basePageParameter.getPageNum(),
                basePageParameter.getPageSize());

        return page;
    }

    @Override
    public Pagination<ClockingDTO> pageListVO(ClockingDTO dto, BasePageParameter basePageParameter) {
        FilterMap map=new FilterMap();
        if(null != dto.getCourseCode()){
            map.like("t_clocking.course_code","%"+dto.getCourseCode()+"%");
        }
        if(StringUtils.isNotBlank(dto.getTeacherAccount())){
            map.like("t_clocking.teacher_account","%"+dto.getTeacherAccount()+"%");
        }
        OrderMap orderMap=new OrderMap();
        orderMap.desc("id");
        Pagination<ClockingDTO> page  =dao.pageVO(map, orderMap,
                basePageParameter.getPageNum(),
                basePageParameter.getPageSize());
        return page;
    }
}
