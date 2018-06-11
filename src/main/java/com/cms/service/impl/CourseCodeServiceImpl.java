package com.cms.service.impl;

import com.cms.dao.CourseCodeDao;
import com.cms.dao.StudentDao;
import com.cms.domain.CourseCodeInfo;
import com.cms.domain.CourseInfo;
import com.cms.domain.StudentInfo;
import com.cms.dto.CourseCodeDTO;
import com.cms.mapper.CourseCodeMapper;
import com.cms.service.CourseCodeService;
import com.cms.util.BeanUtil;
import com.cms.vo.BasePageParameter;
import com.cms.vo.StudentVO;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.exception.MyException;
import com.core.page.Pagination;
import com.core.util.enums.ConstantEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/4 18:00
 */
@Service
public class CourseCodeServiceImpl extends BaseServiceImpl<CourseCodeInfo, CourseCodeInfo, CourseCodeMapper, CourseCodeDao> implements CourseCodeService {

    @Resource
    StudentDao studentDao;

    @Override
    public CourseCodeDTO findOneDTO(Long courseCode) throws MyException {
        FilterMap map = new FilterMap();
        CourseCodeInfo info = dao.findOne(courseCode);
        if (null == info)
            throw new MyException(ConstantEnum.COURSE_CODE_NULL);
        CourseCodeDTO dto = BeanUtil.copyProperties(info, CourseCodeDTO.class);
        List<Long> classList = new ArrayList<>();
        List<StudentInfo> studentInfoList = new ArrayList<>();
        String[] classIds = info.getClassIds().split(",");
        for (String s : classIds){
            if (StringUtils.isNotBlank(s)) {
                classList.add(Long.valueOf(s));

                List<StudentInfo> temp = studentDao.findByClassId(Long.valueOf(s));
                if (CollectionUtils.isNotEmpty(temp))
                    studentInfoList.addAll(temp);
            }
        }

        String[] exports = info.getExports().split(",");
        String[] contains = info.getContains().split(",");

        Iterator<StudentInfo> it = studentInfoList.iterator();
        while(it.hasNext()){
            StudentInfo ele = it.next();
            for (String studentAccount : exports){
                if (ele.getAccount().equals(studentAccount))
                    it.remove();
            }
        }

        for (String s : contains) {
            if (StringUtils.isNotBlank(s)) {
                map.clear();
                map.eq("account", s);
                StudentInfo stu = studentDao.findOne(map);
                studentInfoList.add(stu);
            }
        }

        dto.setClassIdList(classList);
        dto.setStudentInfoList(studentInfoList);
        return dto;
    }

    public void updateState(Long courseCode, Integer state) {
        CourseCodeInfo info = dao.findOne(courseCode);
        info.setState(state);
        dao.update(info);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public void offOrOn(Long courseCode, Integer state) {
        updateState(courseCode, state);
    }

    @Override
    public Pagination<CourseCodeInfo> pageListVO(CourseCodeInfo courseCodeInfo, BasePageParameter basePageParameter) {
        FilterMap map=new FilterMap();
        if(null != courseCodeInfo.getCourseCode()){
            map.like("course_code","%"+courseCodeInfo.getCourseCode()+"%");
        }
        if(StringUtils.isNotBlank(courseCodeInfo.getTeacherAccount())){
            map.like("teacher_account","%"+courseCodeInfo.getTeacherAccount()+"%");
        }
        if(null != courseCodeInfo.getSpecialtyId()){
            map.eq("specialty_id", courseCodeInfo.getSpecialtyId());
        }
        if(null != courseCodeInfo.getState()){
            map.eq("state", courseCodeInfo.getState());
        }
        OrderMap orderMap=new OrderMap();
        orderMap.desc("course_code");
        Pagination<CourseCodeInfo> page  =dao.page(map, orderMap,
                basePageParameter.getPageNum(),
                basePageParameter.getPageSize());
        return page;
    }

    @Override
    public List<CourseCodeInfo> getAll() {
        FilterMap filterMap = new FilterMap();
        OrderMap orderMap = new OrderMap();
        orderMap.asc("course_code");
        return dao.list(filterMap, orderMap);
    }

    @Override
    public CourseCodeInfo findOne(Long id) {
        FilterMap map = new FilterMap();
        map.eq("course_code", id);
        return dao.findOne(map);
    }
}
