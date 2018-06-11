package com.cms.service.impl;

import com.cms.dao.AcademyDao;
import com.cms.domain.AcademicYearInfo;
import com.cms.domain.AcademyInfo;
import com.cms.service.AcademyService;
import com.cms.vo.BasePageParameter;
import com.cms.vo.TeacherVO;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.page.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/9 23:40
 */
@Service
public class AcademyServiceImpl implements AcademyService {

    @Autowired
    AcademyDao academyDao;

    @Override
    public List<AcademyInfo> getAllAcademy() {
        return academyDao.getAllAcademy();
    }

    @Override
    public AcademyInfo findOne(Long id) {
        FilterMap map = new FilterMap();
        map.eq("id", id);
        return academyDao.findOne(map);
    }

    @Override
    public void insert(AcademyInfo academyInfo) {
        academyDao.insert(academyInfo);
    }

    @Override
    public void update(AcademyInfo academyInfo) {
        academyDao.update(academyInfo);
    }

    @Override
    public Pagination<AcademyInfo> pageList(AcademyInfo academyInfo, BasePageParameter basePageParameter) {
        FilterMap map=new FilterMap();

        OrderMap orderMap=new OrderMap();
        orderMap.desc("id");
        Pagination<AcademyInfo> page  =academyDao.page(map, orderMap,
                basePageParameter.getPageNum(),
                basePageParameter.getPageSize());
        return page;
    }
}
