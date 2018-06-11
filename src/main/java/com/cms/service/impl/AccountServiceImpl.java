package com.cms.service.impl;

import com.cms.dao.StudentDao;
import com.cms.dao.TeacherDao;
import com.cms.domain.StudentInfo;
import com.cms.domain.TeacherInfo;
import com.cms.service.AccountService;
import com.cms.vo.StudentVO;
import com.cms.vo.TeacherVO;
import com.core.dao.model.BaseEntity;
import com.core.dao.query.FilterMap;
import com.core.util.EncryptionUtil;
import com.core.util.enums.ConstantEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/2 9:14
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    StudentDao studentDao;

    @Autowired
    TeacherDao teacherDao;
    /**
     *
     * @param account login account
     * @param password login password
     * @param type login for 0-student 1-teacher
     * @return
     */
    @Override
    public BaseEntity login(String account, String password, int type) {
        FilterMap map=new FilterMap();
        map.eq("account",account);
        //map.eq("password", EncryptionUtil.encryptMD5(password));
        if (type == 0){
            try {
                return studentDao.findOne(map);
            } catch (Exception e) {
                return null;
            }
        }else {
            try{
                return teacherDao.findOne(map);
            }catch (Exception e){
                return null;
            }
        }

    }

    @Override
    public TeacherVO getDetailTeacher(String account) {
        if (StringUtils.isNotBlank(account))
            return teacherDao.getDetail(account);
        else
            return null;
    }

    @Override
    public StudentVO getDetailStudent(String account) {
        if (StringUtils.isNotBlank(account))
            return studentDao.getDetail(account);
        else
            return null;
    }
}
