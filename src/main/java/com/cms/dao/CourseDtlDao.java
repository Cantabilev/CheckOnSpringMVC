package com.cms.dao;

import com.cms.domain.ClockingDtlInfo;
import com.cms.domain.CourseDtlInfo;
import com.cms.mapper.CourseDtlMapper;
import com.cms.vo.CourseDtlVO;
import com.cms.vo.TeacherVO;
import com.core.dao.BaseDao;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.dao.query.support.DefaultDynamicQuery;
import com.core.exception.MyException;
import com.core.page.Pagination;
import com.core.util.enums.ConstantEnum;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/8 8:39
 */
@Repository
public class CourseDtlDao extends BaseDao<CourseDtlInfo, CourseDtlMapper> {

    public CourseDtlVO getDetailInfo(Long courseDtlId){
        try{
            return mapper.courseDtlVO(courseDtlId);
        }catch (Exception e){
            throw new RuntimeException(ConstantEnum.SYS_ERROR.getMsg() + e.getMessage());
        }
    }

    public void updateAllPlace(Long courseCode, String place) throws MyException {
        try{
            mapper.updateAllPlace(courseCode, place);
        }catch(Exception e){
            throw new MyException(ConstantEnum.SYS_ERROR, e.getMessage());
        }
    }

    public CourseDtlInfo findOne(String teacherAcount, Long courseDtlId){
        FilterMap map = new FilterMap();
        map.eq("id", courseDtlId);
        map.eq("teacher_account", teacherAcount);
        return super.findOne(map);
    }

    public List<CourseDtlInfo> getListByCourseDtlId(List<Long> courseDtlIds){
        FilterMap map = new FilterMap();
        map.in("id", courseDtlIds);
        return super.list(map, new OrderMap());
    }

    public List<CourseDtlInfo> getListByCourseCode(Long courseCode){
        FilterMap map = new FilterMap();
        map.eq("course_code", courseCode);
        return super.list(map, new OrderMap());
    }

    public Pagination<CourseDtlVO> pageVO(FilterMap filterMap, OrderMap orderMap, int pageIndex, int pageLimit){
        DefaultDynamicQuery query = new DefaultDynamicQuery(filterMap,orderMap,pageIndex,pageLimit);
        List<CourseDtlVO> list = mapper.listVO(query);
        Integer count = mapper.countVO(query);
        Pagination<CourseDtlVO> page = new Pagination<>();
        page.setTotalItemsCount(count);
        page.setItems(list);
        page.setPageNum(pageIndex);
        page.setPageSize(pageLimit);
        page.setTotalPageCount((count - 1) / pageLimit + 1);
        return page;
    }
}
