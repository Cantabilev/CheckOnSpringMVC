package com.cms.service.impl;

import com.cms.dao.ClassDao;
import com.cms.dao.SpecialtyDao;
import com.cms.domain.ClassInfo;
import com.cms.domain.SpecialtyInfo;
import com.cms.service.ClassService;
import com.cms.service.SpecialtyService;
import com.cms.vo.BasePageParameter;
import com.cms.vo.ClassVO;
import com.cms.vo.SpecialtyVO;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.page.Pagination;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/5 15:13
 */
@Service
public class ClassServiceImpl implements ClassService {

    @Resource
    ClassDao classDao;

    @Override
    public ClassInfo findOne(Long id) {
        FilterMap map = new FilterMap();
        map.eq("id", id);
        return classDao.findOne(map);
    }

    @Override
    public void insert(ClassInfo classInfo) {
        classDao.insert(classInfo);
    }

    @Override
    public void update(ClassInfo classInfo) {
        classDao.update(classInfo);
    }

    @Override
    public Pagination<ClassVO> pageListVO(ClassVO vo, BasePageParameter basePageParameter) {

        FilterMap map=new FilterMap();
        if(null != vo.getAcademyId()){
            map.eq("t_academy.id", vo.getAcademyId());
        }
        if(null != vo.getSpecialtyCode()){
            map.like("t_specialty.specialty_code", "%"+vo.getSpecialtyCode()+"%");
        }
        if(null != vo.getClassCode()){
            map.like("t_class.class_code", "%"+vo.getClassCode()+"%");
        }
        OrderMap orderMap=new OrderMap();
        orderMap.desc("id");
        Pagination<ClassVO> page  =classDao.pageVO(map, orderMap,
                basePageParameter.getPageNum(),
                basePageParameter.getPageSize());
        return page;
    }

    @Override
    public List<ClassInfo> getAll() {
        List<ClassInfo> list = classDao.list(new FilterMap(), new OrderMap());
        return list;
    }

    @Override
    public List<ClassInfo> getAllByGradeIdAndSpecialtyId(Long gradeId, Long specialtyId){
        FilterMap map = new FilterMap();
        map.eq("grade_id", gradeId);
        map.eq("specialty_id", specialtyId);
        List<ClassInfo> list = classDao.list(map, new OrderMap());
        return list;
    }
}
