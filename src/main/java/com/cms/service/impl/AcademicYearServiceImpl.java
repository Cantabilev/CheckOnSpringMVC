package com.cms.service.impl;

import com.cms.dao.AcademicYearDao;
import com.cms.domain.AcademicYearInfo;
import com.cms.domain.TeacherInfo;
import com.cms.service.AcademicYearService;
import com.cms.vo.BasePageParameter;
import com.cms.vo.TeacherVO;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.page.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/20 15:02
 */
@Service
public class AcademicYearServiceImpl implements AcademicYearService {

    @Resource
    AcademicYearDao academicYearDao;

    @Override
    public AcademicYearInfo findOne(Long id) {
        FilterMap map = new FilterMap();
        map.eq("id", id);
        return academicYearDao.findOne(map);
    }

    @Override
    public void insert(AcademicYearInfo academicYear) {
        academicYearDao.insert(academicYear);
    }

    @Override
    public void update(AcademicYearInfo academicYear) {
        academicYearDao.update(academicYear);
    }

    @Override
    public Pagination<AcademicYearInfo> pageList(AcademicYearInfo vo, BasePageParameter basePageParameter) {
        FilterMap map=new FilterMap();

        OrderMap orderMap=new OrderMap();
        orderMap.desc("id");
        Pagination<AcademicYearInfo> page  =academicYearDao.page(map, orderMap,
                basePageParameter.getPageNum(),
                basePageParameter.getPageSize());
        return page;
    }
    @Override
    public List<AcademicYearInfo> getAcademicYearList() {
        FilterMap map = new FilterMap();
        OrderMap orderMap = new OrderMap();
        List<AcademicYearInfo> list = academicYearDao.list(map, orderMap);
        return list;
    }
}
