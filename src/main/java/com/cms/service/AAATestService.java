package com.cms.service;

import com.cms.domain.StudentInfo;
import com.cms.vo.StudentVO;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/19 17:15
 */
public interface AAATestService extends BaseService<StudentInfo, StudentVO> {

    public Integer testFunction();
}
