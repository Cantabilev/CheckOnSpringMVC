package com.cms.mapper;

import com.cms.domain.ClockingDtlInfo;
import com.cms.dto.StudentClockingDtlDTO;
import com.cms.vo.ClockingDtlVO;
import com.core.dao.mapper.BaseMapper;
import com.core.dao.query.Query;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/8 8:35
 */
public interface ClockingDtlMapper extends BaseMapper<ClockingDtlInfo> {

    public Integer insertBatch(List<ClockingDtlInfo> list);

    @Select("select t_clocking.course_dtl_id from t_clocking,t_clocking_dtl " +
            "WHERE t_clocking.id = t_clocking_dtl.clocking_id AND t_clocking_dtl.student_account = #{studentAccount} AND t_clocking.submit_time > #{date}")
    public List<Long> getClockingIng(String studentAccount, Date date);

    /**
     * 正在考勤的查询使用
     * @param query
     * @return
     */
    public List<Long> listClockingVO(Query query);

    public List<StudentClockingDtlDTO> listStudentClockingDtlVO(Query query);

    public Integer countListStudentClockingDtlVO(Query query);


    public List<ClockingDtlVO> listVO(Query query);

    public Integer countVO(Query query);

    public void updateRepeat(ClockingDtlInfo info);
}
