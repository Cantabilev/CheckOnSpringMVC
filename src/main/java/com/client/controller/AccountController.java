package com.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.cms.domain.StudentInfo;
import com.cms.domain.TeacherInfo;
import com.cms.service.AccountService;
import com.cms.vo.StudentVO;
import com.cms.vo.TeacherVO;
import com.core.dao.model.BaseEntity;
import com.core.util.EncryptionUtil;
import com.core.util.ResTool;
import com.core.util.enums.ConstantEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/3/30 13:56
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Resource
    AccountService accountService;

    // TODO 检测是否封禁
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject login(String account, String password, int type) {

        BaseEntity entity = accountService.login(account, password, type);
        if (type == 0 && entity instanceof StudentInfo) {
            try {
                StudentInfo resultInfo = (StudentInfo) entity;
                if (resultInfo.getPassword().equals(EncryptionUtil.encryptMD5(password)))
                    return ResTool.resp(ConstantEnum.OK);
                else
                    return ResTool.resp(ConstantEnum.LOGIN_FAIL_PASSWORD);
            } catch (Exception e) {
                return ResTool.resp(ConstantEnum.FAIL);
            }
        } else if (type == 1 && entity instanceof TeacherInfo) {
            try {
                TeacherInfo resultInfo = (TeacherInfo) entity;
                if (resultInfo.getPassword().equals(EncryptionUtil.encryptMD5(password)))
                    return ResTool.resp(ConstantEnum.OK);
                else
                    return ResTool.resp(ConstantEnum.LOGIN_FAIL_PASSWORD);
            } catch (Exception e) {
                return ResTool.resp(ConstantEnum.FAIL);
            }
        }
        return ResTool.resp(ConstantEnum.LOGIN_FAIL_TYPE);
    }

    @ResponseBody
    @RequestMapping(value = "/getDetailTeacher", method = RequestMethod.GET)
    public JSONObject getDetailTeacher(String account) {
        TeacherVO vo = accountService.getDetailTeacher(account);
        if (null != vo)
            return ResTool.resp(ConstantEnum.OK, vo);
        else
            return ResTool.resp(ConstantEnum.FAIL);
    }

    @ResponseBody
    @RequestMapping(value = "/getDetailStudent", method = RequestMethod.GET)
    public JSONObject getDetailStudent(String account) {
        StudentVO vo = accountService.getDetailStudent(account);
        if (null != vo)
            return ResTool.resp(ConstantEnum.OK, vo);
        else
            return ResTool.resp(ConstantEnum.FAIL);
    }
}
