package com.client.controller;

import com.client.service.ClockingDtlService;
import com.cms.dto.ClockingDTO;
import com.cms.vo.BasePageParameter;
import com.cms.vo.ClockingDtlVO;
import com.core.page.Pagination;
import com.core.util.DateUtil2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/21 20:58
 */
@Controller
@RequestMapping("/clockingDtl")
public class ClockingDtlController {

    @Resource
    ClockingDtlService clockingDtlService;

    @RequestMapping(value = "/getPageList")
    public ModelAndView getPageList(ClockingDtlVO vo, BasePageParameter parm){
        ModelAndView mv=new ModelAndView("checkcms/clockingDtlList");

        Pagination<ClockingDtlVO> page = clockingDtlService.pageListVO(vo,parm);

        for(ClockingDtlVO info : page.getItems()){

            if(info.getSubmitTime()!=null){
                info.setSubmitTimeStr(DateUtil2.timeToString_yyyy_MM_dd_HH_mm(info.getSubmitTime()));
            }
        }

        mv.addObject("pagination",page);
        mv.addObject("cur_nav", "clockingDtl");
        mv.addObject("clockingId", vo.getClockingId());
        mv.addObject("courseCode", vo.getCourseCode());
        mv.addObject("teacherAccount", vo.getTeacherAccount());
        mv.addObject("state", vo.getState());
        return mv;
    }

    @RequestMapping(value = "/updateState")
    public ModelAndView updateState(Long id ,Integer state){
        ModelAndView mv=new ModelAndView("redirect:/clockingDtl/getPageList.html");
        if(id!=null){
            clockingDtlService.offOrOn(id, state);
        }
        return mv;
    }
}
