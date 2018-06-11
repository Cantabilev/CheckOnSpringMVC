package com.cms.controller;

import com.cms.domain.AcademyInfo;
import com.cms.domain.TeacherInfo;
import com.cms.service.AcademyService;
import com.cms.service.TeacherService;
import com.cms.util.BeanUtil;
import com.cms.vo.BasePageParameter;
import com.cms.vo.TeacherVO;
import com.core.page.Pagination;
import com.core.util.EncryptionUtil;
import com.core.util.enums.ConstantEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/9 20:52
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    TeacherService teacherService;

    // TODO 改进为分接口操作
    @Resource
    AcademyService academyService;

    @RequestMapping(value = "/getPageList")
    public ModelAndView getPageList(TeacherVO teacherVO, BasePageParameter parm){
        ModelAndView mv=new ModelAndView("checkcms/teacherList");
        Pagination<TeacherVO> page = teacherService.pageListVO(teacherVO,parm);

        List<AcademyInfo> academyInfos = academyService.getAllAcademy();

        mv.addObject("pagination",page);
        mv.addObject("cur_nav", "teacher");
        mv.addObject("name", teacherVO.getName());
        mv.addObject("account", teacherVO.getAccount());
        mv.addObject("state", teacherVO.getState());
        mv.addObject("academyList", academyInfos);
        mv.addObject("academyId", teacherVO.getAcademyId());
        return mv;
    }

    @RequestMapping(value = "/updateState")
    public ModelAndView updateState(Long id ,Integer state){
        ModelAndView mv=new ModelAndView("redirect:/teacher/getPageList.html");
        if(id!=null){
            teacherService.offOrOn(id, state);
        }
        return mv;
    }

    @RequestMapping(value = "toMerge")
    public ModelAndView toMerge(Long id){
        ModelAndView mv = new ModelAndView("checkcms/teacherMerge");

        List<AcademyInfo> academyInfos = academyService.getAllAcademy();

        TeacherInfo info = new TeacherInfo();
        if (id != null) {
            info = teacherService.findOne(id);
        }
        mv.addObject("academyList", academyInfos);
        mv.addObject("entity",info);
        return mv;
    }

    @RequestMapping(value = "/doMerge")
    public ModelAndView doMerge(TeacherInfo teacherInfo){

        if (teacherInfo.getPassword().length() > 16)// 如果大于16位  则未修改 密码控制在16位以下
            teacherInfo.setPassword(null);
        else
            teacherInfo.setPassword(EncryptionUtil.encryptMD5(teacherInfo.getPassword()));

        if(teacherInfo.getId()==null){
            teacherService.insert(teacherInfo);
        }else{
            teacherService.update(teacherInfo);
        }

        ModelAndView mv=new ModelAndView("redirect:/teacher/getPageList.html");
        return mv;
    }
}
