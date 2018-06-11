package com.cms.service.impl;

import com.cms.dao.*;
import com.cms.domain.CourseCodeInfo;
import com.cms.domain.CourseDtlInfo;
import com.cms.domain.StudentInfo;
import com.cms.domain.TeacherInfo;
import com.cms.mapper.CourseDtlMapper;
import com.cms.service.CourseDtlService;
import com.cms.vo.BasePageParameter;
import com.cms.vo.CourseDtlVO;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.dao.query.support.DefaultDynamicQuery;
import com.core.exception.MyException;
import com.core.page.Pagination;
import com.core.util.enums.ConstantEnum;
import com.core.util.enums.StateEnum;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/8 20:27
 */
@Service
public class CourseDtlServiceImpl extends BaseServiceImpl<CourseDtlInfo, CourseDtlVO, CourseDtlMapper, CourseDtlDao> implements CourseDtlService {

    @Resource
    CourseDao courseDao;
    @Resource
    CourseCodeDao courseCodeDao;
    @Resource
    StudentDao studentDao;
    @Resource
    TeacherDao teacherDao;

    public void updateState(Long id, Integer state) {
        CourseDtlInfo info = dao.findOne(id);
        info.setState(state);
        dao.update(info);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public void offOrOn(Long id, Integer state) {
        updateState(id, state);
    }

    @Override
    public Pagination<CourseDtlInfo> getCourseListTeacher(String teacherAccount, BasePageParameter basePageParameter) {
        if (basePageParameter.getPageNum() < 0)
            throw new RuntimeException(ConstantEnum.PARAM_FAIL.getMsg());

        FilterMap map = new FilterMap();
        if (StringUtils.isNotBlank(teacherAccount)) {
            map.eq("teacher_account", teacherAccount);
        }

        OrderMap orderMap = new OrderMap();
        orderMap.asc("date");
        Pagination<CourseDtlInfo> page = dao.page(map, orderMap,
                basePageParameter.getPageNum(),
                basePageParameter.getPageSize());
        return page;

    }

    @Override
    public Pagination<CourseDtlInfo> getCourseListStudent(StudentInfo student, BasePageParameter basePageParameter) {
        if (basePageParameter.getPageNum() < 0)
            throw new RuntimeException(ConstantEnum.PARAM_FAIL.getMsg());

        FilterMap map = new FilterMap();
        if (null != student.getAcademyId()) {
            map.eq("grade_id", student.getGradeId());
        }
        if (null != student.getSpecialtyId()) {
            map.eq("specialty_id", student.getSpecialtyId());
        }
        if (null != student.getClassId()) {
            map.eq("class_id", student.getClassId());
        }
        map.eq("state", StateEnum.COURSE_NORMAL.getCode());

        OrderMap orderMap = new OrderMap();
        orderMap.asc("date");
        Pagination<CourseDtlInfo> page = dao.page(map, orderMap,
                basePageParameter.getPageNum(),
                basePageParameter.getPageSize());
        return page;
    }

    @Override
    public List<CourseDtlInfo> getCourseListTeacher(String teacherAccount, Long academicYearId, String semester) {
        FilterMap map = new FilterMap();
        map.eq("teacher_account", teacherAccount);
        map.eq("academic_year_id", academicYearId);
        map.eq("semester", semester);
        map.eq("state", StateEnum.OK.getCode());

        OrderMap orderMap = new OrderMap();
        orderMap.asc("date");

        List<CourseDtlInfo> infos = dao.list(map, orderMap);
        return infos;
    }

    @Override
    public List<CourseDtlInfo> getCourseListStudent(StudentInfo student, Long academicYearId, String semester) {
        // 先从courseCode中取    通过 classId匹配 相应的 courseCode
        // 再从courseDtl中 取 inInteger（courseCode）

        FilterMap map = new FilterMap();
        FilterMap map1 = new FilterMap();
        if (null != student.getClassId()) {
            map.eq("class_id", student.getClassId());
        }
        map.eq("academic_year_id", academicYearId);
        map1.eq("academic_year_id", academicYearId);
        map.eq("semester", semester);
        map1.eq("semester", semester);
        map.eq("state", StateEnum.OK.getCode());
        map1.eq("state", StateEnum.OK.getCode());
        // 不同条件
        if (null != student.getAccount()) {
            map1.like("contains", "%"+student.getAccount()+"%");
        }
        // 联合
        map.or(new DefaultDynamicQuery(map1));

        List<CourseCodeInfo> list = courseCodeDao.list(map, new OrderMap());
        List<Long> codes = new ArrayList<>();
        for (CourseCodeInfo info : list){
            codes.add(info.getCourseCode());
        }

        if (CollectionUtils.isEmpty(codes))
            return Lists.newArrayList();
        map.clear();
        map.in("course_code", codes);
        map.eq("state", StateEnum.OK.getCode());
        OrderMap orderMap = new OrderMap();
        orderMap.asc("date");
        List<CourseDtlInfo> infos = dao.list(map, orderMap);
        return infos;
    }

    @Override
    public CourseDtlVO findOneVO(Long courseDtlId) {
        return dao.getDetailInfo(courseDtlId);
    }

    @Override
    public Pagination<CourseDtlVO> pageListVO(CourseDtlVO courseDtlVO, BasePageParameter basePageParameter) {
        if (basePageParameter.getPageNum() < 0)
            throw new RuntimeException(ConstantEnum.PARAM_FAIL.getMsg());

        FilterMap map = new FilterMap();
        if (null != courseDtlVO.getCourseCode()) {
            map.like("course_code", "%"+courseDtlVO.getCourseCode()+"%");
        }
        if (StringUtils.isNotBlank( courseDtlVO.getTeacherAccount())) {
            map.like("teacher_account", "%"+courseDtlVO.getTeacherAccount()+"%");
        }
        if (null != courseDtlVO.getSpecialtyId()) {
            map.eq("specialty_id", courseDtlVO.getSpecialtyId());
        }
        if (null != courseDtlVO.getState()) {
            map.eq("state", courseDtlVO.getState());
        }

        OrderMap orderMap = new OrderMap();
        orderMap.desc("id");
        Pagination<CourseDtlVO> page = dao.pageVO(map, orderMap,
                basePageParameter.getPageNum(),
                basePageParameter.getPageSize());
        return page;
    }

    @Override
    public void modifySingleCoursePlace(Long courseDtlId, String teacherAccount, String place) throws MyException {

        FilterMap map = new FilterMap();
        map.eq("id", courseDtlId);
        map.eq("teacher_account", teacherAccount);
        CourseDtlInfo course = dao.findOne(map);
        if (null == course)
            throw new MyException(ConstantEnum.COURSE_NULL);
        course.setPlace(place);
        dao.update(course);
    }

    @Override
    public void modifyCoursePlace(Long courseCode, String teacherAccount, String place) throws MyException {
        FilterMap map = new FilterMap();
        map.eq("course_code", courseCode);
        map.eq("teacher_account", teacherAccount);
        CourseDtlInfo course = dao.findOne(map);
        if (null == course)
            throw new MyException(ConstantEnum.COURSE_NULL);
        dao.updateAllPlace(courseCode, place);
    }

    // TODO 额外增加删除学生 最多20个
    @Override
    public void addCourseStudent(Long courseCode, String teacherAccount, String containStudent) throws MyException {
        FilterMap map = new FilterMap();
        map.eq("course_code", courseCode);
        map.eq("teacher_account", teacherAccount);
        CourseCodeInfo course = courseCodeDao.findOne(map);
        if (null == course)
            throw new MyException(ConstantEnum.COURSE_NULL);

        map.clear();
        map.eq("account", containStudent);
        StudentInfo student = studentDao.findOne(map);
        if (null == student)
            throw new MyException(ConstantEnum.STUDENT_NULL);

        String currContains = course.getContains();
        String currExports = course.getExports();
        String newContains = currContains;
        String newExports = currExports;

        // 如果该学生已在对应班级中
        if (student.getSpecialtyId().equals(course.getSpecialtyId()) && student.getClassId().equals(course.getClassIds()) && student.getGradeId().equals(course.getGradeId()))
            throw new MyException(ConstantEnum.STUDENT_REPEAT_ADD);

        // 如果该学生 被排除
        if (currExports.contains(containStudent)) {
            newExports = currExports.replace("containStudent,", "");
            // 如果该学生 不在该课程班级内
            if (!student.getClassId().equals(course.getClassIds()))
                newContains = newContains.concat(containStudent + ",");

            courseCodeDao.updateAllStudent(courseCode, newContains, newExports);
            return;
        }

        // 如果该学生已被 包含
        if (currContains.contains(containStudent))
            throw new MyException(ConstantEnum.STUDENT_REPEAT_ADD);
        else {
            newContains = newContains.concat(containStudent + ",");
            courseCodeDao.updateAllStudent(courseCode, newContains, newExports);
        }
    }

    @Override
    public void addCourseStudent(Long courseCode, String teacherAccount, List<String> containsStudent) throws MyException {

        // TODO 修改 上课学生  批量增加 学生
    }

    // TODO 额外增加删除学生 最多20个
    @Override
    public void deleteCourseStudent(Long courseCode, String teacherAccount, String exportStudent) throws MyException {
        FilterMap map = new FilterMap();
        map.eq("course_code", courseCode);
        map.eq("teacher_account", teacherAccount);
        CourseCodeInfo course = courseCodeDao.findOne(map);
        if (null == course)
            throw new MyException(ConstantEnum.COURSE_NULL);

        map.clear();
        map.eq("account", exportStudent);
        StudentInfo student = studentDao.findOne(map);
        if (null == student)
            throw new MyException(ConstantEnum.STUDENT_NULL);

        String currContains = course.getContains();
        String currExports = course.getExports();
        String newContains = currContains;
        String newExports = currExports;

        // 如果该学生 被包含
        if (currContains.contains(exportStudent)) {
            newContains = currContains.replace(exportStudent+",", "");
            courseCodeDao.updateAllStudent(courseCode, newContains, newExports);
            return;
        }

        // 如果该学生已被 排除
        if (currExports.contains(exportStudent))
            throw new MyException(ConstantEnum.STUDENT_REPEAT_DELETE);
        else {
            // 如果该学生在课程中
            String[] classIds = course.getClassIds().split(",");
            for (String s : classIds){
                if (String.valueOf(student.getClassId()).equals(s)){
                    newExports = currExports.concat(exportStudent + ",");
                    courseCodeDao.updateAllStudent(courseCode, newContains, newExports);
                    return;
                }
            }

            // for 遍历完还没 处理 即没在课程中
            throw new MyException(ConstantEnum.STUDENT_NOT_IN_COURSE);

            // 该学生不在 该课程中
            //if (!student.getClassId().equals())
            //    throw new MyException(ConstantEnum.STUDENT_NOT_IN_COURSE);

        }
    }

    @Override
    public void deleteCourseStudent(Long courseId, String teacherAccount, List<String> exportsStudent) throws MyException {

        // TODO 修改 上课学生 批量删除 学生
    }

    @Override
    public void addSingleCourse(CourseCodeInfo courseCodeInfo, String place, Date date, Integer scheduleName) throws MyException {
        Long courseCode = courseCodeInfo.getCourseCode();
        FilterMap map = new FilterMap();
        map.eq("account", courseCodeInfo.getTeacherAccount());
        TeacherInfo teacher = teacherDao.findOne(map);
        if (null == teacher)
            throw new MyException(ConstantEnum.TEACHER_NULL);

        if (null == courseCode){// 没有 课程模板      则建立课程模板
            if (null == courseCodeInfo.getCourseId() ||
                    null == courseCodeInfo.getCourseName() ||
                    null == courseCodeInfo.getGradeId() ||
                    null == courseCodeInfo.getSpecialtyId() ||
                    null == courseCodeInfo.getClassIds() ||
                    null == courseCodeInfo.getAcademicYearId() ||
                    null == courseCodeInfo.getAcademicYearName() ||
                    null == courseCodeInfo.getSemester())
                throw new MyException(ConstantEnum.PARAM_LACK);

            courseCodeInfo.setTeacherName(teacher.getName());
//            courseCodeInfo.setExports(null == courseCodeInfo.getExports() ? "" : courseCodeInfo.getExports());
//            courseCodeInfo.setContains(null == courseCodeInfo.getContains() ? "" : courseCodeInfo.getContains());
            courseCodeInfo.setExports("");
            courseCodeInfo.setContains("");

            courseCodeInfo.setState(StateEnum.OK.getCode());
            courseCode = courseCodeDao.insert(courseCodeInfo);
        }
        // 有 课程模板
        map.clear();
        map.eq("course_code", courseCode);
        map.eq("teacher_account", courseCodeInfo.getTeacherAccount());
        CourseCodeInfo course = courseCodeDao.findOne(map);
        if (null == course)
            throw new MyException(ConstantEnum.TARGET_NULL);
        CourseDtlInfo newCourse = new CourseDtlInfo();

        newCourse.setTeacherAccount(course.getTeacherAccount());
        newCourse.setTeacherName(course.getTeacherName());
        newCourse.setCourseId(course.getCourseId());
        newCourse.setCourseName(course.getCourseName());
        newCourse.setCourseCode(course.getCourseCode());
        newCourse.setPlace(place);
        newCourse.setDate(date);
        newCourse.setAcademicYearId(course.getAcademicYearId());
        newCourse.setAcademicYearName(course.getAcademicYearName());
        newCourse.setSemester(course.getSemester());
        newCourse.setScheduleName(scheduleName);
        newCourse.setState(StateEnum.OK.getCode());
        dao.insert(newCourse);

    }

    @Override
    public void deleteSingleCourse(Long courseDtlId, Long courseCode, String teacherAccount) throws MyException {
        FilterMap map = new FilterMap();
        map.eq("id", courseDtlId);
        map.eq("course_code", courseCode);
        map.eq("teacher_account", teacherAccount);
        CourseDtlInfo course = dao.findOne(map);
        if (null == course)
            throw new MyException(ConstantEnum.COURSE_NULL);
        else
            course.setState(StateEnum.FROZEN.getCode());
        dao.update(course);
    }
}
