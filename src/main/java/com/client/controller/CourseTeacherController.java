package com.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.cms.domain.CourseCodeInfo;
import com.cms.domain.TeacherInfo;
import com.cms.service.CourseDtlService;
import com.cms.service.TeacherService;
import com.core.exception.MyException;
import com.core.util.ResTool;
import com.core.util.enums.ConstantEnum;
import com.core.util.enums.StateEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/18 14:12
 */
@Controller
@RequestMapping("/course/teacher")
public class CourseTeacherController {

    @Resource
    CourseDtlService courseDtlService;

    @Resource
    TeacherService teacherService;

    // TODO test the interface function
    /**
     * 修改单个课程上课地点
     * @param teacherAccount
     * @param courseDtlId courseDtlID
     * @param place
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/postModifySingleCoursePlace", method = RequestMethod.POST)
    public JSONObject postModifySingleCoursePlace(String teacherAccount, Long courseDtlId, String place){
        if (null == teacherAccount || null == courseDtlId || null == place)
            return ResTool.resp(ConstantEnum.PARAM_NULL);

        TeacherInfo teacher = teacherService.findOne(teacherAccount);

        if (null == teacher)
            return ResTool.resp(ConstantEnum.TARGET_NULL);
        if (teacher.getState() != StateEnum.OK.getCode())
            return ResTool.resp(ConstantEnum.ACCOUNT_FROZEN);
        try {
            courseDtlService.modifySingleCoursePlace(courseDtlId, teacherAccount, place);
            return ResTool.resp(ConstantEnum.OK);
        } catch (MyException e) {
            return ResTool.resp(e.getConstantEnum());
        }
    }

    // TODO test the interface function

    /**
     * 修改该老师的所有该课程上课地点
     * @param teacherAccount
     * @param courseCode
     * @param place
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/postModifyCoursePlace", method = RequestMethod.POST)
    public JSONObject postModifyCoursePlace(String teacherAccount, Long courseCode, String place){
        if (null == teacherAccount || null == courseCode || null == place)
            return ResTool.resp(ConstantEnum.PARAM_NULL);

        TeacherInfo teacher = teacherService.findOne(teacherAccount);

        if (null == teacher)
            return ResTool.resp(ConstantEnum.TARGET_NULL);
        if (teacher.getState() != StateEnum.OK.getCode())
            return ResTool.resp(ConstantEnum.ACCOUNT_FROZEN);
        try {
            courseDtlService.modifyCoursePlace(courseCode, teacherAccount, place);
            return ResTool.resp(ConstantEnum.OK);
        } catch (MyException e) {
            e.printStackTrace();
            return ResTool.resp(e.getConstantEnum());
        }
    }

    /**
     * 教师添加一个学生
     * @param teacherAccount
     * @param courseCode
     * @param contain
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/postAddStudent", method = RequestMethod.POST)
    public JSONObject postAddStudent(String teacherAccount, Long courseCode, String contain){
        if (null == teacherAccount || null == courseCode || null == contain)
            return ResTool.resp(ConstantEnum.PARAM_NULL);

        TeacherInfo teacher = teacherService.findOne(teacherAccount);

        if (null == teacher)
            return ResTool.resp(ConstantEnum.TARGET_NULL);
        if (teacher.getState() != StateEnum.OK.getCode())
            return ResTool.resp(ConstantEnum.ACCOUNT_FROZEN);

        try {
            courseDtlService.addCourseStudent(courseCode, teacherAccount, contain);
            return ResTool.resp(ConstantEnum.OK);
        } catch (MyException e) {
            return ResTool.resp(e.getConstantEnum());
        }
    }

    /**
     * 教师删除一个学生
     * @param teacherAccount
     * @param courseCode
     * @param export
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/postDeleteStudent", method = RequestMethod.POST)
    public JSONObject postDeleteStudent(String teacherAccount, Long courseCode, String export){
        if (null == teacherAccount || null == courseCode || null == export)
            return ResTool.resp(ConstantEnum.PARAM_NULL);

        TeacherInfo teacher = teacherService.findOne(teacherAccount);

        if (null == teacher)
            return ResTool.resp(ConstantEnum.TARGET_NULL);
        if (teacher.getState() != StateEnum.OK.getCode())
            return ResTool.resp(ConstantEnum.ACCOUNT_FROZEN);

        try {
            courseDtlService.deleteCourseStudent(courseCode, teacherAccount, export);
            return ResTool.resp(ConstantEnum.OK);
        } catch (MyException e) {
            return ResTool.resp(e.getConstantEnum());
        }
    }

    /**
     * 老师添加一节课
     * @param courseCodeInfo 当有课程模板时 只需要 teacherAccount、 courseCode
     *                       没有课程模板时 需要先添加课程模板， 所以需要课程模板的所有字段 （除courseCode外的所有字段）
     * @param place 无论有无课程模板均需要 地点
     * @param date 无论有无课程模板均需要 时间
     * @param scheduleName 无论有无课程模板均需要 上课节数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/postAddSingleCourse", method = RequestMethod.POST)
    public JSONObject postAddSingleCourse(CourseCodeInfo courseCodeInfo, String place, Long date, Integer scheduleName){
        if (null == courseCodeInfo || null == courseCodeInfo.getTeacherAccount() || null == place || null == date || null == scheduleName)
            return ResTool.resp(ConstantEnum.PARAM_NULL);
        TeacherInfo teacher = teacherService.findOne(courseCodeInfo.getTeacherAccount());

        if (null == teacher)
            return ResTool.resp(ConstantEnum.TARGET_NULL);
        if (teacher.getState() != StateEnum.OK.getCode())
            return ResTool.resp(ConstantEnum.ACCOUNT_FROZEN);

        try {
            courseDtlService.addSingleCourse(courseCodeInfo, place, new Date(date), scheduleName);
            return ResTool.resp(ConstantEnum.OK);
        } catch (MyException e) {
            return ResTool.resp(e.getConstantEnum());
        }
    }

    /**
     * 老师删除一节课
     * @param teacherAccount
     * @param courseDtlId
     * @param courseCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/postDeleteSingleCourse", method = RequestMethod.POST)
    public JSONObject postDeleteSingleCourse(Long courseDtlId, Long courseCode, String teacherAccount){
        if (null == teacherAccount || null == courseDtlId || null == courseCode)
            return ResTool.resp(ConstantEnum.PARAM_NULL);
        TeacherInfo teacher = teacherService.findOne(teacherAccount);

        if (null == teacher)
            return ResTool.resp(ConstantEnum.TARGET_NULL);
        if (teacher.getState() != StateEnum.OK.getCode())
            return ResTool.resp(ConstantEnum.ACCOUNT_FROZEN);

        try {
            courseDtlService.deleteSingleCourse(courseDtlId, courseCode, teacherAccount);
            return ResTool.resp(ConstantEnum.OK);
        } catch (MyException e) {
            return ResTool.resp(e.getConstantEnum());
        }
    }
}
