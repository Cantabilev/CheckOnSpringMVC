package com.cms.mapper;

import com.cms.domain.ClockingInfo;
import com.cms.dto.ClockingDTO;
import com.core.dao.mapper.BaseMapper;
import com.core.dao.query.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/8 8:33
 */
public interface ClockingMapper extends BaseMapper<ClockingInfo> {

    public List<ClockingDTO> getClockingDtlByCourseCode(@Param("courseCode") Long courseCode);

    public List<ClockingDTO> listClockingVO(Query query);

    public Integer countClockingVO(Query query);
}
