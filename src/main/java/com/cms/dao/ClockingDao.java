package com.cms.dao;

import com.cms.domain.ClockingInfo;
import com.cms.dto.ClockingDTO;
import com.cms.mapper.ClockingMapper;
import com.core.dao.BaseDao;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.dao.query.support.DefaultDynamicQuery;
import com.core.page.Pagination;
import com.core.util.enums.StateEnum;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/8 8:39
 */
@Repository
public class ClockingDao extends BaseDao<ClockingInfo, ClockingMapper> {

    public ClockingInfo findOne(Long clockingId, String teacherAccount){
        FilterMap map = new FilterMap();
        map.eq("id", clockingId);
        map.eq("teacher_account", teacherAccount);
        return  super.findOne(map);
    }

    public ClockingInfo findOneByTeacherAndCoursesDtl(String teacherAccount, Long courseDtlId){
        FilterMap map = new FilterMap();
        map.eq("teacher_account", teacherAccount);
        map.eq("course_dtl_id", courseDtlId);
        return  super.findOne(map);
    }

    public ClockingInfo findOneByTeacherAndCourseCode(String teacherAccount, Long courseCode){
        FilterMap map = new FilterMap();
        map.eq("teacher_account", teacherAccount);
        map.eq("course_code", courseCode);
        return  super.findOne(map);
    }

    public List<ClockingDTO> getCourseCodeClocking(Long courseCode){
        return mapper.getClockingDtlByCourseCode(courseCode);
    }

    public Pagination<ClockingDTO> pageClockingByTeacher(FilterMap filterMap, OrderMap orderMap, int pageIndex, int pageLimit){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,orderMap,pageIndex,pageLimit);
        List<ClockingDTO> list = mapper.listClockingVO(query);
        Integer count = mapper.countClockingVO(query);
        Pagination<ClockingDTO> page = new Pagination<>();
        page.setTotalItemsCount(count);
        page.setItems(list);
        page.setPageNum(pageIndex);
        page.setPageSize(pageLimit);
        page.setTotalPageCount((count - 1) / pageLimit + 1);
        return page;
    }

    public Pagination<ClockingDTO> pageVO(FilterMap filterMap, OrderMap orderMap, int pageIndex, int pageLimit){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,orderMap,pageIndex,pageLimit);
        List<ClockingDTO> list = mapper.listClockingVO(query);
        Integer count = mapper.countClockingVO(query);
        Pagination<ClockingDTO> page = new Pagination<>();
        page.setTotalItemsCount(count);
        page.setItems(list);
        page.setPageNum(pageIndex);
        page.setPageSize(pageLimit);
        page.setTotalPageCount((count - 1) / pageLimit + 1);
        return page;
    }
}
