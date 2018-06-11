package com.core.dao.query.support;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/3/18 18:06
 *
 * 分页参数
 */
public class PageParam {
    /**
     * 开始
     */
    private Integer start;

    /**
     * 限制
     */
    private Integer limit;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
