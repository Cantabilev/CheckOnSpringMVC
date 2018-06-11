package com.cms.controller;

import com.alibaba.fastjson.JSONObject;
import com.cms.domain.AcademyInfo;
import com.cms.domain.SpecialtyInfo;
import com.cms.service.AcademyService;
import com.cms.service.SpecialtyService;
import com.cms.vo.BasePageParameter;
import com.cms.vo.SpecialtyVO;
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
@RequestMapping("/specialty")
public class SpecialtyController {

    @Resource
    AcademyService academyService;

    @Resource
    SpecialtyService specialtyService;

    @RequestMapping(value = "/getPageList")
    public ModelAndView getPageList(SpecialtyVO specialtyVO, BasePageParameter parm){
        ModelAndView mv=new ModelAndView("checkcms/specialtyList");
        Pagination<SpecialtyVO> page = specialtyService.pageListVO(specialtyVO, parm);

        List<AcademyInfo> academyInfos = academyService.getAllAcademy();

        mv.addObject("pagination",page);
        mv.addObject("cur_nav", "specialty");
        mv.addObject("specialtyCode", specialtyVO.getSpecialtyCode());
        mv.addObject("academyList", academyInfos);
        mv.addObject("academyId", specialtyVO.getAcademyId());
        return mv;
    }

    @RequestMapping(value = "toMerge")
    public ModelAndView toMerge(Long id){
        ModelAndView mv = new ModelAndView("checkcms/specialtyMerge");

        List<AcademyInfo> academyInfos = academyService.getAllAcademy();

        SpecialtyInfo info = new SpecialtyInfo();
        if (id != null) {
            info = specialtyService.findOne(id);
        }
        mv.addObject("academyList", academyInfos);
        mv.addObject("entity",info);
        return mv;
    }

    @RequestMapping(value = "/doMerge")
    public ModelAndView doMerge(SpecialtyInfo specialtyInfo){

        if(specialtyInfo.getId() == null){
            specialtyService.insert(specialtyInfo);
        }else{
            specialtyService.update(specialtyInfo);
        }

        ModelAndView mv=new ModelAndView("redirect:/specialty/getPageList.html");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/getSpecialtyByAcademyId", method = RequestMethod.GET)
    public JSONObject getSpecialtyByAcademyId(Long academyId){
        if (null == academyId)
            return ResTool.resp(ConstantEnum.PARAM_NULL);
        List<SpecialtyInfo> specialtyInfos = specialtyService.getAllByAcademyId(academyId);
        return ResTool.resp(ConstantEnum.OK, specialtyInfos);
    }
}
