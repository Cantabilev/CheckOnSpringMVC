package com.client.service;

import com.cms.domain.ClockingDtlInfo;
import com.cms.service.BaseService;
import com.cms.service.impl.BaseServiceImpl;
import com.cms.vo.ClockingDtlVO;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/21 20:27
 */
public interface ClockingDtlService extends BaseService<ClockingDtlInfo, ClockingDtlVO> {

    void offOrOn(Long id, Integer state);
}
