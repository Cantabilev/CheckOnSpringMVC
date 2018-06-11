package com.cms.service;

import com.cms.domain.AcademyInfo;
import com.cms.domain.SpecialtyInfo;
import com.cms.vo.BasePageParameter;
import com.cms.vo.SpecialtyVO;
import com.core.page.Pagination;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/9 23:38
 */
public interface SpecialtyService {

    public SpecialtyInfo findOne(Long id);

    public List<SpecialtyInfo> getAll();

    public List<SpecialtyInfo> getAllByAcademyId(Long academyId);

    public void insert(SpecialtyInfo specialtyInfo);

    public void update(SpecialtyInfo specialtyInfo);

    Pagination<SpecialtyVO> pageListVO(SpecialtyVO vo, BasePageParameter basePageParameter);
}
