package com.cms.controller;

import com.alibaba.fastjson.JSON;
import com.cms.domain.AdminInfo;
import com.cms.service.AdminInfoService;
import com.cms.vo.MsgVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/3/20 13:42
 */
@Controller
@RequestMapping("/adminInfo")
public class AdminInfoController {

    @Resource
    AdminInfoService adminInfoService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, AdminInfo accountInfo, String captcha) {
        MsgVO msg=new MsgVO();
        if(!captcha.equalsIgnoreCase((String)session.getAttribute("randomString"))){
            msg.setCode(-2);
            msg.setMsg("验证码错误");
            JSON.toJSON(msg);
            return JSON.toJSONString(msg);
        }
        if (adminInfoService.login(accountInfo)) {
            msg.setCode(0);
        }else{
            msg.setCode(-1);
            msg.setMsg("Error");
        }
        return JSON.toJSONString(msg);
    }

    /**
     * 退出系统
     * @param session
     *          Session
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value="/logout")
    public String logout(HttpSession session) throws Exception{
        MsgVO msg=new MsgVO();
        //清除Session
        session.invalidate();
        msg.setCode(0);
        msg.setMsg("退出成功");
        return JSON.toJSONString(msg);
    }
}
