package com.cms.controller;

import com.alibaba.fastjson.JSON;
import com.cms.service.AAATestService;
import com.cms.vo.MsgVO;
import com.core.spring.JsonView;
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
 * @date 2018/3/22 21:55
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    AAATestService aaaTestService;

    @ResponseBody
    @RequestMapping(value = "/testGet", method = RequestMethod.GET)
    public String testGet(HttpSession session, String testGet) {
        MsgVO msg=new MsgVO();
        msg.setCode(0);
        msg.setMsg(String.valueOf(testGet));
        return JSON.toJSONString(msg);
    }
    @ResponseBody
    @RequestMapping(value = "/testPost", method = RequestMethod.POST)
    public String testPost(HttpSession session, String testPost) {
        MsgVO msg=new MsgVO();
        msg.setCode(1);
        msg.setMsg(String.valueOf(testPost));
        return JSON.toJSONString(msg);
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(HttpSession session, String str, Long id) {
        JsonView msg=new JsonView();
        msg.setCode(aaaTestService.testFunction());
        msg.setMsg(String.valueOf(str));
        msg.setData(aaaTestService.findOne(id));

        return JSON.toJSONString(msg);
    }
}
