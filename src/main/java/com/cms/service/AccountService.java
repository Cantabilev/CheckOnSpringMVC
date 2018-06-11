package com.cms.service;

import com.cms.vo.StudentVO;
import com.cms.vo.TeacherVO;
import com.core.dao.model.BaseEntity;
import com.core.util.enums.ConstantEnum;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/2 9:11
 */
public interface AccountService {
    /**
     *
     * @param account login account
     * @param password login password
     * @param type login for 0-student 1-teacher
     * @return login state is Success
     */
    public BaseEntity login(String account, String password, int type);

    public TeacherVO getDetailTeacher(String account);

    public StudentVO getDetailStudent(String account);
}
