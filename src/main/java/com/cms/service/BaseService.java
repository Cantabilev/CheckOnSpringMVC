package com.cms.service;

import com.cms.domain.TeacherInfo;
import com.cms.vo.BasePageParameter;
import com.cms.vo.TeacherVO;
import com.core.dao.model.BaseEntity;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.page.Pagination;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/19 16:46
 */
public interface BaseService<T extends BaseEntity, VO> {

    public Pagination<VO> pageListVO(VO vo, BasePageParameter basePageParameter);

    T findOne(Long id);

    void insert(T t);

    void update(T t);

    public List<T> getAll();

    public List<T> getListByArg(FilterMap filterMap, OrderMap orderMap);
}
