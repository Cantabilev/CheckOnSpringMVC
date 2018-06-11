package com.cms.dao;

import com.cms.domain.ClockingDtlInfo;
import com.cms.dto.ClockingDTO;
import com.cms.dto.StudentClockingDtlDTO;
import com.cms.mapper.ClockingDtlMapper;
import com.cms.vo.ClockingDtlVO;
import com.core.dao.BaseDao;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.dao.query.support.DefaultDynamicQuery;
import com.core.exception.MyException;
import com.core.page.Pagination;
import com.core.util.enums.ConstantEnum;
import com.core.util.enums.StateEnum;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/8 8:41
 */
@Repository
public class ClockingDtlDao extends BaseDao<ClockingDtlInfo, ClockingDtlMapper> {

    public Integer insertBatch(List<ClockingDtlInfo> list){
        return mapper.insertBatch(list);
    }

    public void updateRepeat(ClockingDtlInfo info) {
        mapper.updateRepeat(info);
    }

    public ClockingDtlInfo findOne(Long courseDtlId, String studentAccount){
        FilterMap map = new FilterMap();
        map.eq("course_dtl_id", courseDtlId);
        map.eq("student_account", studentAccount);
        return super.findOne(map);
    }

    public List<ClockingDtlInfo> getListByCourseDtlId(Long courseDtlId){
        FilterMap map = new FilterMap();
        map.eq("course_dtl_id", courseDtlId);
        return super.list(map, new OrderMap());
    }

    public List<Long> getClockingDtlIngListByStudentAccount(String studentAccount, Long timeStamp){
        FilterMap map = new FilterMap();
        map.eq("t_clocking_dtl.student_account", studentAccount);
        map.gt("t_clocking.submit_time", new Date(timeStamp));
        //map.eq("t_clocking_dtl.state", StateEnum.STUDENT_SUBMIT_DEFAULT.getCode());

        return mapper.listClockingVO(new DefaultDynamicQuery(map));
    }

    public List<ClockingDtlInfo> getListByStudentAccAndCourseCode(String studentAccount, Long courseCode){
        FilterMap map = new FilterMap();
        map.eq("student_account", studentAccount);
        map.eq("course_code", courseCode);
        return super.list(map, new OrderMap());
    }

    public List<StudentClockingDtlDTO> getListByClockingId(Long clockingId){
        FilterMap map = new FilterMap();
        map.eq("clocking_id", clockingId);
        return mapper.listStudentClockingDtlVO(new DefaultDynamicQuery(map));
    }

    public Integer countByClockingIdAndState(Long clockingId, Integer state){
        FilterMap map = new FilterMap();
        map.eq("clocking_id", clockingId);
        map.eq("state", state);
        return mapper.count(new DefaultDynamicQuery(map));
    }

    public Pagination<StudentClockingDtlDTO> pageClockingByStudent(FilterMap filterMap, OrderMap orderMap, int pageIndex, int pageLimit){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,orderMap,pageIndex,pageLimit);
        List<StudentClockingDtlDTO> list = mapper.listStudentClockingDtlVO(query);
        Integer count = mapper.countListStudentClockingDtlVO(query);
        Pagination<StudentClockingDtlDTO> page = new Pagination<>();
        page.setTotalItemsCount(count == null ? 0 : count);
        page.setItems(list);
        page.setPageNum(pageIndex);
        page.setPageSize(pageLimit);
        page.setTotalPageCount(count == null ? 0 : (count - 1) / pageLimit + 1);
        return page;
    }

    public Pagination<ClockingDtlVO> pageVO(FilterMap filterMap, OrderMap orderMap, int pageIndex, int pageLimit){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,orderMap,pageIndex,pageLimit);
        List<ClockingDtlVO> list = mapper.listVO(query);
        Integer count = mapper.countVO(query);
        Pagination<ClockingDtlVO> page = new Pagination<>();
        page.setTotalItemsCount(count);
        page.setItems(list);
        page.setPageNum(pageIndex);
        page.setPageSize(pageLimit);
        page.setTotalPageCount((count - 1) / pageLimit + 1);
        return page;
    }

}
