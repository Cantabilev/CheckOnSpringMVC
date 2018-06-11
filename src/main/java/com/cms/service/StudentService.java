package com.cms.service;

import com.cms.domain.StudentInfo;
import com.cms.domain.TeacherInfo;
import com.cms.vo.BasePageParameter;
import com.cms.vo.StudentVO;
import com.cms.vo.TeacherVO;
import com.core.page.Pagination;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/9 20:57
 */
public interface StudentService {

    public Pagination<StudentVO> pageListVO(StudentVO vo, BasePageParameter basePageParameter);

    void offOrOn(Long id, Integer state);

    StudentInfo findOne(Long id);

    StudentInfo findOne(String account);

    void insert(StudentInfo student);

    void update(StudentInfo student);
}
