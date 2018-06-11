package com.cms.service.impl;

import com.cms.dao.SpecialtyDao;
import com.cms.domain.SpecialtyInfo;
import com.cms.service.SpecialtyService;
import com.cms.vo.BasePageParameter;
import com.cms.vo.SpecialtyVO;
import com.cms.vo.TeacherVO;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.page.Pagination;
import org.apache.commons.lang3.StringUtils;
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
public class SpecialtyServiceImpl implements SpecialtyService {

    @Resource
    SpecialtyDao specialtyDao;

    @Override
    public SpecialtyInfo findOne(Long id) {
        FilterMap map = new FilterMap();
        map.eq("id", id);
        return specialtyDao.findOne(map);
    }

    @Override
    public List<SpecialtyInfo> getAll() {
        List<SpecialtyInfo> list = specialtyDao.list(new FilterMap(), new OrderMap());
        return list;
    }

    @Override
    public List<SpecialtyInfo> getAllByAcademyId(Long academyId) {
        FilterMap map = new FilterMap();
        map.eq("academy_id", academyId);
        List<SpecialtyInfo> list = specialtyDao.list(map, new OrderMap());
        return list;
    }

    @Override
    public void insert(SpecialtyInfo specialtyInfo) {
        specialtyDao.insert(specialtyInfo);
    }

    @Override
    public void update(SpecialtyInfo specialtyInfo) {
        specialtyDao.update(specialtyInfo);
    }

    @Override
    public Pagination<SpecialtyVO> pageListVO(SpecialtyVO vo, BasePageParameter basePageParameter) {

        FilterMap map=new FilterMap();
        if(null != vo.getAcademyId()){
            map.eq("academy_id", vo.getAcademyId());
        }
        if(null != vo.getSpecialtyCode()){
            map.like("specialty_code", "%"+vo.getSpecialtyCode()+"%");
        }
        OrderMap orderMap=new OrderMap();
        orderMap.desc("id");
        Pagination<SpecialtyVO> page  =specialtyDao.pageVO(map, orderMap,
                basePageParameter.getPageNum(),
                basePageParameter.getPageSize());
        return page;
    }
}
