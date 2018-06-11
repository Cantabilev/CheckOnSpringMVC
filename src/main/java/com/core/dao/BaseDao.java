package com.core.dao;

import com.core.dao.mapper.BaseMapper;
import com.core.dao.model.BaseEntity;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.dao.query.support.DefaultDynamicQuery;
import com.core.page.Pagination;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/3/18 17:55
 */
public class BaseDao <T extends BaseEntity,M extends BaseMapper<T>> {
    @Autowired
    protected M mapper;

    /**
     * 插入
     *
     * @param t
     */
    public Long insert(T t){
        return mapper.insert(t);
    }

    /**
     * 更新
     *
     * @param t
     */
    public void update(T t){
        mapper.update(t);
    }

    /**
     * 更新
     *
     * @param items
     */
    public void updateAll(List<T> items){
        for (T item : items) {
            mapper.update(item);
        }
    }

    /**
     * 查找一个
     *
     * @param id
     * @return
     */
    public T findOne(Long id){
        FilterMap filterMap = new FilterMap();
        filterMap.eq("id", id);
        return findOne(filterMap);
    }

    /**
     * 查找一个
     *
     * @param filterMap
     * @return
     */
    public T findOne(FilterMap filterMap){
        OrderMap orderMap = new OrderMap();
        List<T> list = list(filterMap, orderMap, 1, 1);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 列表
     *
     * @param filterMap
     * @param orderMap
     * @return
     */
    public List<T> list(FilterMap filterMap, OrderMap orderMap){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,orderMap);
        return mapper.list(query);
    }


    /**
     * 分页
     *
     * @param filterMap
     * @param orderMap
     * @param pageIndex
     * @param pageLimit
     * @return
     */
    protected List<T> list(FilterMap filterMap, OrderMap orderMap, int pageIndex, int pageLimit){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,orderMap,pageIndex,pageLimit);
        return mapper.list(query);
    }

    /**
     * 分页
     *
     * @param filterMap
     * @param orderMap
     * @param pageIndex
     * @param pageLimit
     * @return
     */
    public Pagination<T> page(FilterMap filterMap, OrderMap orderMap, int pageIndex, int pageLimit){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,orderMap,pageIndex,pageLimit);
        List<T> list = mapper.list(query);
        Integer count = mapper.count(query);
        Pagination<T> page = new Pagination<T>();
        page.setTotalItemsCount(count);
        page.setItems(list);
        page.setPageNum(pageIndex);
        page.setPageSize(pageLimit);
        page.setTotalPageCount((count - 1) / pageLimit + 1);
        return page;
    }

    /**
     * 求数量
     *
     * @param filterMap
     * @return
     */
    public Integer count(FilterMap filterMap){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap);
        return mapper.count(query);
    }
}
