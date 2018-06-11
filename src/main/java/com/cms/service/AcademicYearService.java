package com.cms.service;

import com.cms.domain.AcademicYearInfo;
import com.cms.vo.BasePageParameter;
import com.core.page.Pagination;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/20 15:01
 */
public interface AcademicYearService {

    public AcademicYearInfo findOne(Long id);

    public void insert(AcademicYearInfo academicYear);

    public void update(AcademicYearInfo academicYear);

    public List<AcademicYearInfo> getAcademicYearList();

    Pagination<AcademicYearInfo> pageList(AcademicYearInfo vo, BasePageParameter basePageParameter);
}
