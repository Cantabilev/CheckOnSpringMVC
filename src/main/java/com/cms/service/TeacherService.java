package com.cms.service;

import com.cms.domain.TeacherInfo;
import com.cms.vo.BasePageParameter;
import com.cms.vo.TeacherVO;
import com.core.page.Pagination;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/9 20:57
 */
public interface TeacherService extends BaseService<TeacherInfo, TeacherVO> {

    void offOrOn(Long id, Integer status);

    TeacherInfo findOne(String account);
}
