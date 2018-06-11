package com.cms.dao;

import com.cms.domain.ClassInfo;
import com.cms.mapper.ClassMapper;
import com.cms.vo.ClassVO;
import com.cms.vo.SpecialtyVO;
import com.core.dao.BaseDao;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.dao.query.support.DefaultDynamicQuery;
import com.core.page.Pagination;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/8 8:40
 */
@Repository
public class ClassDao extends BaseDao<ClassInfo, ClassMapper> {

    public Pagination<ClassVO> pageVO(FilterMap filterMap, OrderMap orderMap, int pageIndex, int pageLimit){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,orderMap,pageIndex,pageLimit);
        List<ClassVO> list = mapper.listVO(query);
        Integer count = mapper.countVO(query);
        Pagination<ClassVO> page = new Pagination<>();
        page.setTotalItemsCount(count);
        page.setItems(list);
        page.setPageNum(pageIndex);
        page.setPageSize(pageLimit);
        page.setTotalPageCount((count - 1) / pageLimit + 1);
        return page;
    }
}
