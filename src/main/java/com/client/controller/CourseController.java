package com.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.cms.domain.*;
import com.cms.dto.CourseCodeDTO;
import com.cms.service.AcademyService;
import com.cms.service.CourseCodeService;
import com.cms.service.CourseDtlService;
import com.cms.service.CourseService;
import com.cms.vo.BasePageParameter;
import com.cms.vo.CourseDtlVO;
import com.cms.vo.CourseVO;
import com.cms.vo.TeacherVO;
import com.core.exception.MyException;
import com.core.page.Pagination;
import com.core.util.EncryptionUtil;
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
 * 课程表 Controller
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Resource
    CourseDtlService courseDtlService;

    @Resource
    CourseCodeService courseCodeService;

    @Resource
    CourseService courseService;

    @Resource
    AcademyService academyService;

//    /**
//     * 教师获取课表
//     * @param teacherAccount
//     * @param basePageParameter
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/getCourseListByTeacher", method = RequestMethod.GET)
//    public JSONObject getCourseListByTeacher(String teacherAccount, BasePageParameter basePageParameter){
//        if (null == teacherAccount)
//            return ResTool.resp(ConstantEnum.PARAM_NULL);
//        try{
//            Pagination<CourseDtlInfo> pages = courseDtlService.getCourseListTeacher(teacherAccount, basePageParameter);
//            return ResTool.resp(ConstantEnum.OK, pages.getItems());
//        }catch(Exception e){
//            return ResTool.respErr(e.getMessage());
//        }
//    }

//    /**
//     * 学生获取课表
//     * @param student
//     * @param basePageParameter
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/getCourseListByStudent", method = RequestMethod.GET)
//    public JSONObject getCourseListByStudent(StudentInfo student, BasePageParameter basePageParameter){
//        if (null == student)
//            throw new RuntimeException(ConstantEnum.PARAM_NULL.getMsg());
//        try{
//            Pagination<CourseDtlInfo> pages = courseDtlService.getCourseListStudent(student, basePageParameter);
//            return ResTool.resp(ConstantEnum.OK, pages.getItems());
//        }catch(Exception e){
//            return ResTool.respErr(e.getMessage());
//        }
//    }

    /**
     * 教师获取课表
     * @param teacherAccount
     * @param academicYearId 学年ID
     * @param semester 学期数
     * @return
     */
    // TODO TEST the interface function
    @ResponseBody
    @RequestMapping(value = "/getCourseListByTeacher", method = RequestMethod.GET)
    public JSONObject getCourseListByTeacher(String teacherAccount, Long academicYearId, String semester){
        if (null == teacherAccount || null == academicYearId || null == semester)
            return ResTool.resp(ConstantEnum.PARAM_NULL);
        try{
            List<CourseDtlInfo> pages = courseDtlService.getCourseListTeacher(teacherAccount, academicYearId, semester);
            return ResTool.resp(ConstantEnum.OK, pages);
        }catch(Exception e){
            e.printStackTrace();
            return ResTool.resp(ConstantEnum.SYS_ERROR);
        }
    }

    /**
     * 学生获取课表
     * @param student
     * @param academicYearId 学年 ID
     * @param semester 学期 数
     * @return
     */
    // TODO TEST the interface function
    @ResponseBody
    @RequestMapping(value = "/getCourseListByStudent", method = RequestMethod.GET)
    public JSONObject getCourseListByStudent(StudentInfo student, Long academicYearId, String semester){
        if (null == student || null == student.getClassId() || null == academicYearId || null == semester)
            return ResTool.resp(ConstantEnum.PARAM_NULL);
        try{
            List<CourseDtlInfo> pages = courseDtlService.getCourseListStudent(student, academicYearId, semester);
            return ResTool.resp(ConstantEnum.OK, pages);
        }catch(Exception e){
            e.printStackTrace();
            return ResTool.resp(ConstantEnum.SYS_ERROR);
        }
    }

    // TODO courseCode 更新后检查 VO 更新视图
    @ResponseBody
    @RequestMapping(value = "/getDetailCourseInfo", method = RequestMethod.GET)
    public JSONObject getDetailCourseInfo(Long courseDtlId){
        if (null == courseDtlId)
            return ResTool.resp(ConstantEnum.PARAM_NULL);
        try{
            CourseDtlVO vo = courseDtlService.findOneVO(courseDtlId);
            if (null == vo)
                return ResTool.resp(ConstantEnum.COURSE_NULL);
            else
                return ResTool.resp(ConstantEnum.OK, vo);
        }catch(Exception e){
            e.printStackTrace();
            return ResTool.resp(ConstantEnum.SYS_ERROR);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getDetailCourseCode", method = RequestMethod.GET)
    public JSONObject getDetailCourseCode(Long courseCode){
        if (null == courseCode)
            return ResTool.resp(ConstantEnum.PARAM_NULL);
        try{
            CourseCodeDTO vo = courseCodeService.findOneDTO(courseCode);
            if (null == vo)
                return ResTool.resp(ConstantEnum.COURSE_CODE_NULL);
            else
                return ResTool.resp(ConstantEnum.OK, vo);
        }catch(MyException e){
            e.printStackTrace();
            return ResTool.resp(e.getConstantEnum());
        }
    }







    @RequestMapping(value = "/getPageList")
    public ModelAndView getPageList(CourseVO courseVO, BasePageParameter parm){
        ModelAndView mv=new ModelAndView("checkcms/courseList");
        Pagination<CourseVO> page = courseService.pageListVO(courseVO,parm);

        List<AcademyInfo> academyInfos = academyService.getAllAcademy();

        mv.addObject("pagination",page);
        mv.addObject("cur_nav", "course");
        mv.addObject("name", courseVO.getName());
        mv.addObject("state", courseVO.getState());
        mv.addObject("academyList", academyInfos);
        mv.addObject("academyId", courseVO.getAcademyId());
        return mv;
    }

    @RequestMapping(value = "/updateState")
    public ModelAndView updateState(Long id ,Integer state){
        ModelAndView mv=new ModelAndView("redirect:/course/getPageList.html");
        if(id!=null){
            courseService.offOrOn(id, state);
        }
        return mv;
    }

    @RequestMapping(value = "toMerge")
    public ModelAndView toMerge(Long id){
        ModelAndView mv = new ModelAndView("checkcms/courseMerge");

        List<AcademyInfo> academyInfos = academyService.getAllAcademy();

        CourseInfo info = new CourseInfo();
        if (id != null) {
            info = courseService.findOne(id);
        }
        mv.addObject("academyList", academyInfos);
        mv.addObject("entity",info);
        return mv;
    }

    @RequestMapping(value = "/doMerge")
    public ModelAndView doMerge(CourseInfo courseInfo){

        if(courseInfo.getId()==null){
            courseService.insert(courseInfo);
        }else{
            courseService.update(courseInfo);
        }

        ModelAndView mv=new ModelAndView("redirect:/course/getPageList.html");
        return mv;
    }
}
