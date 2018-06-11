package com.cms.service.impl;

import com.cms.dao.GradeDao;
import com.cms.domain.GradeInfo;
import com.cms.service.GradeService;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/16 17:32
 */
@Service
public class GradeServiceImpl implements GradeService {

    @Resource
    GradeDao gradeDao;

    @Override
    public List<GradeInfo> getAllGrade() {
        List<GradeInfo> list = gradeDao.list(new FilterMap(), new OrderMap());
        return list;
    }
}
