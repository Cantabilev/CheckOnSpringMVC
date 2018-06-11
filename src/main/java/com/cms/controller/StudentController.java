package com.cms.controller;

import com.cms.dao.ClassDao;
import com.cms.dao.StudentDao;
import com.cms.domain.*;
import com.cms.service.*;
import com.cms.vo.BasePageParameter;
import com.cms.vo.StudentVO;
import com.cms.vo.TeacherVO;
import com.core.dao.query.FilterMap;
import com.core.exception.MyException;
import com.core.page.Pagination;
import com.core.util.EncryptionUtil;
import com.core.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileFilter;
import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/9 20:52
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    StudentService studentService;

    // TODO 改进为分接口操作
    @Resource
    AcademyService academyService;

    @Resource
    GradeService gradeService;
    @Resource
    SpecialtyService specialtyService;
    @Resource
    ClassService classService;
    @Resource
    StudentDao studentDao;
    @Resource
    ClassDao classDao;

    @RequestMapping(value = "/getPageList")
    public ModelAndView getPageList(StudentVO studentVO, BasePageParameter parm){
        ModelAndView mv=new ModelAndView("checkcms/studentList");
        Pagination<StudentVO> page = studentService.pageListVO(studentVO,parm);

        List<AcademyInfo> academyInfos = academyService.getAllAcademy();

        mv.addObject("pagination",page);
        mv.addObject("cur_nav", "student");
        mv.addObject("name", studentVO.getName());
        mv.addObject("account", studentVO.getAccount());
        mv.addObject("state", studentVO.getState());
        mv.addObject("academyList", academyInfos);
        mv.addObject("academyId", studentVO.getAcademyId());
        return mv;
    }

    @RequestMapping(value = "/updateState")
    public ModelAndView updateState(Long id ,Integer state){
        ModelAndView mv=new ModelAndView("redirect:/student/getPageList.html");
        if(id!=null){
            studentService.offOrOn(id, state);
        }
        return mv;
    }

    @RequestMapping(value = "toMerge")
    public ModelAndView toMerge(Long id){
        ModelAndView mv = new ModelAndView("checkcms/studentMerge");

        List<GradeInfo> gradeInfos = gradeService.getAllGrade();
        List<AcademyInfo> academyInfos = academyService.getAllAcademy();
        List<SpecialtyInfo> specialtyInfos = specialtyService.getAll();
        List<ClassInfo> classInfos = classService.getAll();

        StudentInfo info = new StudentInfo();
        if (id != null) {
            info = studentService.findOne(id);
            mv.addObject("gradeList", gradeInfos);
            mv.addObject("academyList", academyInfos);
            mv.addObject("specialtyList", specialtyInfos);
            mv.addObject("classList", classInfos);
        }
        mv.addObject("entity",info);
        return mv;
    }

    @RequestMapping(value = "/doMerge")
    public ModelAndView doMerge(StudentInfo studentInfo){

        if (studentInfo.getPassword().length() > 16)// 如果大于16位  则未修改 密码控制在16位以下
            studentInfo.setPassword(null);
        else
            studentInfo.setPassword(EncryptionUtil.encryptMD5(studentInfo.getPassword()));

        Long gradeId = studentInfo.getGradeId();
        Long classId = studentInfo.getClassId();

        FilterMap map = new FilterMap();
        map.eq("grade_id", gradeId);
        map.eq("class_id", classId);
        Integer count = studentDao.count(map);
        if (count >= 99){
            ModelAndView mv = new ModelAndView("common/failure");
            String failureMsg = "班级容纳已经上限，请选择其他班级";
            mv.addObject("failureMsg", failureMsg);
            return mv;
        }

        if(studentInfo.getId()==null){

            try {
                String prefix = StringUtil.formatLimitGrade(studentInfo.getGradeId());
                String suffix = StringUtil.formatLimit2(count+1);
                ClassInfo classInfo = classDao.findOne(studentInfo.getClassId());
                studentInfo.setAccount(prefix + classInfo.getClassCode() + suffix);
                studentInfo.setPassword(EncryptionUtil.encryptMD5(studentInfo.getPassword()));
                studentService.insert(studentInfo);
            } catch (MyException e) {
                e.printStackTrace();
                ModelAndView mv = new ModelAndView("common/failure");
                String failureMsg = e.getConstantEnum().getMsg();
                mv.addObject("failureMsg", failureMsg);
                return mv;
            }
        }else{
            studentService.update(studentInfo);
        }

        ModelAndView mv=new ModelAndView("redirect:/student/getPageList.html");
        return mv;
    }
}
