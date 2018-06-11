package com.cms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cms.domain.AcademicYearInfo;
import com.cms.domain.AcademyInfo;
import com.cms.domain.StudentInfo;
import com.cms.service.AcademicYearService;
import com.cms.vo.BasePageParameter;
import com.cms.vo.StudentVO;
import com.core.page.Pagination;
import com.core.util.DateUtil2;
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
 * @date 2018/4/20 14:54
 */
@Controller
@RequestMapping(value = "/academicYear")
public class AcademicYearController {

    @Resource
    AcademicYearService academicYearService;

    @ResponseBody
    @RequestMapping(value = "/getAllAcademicYear", method = RequestMethod.GET)
    public JSONObject getAllAcademicYear() {
        try {
            return ResTool.resp(ConstantEnum.OK, academicYearService.getAcademicYearList());
        } catch (Exception e) {
            return ResTool.resp(ConstantEnum.SYS_ERROR);
        }
    }

    @RequestMapping(value = "/getPageList")
    public ModelAndView getPageList(AcademicYearInfo academicYearInfo, BasePageParameter parm){
        ModelAndView mv=new ModelAndView("checkcms/academicYearList");
        Pagination<AcademicYearInfo> page = academicYearService.pageList(academicYearInfo,parm);

        for(AcademicYearInfo info : page.getItems()){
            if(info.getSemesterOneStart()!=null){
                info.setSemesterOneStartStr(DateUtil2.timeToString_yyyy_MM_dd(info.getSemesterOneStart()));
            }
            if(info.getSemesterTwoStart()!=null){
                info.setSemesterTwoStartStr(DateUtil2.timeToString_yyyy_MM_dd(info.getSemesterTwoStart()));
            }
            if(info.getSemesterOneEnd()!=null){
                info.setSemesterOneEndStr(DateUtil2.timeToString_yyyy_MM_dd(info.getSemesterOneEnd()));
            }
            if(info.getSemesterTwoEnd()!=null){
                info.setSemesterTwoEndStr(DateUtil2.timeToString_yyyy_MM_dd(info.getSemesterTwoEnd()));
            }
        }
        mv.addObject("pagination",page);
        mv.addObject("cur_nav", "academicYear");
        return mv;
    }

    @RequestMapping(value = "toMerge")
    public ModelAndView toMerge(Long id){
        ModelAndView mv = new ModelAndView("checkcms/academicYearMerge");

        AcademicYearInfo info = new AcademicYearInfo();
        if (id != null) {
            info = academicYearService.findOne(id);
            if(info.getSemesterOneStart()!=null){
                info.setSemesterOneStartStr(DateUtil2.timeToString_yyyy_MM_dd(info.getSemesterOneStart()));
            }
            if(info.getSemesterTwoStart()!=null){
                info.setSemesterTwoStartStr(DateUtil2.timeToString_yyyy_MM_dd(info.getSemesterTwoStart()));
            }
            if(info.getSemesterOneEnd()!=null){
                info.setSemesterOneEndStr(DateUtil2.timeToString_yyyy_MM_dd(info.getSemesterOneEnd()));
            }
            if(info.getSemesterTwoEnd()!=null){
                info.setSemesterTwoEndStr(DateUtil2.timeToString_yyyy_MM_dd(info.getSemesterTwoEnd()));
            }
        }
        mv.addObject("entity",info);
        return mv;
    }

    @RequestMapping(value = "/doMerge")
    public ModelAndView doMerge(AcademicYearInfo academicYearInfo){

        if(academicYearInfo.getSemesterOneStartStr()!=null){
            academicYearInfo.setSemesterOneStart(DateUtil2.stringToTime(academicYearInfo.getSemesterOneStartStr(), DateUtil2.yyyy_MM_dd));
        }
        if(academicYearInfo.getSemesterTwoStartStr()!=null){
            academicYearInfo.setSemesterTwoStart(DateUtil2.stringToTime(academicYearInfo.getSemesterTwoStartStr(), DateUtil2.yyyy_MM_dd));
        }
        if(academicYearInfo.getSemesterOneEndStr()!=null){
            academicYearInfo.setSemesterOneEnd(DateUtil2.stringToTime(academicYearInfo.getSemesterOneEndStr(), DateUtil2.yyyy_MM_dd));
        }
        if(academicYearInfo.getSemesterTwoEndStr()!=null){
            academicYearInfo.setSemesterTwoEnd(DateUtil2.stringToTime(academicYearInfo.getSemesterTwoEndStr(), DateUtil2.yyyy_MM_dd));
        }

        if(academicYearInfo.getId()==null){
            academicYearService.insert(academicYearInfo);
        }else{
            academicYearService.update(academicYearInfo);
        }

        ModelAndView mv=new ModelAndView("redirect:/academicYear/getPageList.html");
        return mv;
    }
}
