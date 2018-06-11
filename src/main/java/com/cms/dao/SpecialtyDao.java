package com.cms.dao;

import com.cms.domain.AcademyInfo;
import com.cms.domain.SpecialtyInfo;
import com.cms.mapper.AcademyMapper;
import com.cms.mapper.SpecialtyMapper;
import com.cms.vo.SpecialtyVO;
import com.cms.vo.TeacherVO;
import com.core.dao.BaseDao;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.dao.query.support.DefaultDynamicQuery;
import com.core.page.Pagination;
import com.thoughtworks.xstream.mapper.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/8 8:39
 */
@Repository
public class SpecialtyDao extends BaseDao<SpecialtyInfo, SpecialtyMapper> {

    public Pagination<SpecialtyVO> pageVO(FilterMap filterMap, OrderMap orderMap, int pageIndex, int pageLimit){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,orderMap,pageIndex,pageLimit);
        List<SpecialtyVO> list = mapper.listVO(query);
        Integer count = mapper.countVO(query);
        Pagination<SpecialtyVO> page = new Pagination<>();
        page.setTotalItemsCount(count);
        page.setItems(list);
        page.setPageNum(pageIndex);
        page.setPageSize(pageLimit);
        page.setTotalPageCount((count - 1) / pageLimit + 1);
        return page;
    }
}
