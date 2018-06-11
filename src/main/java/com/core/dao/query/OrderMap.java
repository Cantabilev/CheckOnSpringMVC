package com.core.dao.query;

import com.core.dao.query.support.OrderParam;

import java.util.LinkedHashMap;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/3/18 18:00
 */
public class OrderMap extends LinkedHashMap<OrderParam,Object> {
    /**
     * 顺序
     *
     * @param column
     */
    public void asc(String column) {
        OrderParam order = new OrderParam(column, DynamicQuery.ORDER_DIRECTION_ASC);
        this.put(order, null);
    }

    /**
     * 反序
     *
     * @param column
     */
    public void desc(String column) {
        OrderParam order = new OrderParam(column, DynamicQuery.ORDER_DIRECTION_DESC);
        this.put(order, null);
    }
}
