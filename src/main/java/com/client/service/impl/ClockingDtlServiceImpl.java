package com.client.service.impl;

import com.client.service.ClockingDtlService;
import com.cms.dao.ClockingDtlDao;
import com.cms.domain.ClockingDtlInfo;
import com.cms.domain.StudentInfo;
import com.cms.dto.ClockingDTO;
import com.cms.mapper.ClockingDtlMapper;
import com.cms.service.impl.BaseServiceImpl;
import com.cms.vo.BasePageParameter;
import com.cms.vo.ClockingDtlVO;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.page.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/21 20:50
 */
@Service
public class ClockingDtlServiceImpl extends BaseServiceImpl<ClockingDtlInfo, ClockingDtlVO, ClockingDtlMapper, ClockingDtlDao> implements ClockingDtlService {

    @Override
    public Pagination<ClockingDtlVO> pageListVO(ClockingDtlVO clockingDtlVO, BasePageParameter basePageParameter) {
        FilterMap map=new FilterMap();
        if(null != clockingDtlVO.getCourseCode()){
            map.like("t_clocking.course_code","%"+clockingDtlVO.getCourseCode()+"%");
        }
        if(StringUtils.isNotBlank(clockingDtlVO.getTeacherAccount())){
            map.like("t_clocking.teacher_account","%"+clockingDtlVO.getTeacherAccount()+"%");
        }
        if (null != clockingDtlVO.getState()){
            map.eq("t_clocking_dtl.state", clockingDtlVO.getState());
        }
        if (null != clockingDtlVO.getClockingId()){
            map.eq("t_clocking_dtl.clocking_id", clockingDtlVO.getClockingId());
        }

        OrderMap orderMap=new OrderMap();
        orderMap.desc("id");
        Pagination<ClockingDtlVO> page  =dao.pageVO(map, orderMap,
                basePageParameter.getPageNum(),
                basePageParameter.getPageSize());
        return page;
    }

    public void updateState(Long id, Integer state) {
        ClockingDtlInfo info = dao.findOne(id);
        info.setState(state);
        dao.update(info);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public void offOrOn(Long id, Integer state) {
        updateState(id, state);
    }
}
