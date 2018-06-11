package com.core.dao.query.support;

import com.core.dao.query.DynamicQuery;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/3/18 18:06
 *
 * 动态查询实现
 */
public class DefaultDynamicQuery implements DynamicQuery {
    private FilterMap filters;

    private OrderMap orders;

    private PageParam pageParam;

    public DefaultDynamicQuery(){
    }

    public DefaultDynamicQuery(FilterMap filterMap){
        this.filters = filterMap;
    }

    public DefaultDynamicQuery(FilterMap filterMap,OrderMap orderMap){
        this.filters = filterMap;
        this.orders = orderMap;
    }

    public DefaultDynamicQuery(FilterMap filterMap,OrderMap orderMap,Integer pageIndex,Integer pageLimit){
        this.filters = filterMap;
        this.orders = orderMap;
        pageParam = new PageParam();
        pageParam.setStart((pageIndex - 1) * pageLimit );
        pageParam.setLimit(pageLimit);
    }

    public FilterMap getFilters() {
        return filters;
    }

    public OrderMap getOrders() {
        return orders;
    }

    public PageParam getPageParam() {
        return pageParam;
    }
}
