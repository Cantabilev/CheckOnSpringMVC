package com.cms.service;

import com.cms.domain.CourseCodeInfo;
import com.cms.dto.CourseCodeDTO;
import com.core.exception.MyException;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/4 17:59
 */
public interface CourseCodeService extends BaseService<CourseCodeInfo, CourseCodeInfo> {

    public CourseCodeDTO findOneDTO(Long courseCode) throws MyException;

    public void offOrOn(Long courseCode, Integer state);
}
