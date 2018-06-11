package com.cms.dao;

import com.cms.domain.StudentInfo;
import com.cms.domain.TeacherInfo;
import com.cms.mapper.StudentMapper;
import com.cms.mapper.TeacherMapper;
import com.cms.vo.TeacherVO;
import com.core.dao.BaseDao;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.dao.query.support.DefaultDynamicQuery;
import com.core.page.Pagination;
import com.core.util.enums.ConstantEnum;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/2 9:15
 */
@Repository
public class TeacherDao extends BaseDao<TeacherInfo, TeacherMapper> {

    public TeacherVO getDetail(String account){
        try{
            return mapper.teacherVO(account);
        }catch (Exception e){
            throw new RuntimeException(ConstantEnum.SYS_ERROR.getMsg() + e.getMessage());
        }
    }

    public Pagination<TeacherVO> pageVO(FilterMap filterMap, OrderMap orderMap, int pageIndex, int pageLimit){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,orderMap,pageIndex,pageLimit);
        List<TeacherVO> list = mapper.listVO(query);
        Integer count = mapper.countVO(query);
        Pagination<TeacherVO> page = new Pagination<TeacherVO>();
        page.setTotalItemsCount(count);
        page.setItems(list);
        page.setPageNum(pageIndex);
        page.setPageSize(pageLimit);
        page.setTotalPageCount((count - 1) / pageLimit + 1);
        return page;
    }
}
