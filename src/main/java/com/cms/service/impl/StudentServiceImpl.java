package com.cms.service.impl;

import com.cms.dao.StudentDao;
import com.cms.dao.TeacherDao;
import com.cms.domain.StudentInfo;
import com.cms.domain.TeacherInfo;
import com.cms.service.StudentService;
import com.cms.service.TeacherService;
import com.cms.vo.BasePageParameter;
import com.cms.vo.StudentVO;
import com.cms.vo.TeacherVO;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.page.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/9 20:59
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    StudentDao studentDao;

    @Override
    public Pagination<StudentVO> pageListVO(StudentVO vo, BasePageParameter basePageParameter) {
        FilterMap map=new FilterMap();
        if(StringUtils.isNotBlank(vo.getAccount())){
            map.like("account","%"+vo.getAccount()+"%");
        }
        if(StringUtils.isNotBlank(vo.getName())){
            map.like("t_teacher.name","%"+vo.getName()+"%");
        }
        if(null != vo.getAcademyId()){
            map.eq("academy_id", vo.getAcademyId());
        }
        if(null != vo.getState()){
            map.eq("state", vo.getState());
        }
        OrderMap orderMap=new OrderMap();
        orderMap.desc("id");
        Pagination<StudentVO> page  =studentDao.pageVO(map, orderMap,
                basePageParameter.getPageNum(),
                basePageParameter.getPageSize());
        return page;
    }

    public void updateState(Long id, Integer state) {
        StudentInfo info = studentDao.findOne(id);
        info.setState(state);
        studentDao.update(info);
    }

    // TODO 封禁教师的同时 对其所有课程 封禁
    @Transactional(rollbackFor=Exception.class)
    @Override
    public void offOrOn(Long id, Integer state) {
        updateState(id, state);
//        if(status.equals( HhConstants.coachInfo.Status.off.getCode())){
    }

    @Override
    public StudentInfo findOne(Long id) {
        FilterMap map = new FilterMap();
        map.eq("id", id);
        return studentDao.findOne(map);
    }

    @Override
    public StudentInfo findOne(String account) {
        FilterMap map = new FilterMap();
        map.eq("account", account);
        return studentDao.findOne(map);
    }

    @Override
    public void insert(StudentInfo student) {
        studentDao.insert(student);
    }

    @Override
    public void update(StudentInfo student) {
        studentDao.update(student);
    }
}
