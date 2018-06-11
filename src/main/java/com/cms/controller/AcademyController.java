package com.cms.controller;

import com.alibaba.fastjson.JSONObject;
import com.cms.domain.AcademyInfo;
import com.cms.service.AcademyService;
import com.cms.vo.BasePageParameter;
import com.core.page.Pagination;
import com.core.util.ResTool;
import com.core.util.enums.ConstantEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/9 23:49
 */
@Controller
@RequestMapping("/academy")
public class AcademyController {

    @Resource
    AcademyService academyService;

    @ResponseBody
    @RequestMapping(value = "/getAllAcademy", method = RequestMethod.GET)
    public JSONObject getAllAcademy(){
        List<AcademyInfo> list = academyService.getAllAcademy();
        return ResTool.resp(ConstantEnum.OK, list);
    }

    @RequestMapping(value = "/getPageList")
    public ModelAndView getPageList(AcademyInfo academyInfo, BasePageParameter parm){
        ModelAndView mv=new ModelAndView("checkcms/academyList");
        Pagination<AcademyInfo> page = academyService.pageList(academyInfo, parm);

        List<AcademyInfo> academyInfos = academyService.getAllAcademy();

        mv.addObject("pagination",page);
        mv.addObject("cur_nav", "academy");
        return mv;
    }

    @RequestMapping(value = "toMerge")
    public ModelAndView toMerge(Long id){
        ModelAndView mv = new ModelAndView("checkcms/academyMerge");

        AcademyInfo info = new AcademyInfo();
        if (id != null) {
            info = academyService.findOne(id);
        }
        mv.addObject("entity",info);
        return mv;
    }

    @RequestMapping(value = "/doMerge")
    public ModelAndView doMerge(AcademyInfo academyInfo){

        if(academyInfo.getId() == null){
            academyService.insert(academyInfo);
        }else{
            academyService.update(academyInfo);
        }

        ModelAndView mv=new ModelAndView("redirect:/academy/getPageList.html");
        return mv;
    }
}
