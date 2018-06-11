package com.cms.controller;

import com.cms.domain.*;
import com.cms.service.*;
import com.cms.vo.BasePageParameter;
import com.core.dao.query.FilterMap;
import com.core.exception.MyException;
import com.core.page.Pagination;
import com.core.util.EncryptionUtil;
import com.core.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/20 13:39
 */
@Controller
@RequestMapping(value = "/courseCode")
public class CourseCodeController {

    @Resource
    CourseCodeService courseCodeService;

    @Resource
    ClassService classService;

    @Resource
    StudentService studentService;

    @Resource
    SpecialtyService specialtyService;

    @Resource
    TeacherService teacherService;

    @Resource
    CourseService courseService;

    @Resource
    GradeService gradeService;

    @Resource
    AcademicYearService academicYearService;

    @RequestMapping(value = "/getPageList")
    public ModelAndView getPageList(CourseCodeInfo courseCodeInfo, BasePageParameter parm){
        ModelAndView mv = new ModelAndView("checkcms/courseCodeList");
        Pagination<CourseCodeInfo> page = courseCodeService.pageListVO(courseCodeInfo,parm);

        List<SpecialtyInfo> specialtyInfos = specialtyService.getAll();

        mv.addObject("pagination",page);
        mv.addObject("cur_nav", "courseCode");
        mv.addObject("courseCode", courseCodeInfo.getCourseCode());
        mv.addObject("teacherAccount", courseCodeInfo.getTeacherAccount());
        mv.addObject("specialtyList", specialtyInfos);
        mv.addObject("specialtyId", courseCodeInfo.getSpecialtyId());
        mv.addObject("state", courseCodeInfo.getState());

        return mv;
    }

    @RequestMapping(value = "/updateState")
    public ModelAndView updateState(Long courseCode ,Integer state){
        ModelAndView mv=new ModelAndView("redirect:/courseCode/getPageList.html");
        if(courseCode!=null){
            courseCodeService.offOrOn(courseCode, state);
        }
        return mv;
    }

    @RequestMapping(value = "/updateClass")
    public ModelAndView updateClass(Integer action, Long courseCode, Long classIds){
        ModelAndView mv = new ModelAndView("checkcms/courseCodeMerge");
        CourseCodeInfo courseCodeInfo = courseCodeService.findOne(courseCode);
        //通过前端传来的教练手机号查找教练
        ClassInfo classInfo = classService.findOne(classIds);
        if (classInfo == null) {
            mv.addObject("classMsg", "操作失败，班级不存在");
            mv.addObject("alreadyClasses", courseCodeInfo.getClassIds());
            mv.addObject("entity", courseCodeInfo);
            return mv;
        }

        String [] classes = courseCodeInfo.getClassIds().split(",");
        String alreadyClasses = "";
        if (action == 1) {//删除班级
            if (!Arrays.asList(classes).contains(classInfo.getId() + "")) {
                mv.addObject("classMsg", "删除失败，班级尚未添加");
                mv.addObject("alreadyClasses", courseCodeInfo.getClassIds());
                mv.addObject("entity", courseCodeInfo);
                return mv;
            }
            //删除班级id
            for (String _class : classes) {
                if (!_class.equals(classInfo.getId() + "") && StringUtils.isNotBlank(_class)) {
                    alreadyClasses = alreadyClasses + _class + ",";
                }
                courseCodeInfo.setClassIds(alreadyClasses);
                courseCodeService.update(courseCodeInfo);
            }
        } else{
            if (Arrays.asList(classes).contains(classInfo.getId() + "")) {
                mv.addObject("classMsg", "添加失败，班级已添加");
                mv.addObject("alreadyClasses", courseCodeInfo.getClassIds());
                mv.addObject("entity", courseCodeInfo);
                return mv;
            }
            alreadyClasses = courseCodeInfo.getClassIds() + classInfo.getId() + ",";
            courseCodeInfo.setClassIds(alreadyClasses);
            courseCodeService.update(courseCodeInfo);
        }

        mv.addObject("alreadyClasses", alreadyClasses);
        mv.addObject("entity", courseCodeInfo);
        return mv;
    }

    @RequestMapping(value = "/updateExportStudent")
    public ModelAndView updateExportStudent(Integer action, Long courseCode, Long exportStudent){
        ModelAndView mv = new ModelAndView("checkcms/courseCodeMerge");
        CourseCodeInfo courseCodeInfo = courseCodeService.findOne(courseCode);
        mv.addObject("alreadyClasses", courseCodeInfo.getClassIds());
        mv.addObject("alreadyContains", courseCodeInfo.getContains());
        //通过前端传来的学生号查找学生
        StudentInfo studentInfo = studentService.findOne(exportStudent);
        if (studentInfo == null) {
            mv.addObject("exportsMsg", "操作失败，学生不存在");
            mv.addObject("alreadyExports", courseCodeInfo.getExports());
            mv.addObject("entity", courseCodeInfo);
            return mv;
        }

        String [] exports = courseCodeInfo.getExports().split(",");
        String alreadyExports = "";
        if (action == 1) {//删除 排除 学生
            if (!Arrays.asList(exports).contains(studentInfo.getAccount() + "")) {
                mv.addObject("exportsMsg", "删除失败，学生不排除列表");
                mv.addObject("alreadyExports", courseCodeInfo.getExports());
                mv.addObject("entity", courseCodeInfo);
                return mv;
            }
            //删除 排除学生
            for (String e : exports) {
                if (!e.equals(studentInfo.getAccount() + "") && StringUtils.isNotBlank(e)) {
                    alreadyExports = alreadyExports + e + ",";
                }
                courseCodeInfo.setExports(alreadyExports);
                courseCodeService.update(courseCodeInfo);
            }
        } else{// 添加 排除学生
            if (Arrays.asList(exports).contains(studentInfo.getAccount() + "")
            || !Arrays.asList(courseCodeInfo.getClassIds().split(",")).contains(String.valueOf(studentInfo.getClassId()))) {
                mv.addObject("classMsg", "添加失败，学生已排除 或 不属于课程");
                mv.addObject("alreadyExports", courseCodeInfo.getExports());
                mv.addObject("entity", courseCodeInfo);
                return mv;
            }
            alreadyExports = courseCodeInfo.getExports() + studentInfo.getAccount() + ",";
            courseCodeInfo.setExports(alreadyExports);
            courseCodeService.update(courseCodeInfo);
        }

        mv.addObject("alreadyExports", alreadyExports);
        mv.addObject("entity", courseCodeInfo);
        return mv;
    }

    @RequestMapping(value = "/updateContainsStudent")
    public ModelAndView updateContainsStudent(Integer action, Long courseCode, Long containsStudent){
        ModelAndView mv = new ModelAndView("checkcms/courseCodeMerge");
        CourseCodeInfo courseCodeInfo = courseCodeService.findOne(courseCode);
        mv.addObject("alreadyClasses", courseCodeInfo.getClassIds());
        mv.addObject("alreadyExports", courseCodeInfo.getExports());
        //通过前端传来的学生号查找学生
        StudentInfo studentInfo = studentService.findOne(containsStudent);
        if (studentInfo == null) {
            mv.addObject("containsMsg", "操作失败，学生不存在");
            mv.addObject("alreadyContains", courseCodeInfo.getContains());
            mv.addObject("entity", courseCodeInfo);
            return mv;
        }

        String [] contains = courseCodeInfo.getExports().split(",");
        String alreadyContains = "";
        if (action == 1) {//删除 额外 学生
            if (!Arrays.asList(contains).contains(studentInfo.getAccount() + "")) {
                mv.addObject("exportsMsg", "删除失败，学生不在 额外 列表");
                mv.addObject("alreadyContains", courseCodeInfo.getContains());
                mv.addObject("entity", courseCodeInfo);
                return mv;
            }
            //删除 排除学生
            for (String e : contains) {
                if (!e.equals(studentInfo.getAccount() + "") && StringUtils.isNotBlank(e)) {
                    alreadyContains = alreadyContains + e + ",";
                }
                courseCodeInfo.setContains(alreadyContains);
                courseCodeService.update(courseCodeInfo);
            }
        } else{// 添加 额外 学生
            if (Arrays.asList(contains).contains(studentInfo.getAccount() + "")
                    || Arrays.asList(courseCodeInfo.getClassIds().split(",")).contains(String.valueOf(studentInfo.getClassId()))) {
                mv.addObject("classMsg", "添加失败，学生已添加 或 属于课程");
                mv.addObject("alreadyContains", courseCodeInfo.getContains());
                mv.addObject("entity", courseCodeInfo);
                return mv;
            }
            alreadyContains = courseCodeInfo.getContains() + studentInfo.getAccount() + ",";
            courseCodeInfo.setContains(alreadyContains);
            courseCodeService.update(courseCodeInfo);
        }

        mv.addObject("alreadyContains", alreadyContains);
        mv.addObject("entity", courseCodeInfo);
        return mv;
    }

    @RequestMapping(value = "toMerge")
    public ModelAndView toMerge(Long courseCode){
        ModelAndView mv = new ModelAndView("checkcms/courseCodeMerge");

        List<TeacherInfo> teacherInfos = teacherService.getAll();
        List<AcademicYearInfo> academicYearInfos = academicYearService.getAcademicYearList();
        List<CourseInfo> courseInfos = courseService.getAll();
        List<GradeInfo> gradeInfos = gradeService.getAllGrade();
        List<SpecialtyInfo> specialtyInfos = specialtyService.getAll();
        List<ClassInfo> classInfos = classService.getAll();

        CourseCodeInfo info = new CourseCodeInfo();
        if (courseCode != null) {
            info = courseCodeService.findOne(courseCode);
        }
        mv.addObject("teacherList", teacherInfos);
        mv.addObject("academicYearList", academicYearInfos);
        mv.addObject("courseList", courseInfos);
        mv.addObject("gradeList", gradeInfos);
        mv.addObject("specialtyList", specialtyInfos);
        mv.addObject("classList", classInfos);

        mv.addObject("alreadyClasses", info.getClassIds());
        mv.addObject("alreadyExports", info.getExports());
        mv.addObject("alreadyContains", info.getContains());

        mv.addObject("entity",info);
        return mv;
    }

    @RequestMapping(value = "/doMerge")
    public ModelAndView doMerge(CourseCodeInfo courseCodeInfo){

        TeacherInfo teacherInfo = teacherService.findOne(courseCodeInfo.getTeacherAccount());
        courseCodeInfo.setTeacherName(teacherInfo.getName());

        AcademicYearInfo academicYearInfo = academicYearService.findOne(courseCodeInfo.getAcademicYearId());
        courseCodeInfo.setAcademicYearName(academicYearInfo.getName());

        CourseInfo courseInfo = courseService.findOne(courseCodeInfo.getCourseId());
        courseCodeInfo.setCourseName(courseInfo.getName());


        courseCodeInfo.setExports(null);
        courseCodeInfo.setContains(null);

        if (courseCodeInfo.getCourseCode() == null) {
            courseCodeInfo.setClassIds(courseCodeInfo.getClassIds()+",");
            courseCodeService.insert(courseCodeInfo);
        } else {
            courseCodeInfo.setClassIds(null);
            courseCodeService.update(courseCodeInfo);
        }

        ModelAndView mv=new ModelAndView("redirect:/courseCode/getPageList.html");
        return mv;
    }
}
