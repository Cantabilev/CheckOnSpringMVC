package com.cms.service;

import com.cms.domain.AcademyInfo;
import com.cms.vo.BasePageParameter;
import com.core.page.Pagination;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/9 23:38
 */
public interface AcademyService {

    public List<AcademyInfo> getAllAcademy();

    public AcademyInfo findOne(Long id);

    public void insert(AcademyInfo academyInfo);

    public void update(AcademyInfo academyInfo);

    Pagination<AcademyInfo> pageList(AcademyInfo academyInfo, BasePageParameter basePageParameter);
}
