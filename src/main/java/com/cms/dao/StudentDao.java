package com.cms.dao;

import com.cms.domain.StudentInfo;
import com.cms.mapper.StudentMapper;
import com.cms.vo.StudentVO;
import com.cms.vo.TeacherVO;
import com.core.dao.BaseDao;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.dao.query.support.DefaultDynamicQuery;
import com.core.page.Pagination;
import com.core.util.enums.ConstantEnum;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/2 9:15
 */
@Repository
public class StudentDao extends BaseDao<StudentInfo, StudentMapper> {
    public StudentVO getDetail(String account){
        try{
            return mapper.studentVO(account);
        }catch (Exception e){
            throw new RuntimeException(ConstantEnum.SYS_ERROR.getMsg() + e.getMessage());
        }
    }

    public List<StudentInfo> findByClassId(Long classId){
        FilterMap map = new FilterMap();
        map.eq("class_id", classId);
        OrderMap orderMap = new OrderMap();
        return super.list(map, orderMap);
    }

    public StudentInfo findOneByAccount(String studentAccount){
        FilterMap map = new FilterMap();
        map.eq("account", studentAccount);
        return super.findOne(map);
    }

    public Pagination<StudentVO> pageVO(FilterMap filterMap, OrderMap orderMap, int pageIndex, int pageLimit){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,orderMap,pageIndex,pageLimit);
        List<StudentVO> list = mapper.listVO(query);
        Integer count = mapper.countVO(query);
        Pagination<StudentVO> page = new Pagination<>();
        page.setTotalItemsCount(count);
        page.setItems(list);
        page.setPageNum(pageIndex);
        page.setPageSize(pageLimit);
        page.setTotalPageCount((count - 1) / pageLimit + 1);
        return page;
    }
}
