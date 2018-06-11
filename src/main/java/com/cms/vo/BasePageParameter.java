package com.cms.vo;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/9 8:40
 */
public class BasePageParameter {
    private int pageNum = 1;
    private int pageSize = 10;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
