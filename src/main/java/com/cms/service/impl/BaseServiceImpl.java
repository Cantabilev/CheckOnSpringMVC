package com.cms.service.impl;

import com.cms.domain.StudentInfo;
import com.cms.service.BaseService;
import com.cms.vo.BasePageParameter;
import com.cms.vo.TeacherVO;
import com.core.dao.BaseDao;
import com.core.dao.mapper.BaseMapper;
import com.core.dao.model.BaseEntity;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.page.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/19 16:53
 */
public abstract class BaseServiceImpl<T extends BaseEntity, VO, M extends BaseMapper<T>, D extends BaseDao<T, M>> implements BaseService<T, VO> {

    @Autowired
    protected D dao;

    @Override
    public T findOne(Long id) {
        FilterMap map = new FilterMap();
        map.eq("id", id);
        return dao.findOne(map);
    }

    @Override
    public void insert(T t) {
        dao.insert(t);
    }

    @Override
    public void update(T t) {
        dao.update(t);
    }

    @Override
    public List<T> getAll() {
        FilterMap filterMap = new FilterMap();
        OrderMap orderMap = new OrderMap();
        orderMap.asc("id");
        return dao.list(filterMap, orderMap);
    }

    @Override
    public List<T> getListByArg(FilterMap filterMap, OrderMap orderMap) {
        return dao.list(filterMap, orderMap);
    }
}
