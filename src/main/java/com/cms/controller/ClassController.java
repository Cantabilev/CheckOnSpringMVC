package com.cms.controller;

import com.alibaba.fastjson.JSONObject;
import com.cms.dao.ClassDao;
import com.cms.domain.AcademyInfo;
import com.cms.domain.ClassInfo;
import com.cms.domain.GradeInfo;
import com.cms.domain.SpecialtyInfo;
import com.cms.service.AcademyService;
import com.cms.service.ClassService;
import com.cms.service.GradeService;
import com.cms.service.SpecialtyService;
import com.cms.vo.BasePageParameter;
import com.cms.vo.ClassVO;
import com.cms.vo.SpecialtyVO;
import com.core.dao.query.FilterMap;
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
 * @date 2018/5/16 15:58
 */
@Controller
@RequestMapping(value = "/class")
public class ClassController {

    @Resource
    AcademyService academyService;

    @Resource
    SpecialtyService specialtyService;

    @Resource
    GradeService gradeService;

    @Resource
    ClassService classService;

    @Resource
    ClassDao classDao;

    @RequestMapping(value = "/getPageList")
    public ModelAndView getPageList(ClassVO vo, BasePageParameter parm){
        ModelAndView mv=new ModelAndView("checkcms/classList");
        Pagination<ClassVO> page = classService.pageListVO(vo, parm);

        List<AcademyInfo> academyInfos = academyService.getAllAcademy();

        mv.addObject("pagination",page);
        mv.addObject("cur_nav", "class");
        mv.addObject("specialtyCode", vo.getSpecialtyCode());
        mv.addObject("classCode", vo.getClassCode());
        mv.addObject("academyList", academyInfos);
        mv.addObject("academyId", vo.getAcademyId());
        return mv;
    }

    @RequestMapping(value = "toMerge")
    public ModelAndView toMerge(Long id){
        ModelAndView mv = new ModelAndView("checkcms/classMerge");

        List<SpecialtyInfo> specialtyInfos = specialtyService.getAll();
        List<GradeInfo> gradeInfos = gradeService.getAllGrade();

        ClassInfo info = new ClassInfo();
        if (id != null) {
            info = classService.findOne(id);
        }

        mv.addObject("gradeList", gradeInfos);
        mv.addObject("specialtyList", specialtyInfos);
        mv.addObject("entity",info);
        return mv;
    }

    @RequestMapping(value = "/doMerge")
    public ModelAndView doMerge(ClassInfo info){

        if(info.getId() == null){

            SpecialtyInfo specialtyInfo = specialtyService.findOne(info.getSpecialtyId());

            FilterMap map = new FilterMap();
            map.eq("grade_id", info.getGradeId());
            map.eq("specialty_id", info.getSpecialtyId());
            map.eq("class_code", specialtyInfo.getSpecialtyCode()+info.getClassCode());
            ClassInfo classInfo = classDao.findOne(map);
            if (classInfo == null){
                info.setClassCode(specialtyInfo.getSpecialtyCode()+info.getClassCode());
                classService.insert(info);
            }else {
                ModelAndView mv = new ModelAndView("common/failure");
                String failureMsg = "该班级已经存在！";
                mv.addObject("failureMsg", failureMsg);
                return mv;
            }

        }

        ModelAndView mv=new ModelAndView("redirect:/class/getPageList.html");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/getClassByGradeIdAndSpecialtyId", method = RequestMethod.GET)
    public JSONObject getClassByGradeIdAndSpecialtyId(Long gradeId, Long specialtyId){
        if (null == specialtyId)
            return ResTool.resp(ConstantEnum.PARAM_NULL);

        List<ClassInfo> classInfos = classService.getAllByGradeIdAndSpecialtyId(gradeId, specialtyId);
        return ResTool.resp(ConstantEnum.OK, classInfos);
    }
}
