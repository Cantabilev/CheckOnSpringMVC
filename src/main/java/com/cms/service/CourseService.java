package com.cms.service;

import com.cms.domain.CourseInfo;
import com.cms.vo.CourseVO;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/20 17:34
 */
public interface CourseService extends BaseService<CourseInfo, CourseVO> {

    void offOrOn(Long id, Integer state);
}
