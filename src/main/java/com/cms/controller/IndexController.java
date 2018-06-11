package com.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/3/21 23:02
 */
@Controller
@RequestMapping()
public class IndexController {
    @RequestMapping(value = "/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
}
