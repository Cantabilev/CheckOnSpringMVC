package com.core.dao.mapper;

import com.core.dao.query.Query;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/3/18 17:57
 */
public interface BaseMapper<T> {

    /**
     * 列表
     *
     * @param query
     * @return
     */
    public List<T> list(Query query);

    /**
     * 查找一个
     *
     * @param query
     * @return
     */
    public T findOne(Query query);

    /**
     * 插入
     *
     * @param t
     */
    public Long insert(T t);

    /**
     * 更新
     *
     * @param t
     */
    public void update(T t);

    /**
     * 数量
     *
     * @param query
     * @return
     */
    public Integer count(Query query);
}
