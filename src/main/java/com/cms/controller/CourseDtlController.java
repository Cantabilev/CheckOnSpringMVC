package com.cms.controller;

import com.alibaba.fastjson.JSONObject;
import com.cms.domain.*;
import com.cms.dto.CourseCodeDTO;
import com.cms.service.*;
import com.cms.util.BeanUtil;
import com.cms.vo.BasePageParameter;
import com.cms.vo.CourseDtlVO;
import com.cms.vo.CourseVO;
import com.core.exception.MyException;
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
 * @date 2018/3/30 13:55
 *
 * 课程调度 Controller
 */
@Controller
@RequestMapping("/courseDtl")
public class CourseDtlController {

    @Resource
    CourseDtlService courseDtlService;

    @Resource
    CourseCodeService courseCodeService;

    @Resource
    CourseService courseService;

    @Resource
    AcademyService academyService;

    @Resource
    SpecialtyService specialtyService;


    @RequestMapping(value = "/getPageList")
    public ModelAndView getPageList(CourseDtlVO courseVO, BasePageParameter parm){
        ModelAndView mv=new ModelAndView("checkcms/courseDtlList");

        List<SpecialtyInfo> specialtyInfos = specialtyService.getAll();

        Pagination<CourseDtlVO> page = courseDtlService.pageListVO(courseVO,parm);

        for(CourseDtlVO info : page.getItems()){
            if(info.getDate()!=null){
                info.setDateStr(DateUtil2.timeToString_yyyy_MM_dd(info.getDate()));
            }
        }

        mv.addObject("pagination",page);
        mv.addObject("cur_nav", "courseDtl");
        mv.addObject("specialtyList", specialtyInfos);
        mv.addObject("courseCode", courseVO.getCourseCode());
        mv.addObject("teacherAccount", courseVO.getTeacherAccount());
        mv.addObject("specialtyId", courseVO.getSpecialtyId());
        mv.addObject("state", courseVO.getState());
        return mv;
    }

    @RequestMapping(value = "/updateState")
    public ModelAndView updateState(Long id ,Integer state){
        ModelAndView mv=new ModelAndView("redirect:/courseDtl/getPageList.html");
        if(id!=null){
            courseDtlService.offOrOn(id, state);
        }
        return mv;
    }

    @RequestMapping(value = "toMerge")
    public ModelAndView toMerge(Long id){
        ModelAndView mv = new ModelAndView("checkcms/courseDtlMerge");

        List<CourseCodeInfo> courseCodeInfos = courseCodeService.getAll();

        CourseDtlVO info = new CourseDtlVO();
        if (id != null) {
            info = courseDtlService.findOneVO(id);
            if(info.getDate()!=null){
                info.setDateStr(DateUtil2.timeToString_yyyy_MM_dd(info.getDate()));
            }
        }
        mv.addObject("courseCodeList", courseCodeInfos);
        mv.addObject("entity",info);
        return mv;
    }

    @RequestMapping(value = "/doMerge")
    public ModelAndView doMerge(CourseDtlVO vo){
        CourseDtlInfo info;

        if(vo.getId()==null){
            CourseCodeInfo courseInfo = courseCodeService.findOne(vo.getCourseCode());
            info = BeanUtil.copyProperties(courseInfo, CourseDtlInfo.class);
            info.setPlace(vo.getPlace());
            info.setDate(DateUtil2.stringToTime(vo.getDateStr(), DateUtil2.yyyy_MM_dd));
            info.setScheduleName(vo.getScheduleName());
            info.setState(vo.getState());

            courseDtlService.insert(info);
        }else{
            info = courseDtlService.findOne(vo.getId());
            info.setPlace(vo.getPlace());
            info.setDate(DateUtil2.stringToTime(vo.getDateStr(), DateUtil2.yyyy_MM_dd));
            info.setScheduleName(vo.getScheduleName());
            courseDtlService.update(info);
        }

        ModelAndView mv=new ModelAndView("redirect:/courseDtl/getPageList.html");
        return mv;
    }
}
