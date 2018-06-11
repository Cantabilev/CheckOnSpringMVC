package com.cms.service;

import com.cms.domain.ClassInfo;
import com.cms.domain.SpecialtyInfo;
import com.cms.vo.BasePageParameter;
import com.cms.vo.ClassVO;
import com.cms.vo.SpecialtyVO;
import com.core.page.Pagination;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/9 23:38
 */
public interface ClassService {

    public ClassInfo findOne(Long id);

    public void insert(ClassInfo classInfo);

    public void update(ClassInfo classInfo);

    Pagination<ClassVO> pageListVO(ClassVO vo, BasePageParameter basePageParameter);

    public List<ClassInfo> getAll();

    public List<ClassInfo> getAllByGradeIdAndSpecialtyId(Long gradeId, Long specialtyId);
}
