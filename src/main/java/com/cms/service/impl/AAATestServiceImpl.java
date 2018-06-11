package com.cms.service.impl;

import com.cms.dao.StudentDao;
import com.cms.domain.StudentInfo;
import com.cms.mapper.StudentMapper;
import com.cms.service.AAATestService;
import com.cms.vo.BasePageParameter;
import com.cms.vo.StudentVO;
import com.core.page.Pagination;
import org.springframework.stereotype.Service;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/19 17:13
 */
@Service
public class AAATestServiceImpl extends BaseServiceImpl<StudentInfo, StudentVO, StudentMapper, StudentDao> implements AAATestService {

    @Override
    public Integer testFunction() {
        return 67;
    }

    @Override
    public Pagination<StudentVO> pageListVO(StudentVO studentVO, BasePageParameter basePageParameter) {
        return null;
    }
}
