package com.cms.dao;

import com.cms.domain.AcademyInfo;
import com.cms.mapper.AcademyMapper;
import com.core.dao.BaseDao;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/8 8:39
 */
@Repository
public class AcademyDao extends BaseDao<AcademyInfo, AcademyMapper> {

    public List<AcademyInfo> getAllAcademy(){
        FilterMap filterMap = new FilterMap();
        OrderMap orderMap = new OrderMap();
        orderMap.asc("id");
        return super.list(filterMap, orderMap);
    }
}
