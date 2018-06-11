package com.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.client.service.ClockingService;
import com.cms.domain.CourseDtlInfo;
import com.cms.domain.StudentInfo;
import com.cms.domain.TeacherInfo;
import com.cms.dto.ClockingDTO;
import com.cms.dto.CourseClockingDTO;
import com.cms.dto.StudentClockingDtlDTO;
import com.cms.vo.BasePageParameter;
import com.core.exception.MyException;
import com.core.page.Pagination;
import com.core.util.DateUtil2;
import com.core.util.ResTool;
import com.core.util.enums.ConstantEnum;
import org.apache.commons.lang.StringUtils;
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
 * @date 2018/4/16 18:04
 *
 * 考勤 Controller
 */
@Controller
@RequestMapping("/clocking")
public class ClockingController {

    @Resource
    ClockingService clockingService;

    /**
     * 教师开始考勤
     * @param teacher
     * @param courseDtlId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/postStartClocking", method = RequestMethod.POST)
    public JSONObject postStartClocking(TeacherInfo teacher, Long courseDtlId, Double longitude, Double latitude){
        if (null == teacher || null == teacher.getAccount() || null == courseDtlId || null == longitude || null == latitude)
            return ResTool.resp(ConstantEnum.PARAM_NULL);
        try{
            clockingService.startClocking(teacher.getAccount(), courseDtlId, longitude,latitude);
            return ResTool.resp(ConstantEnum.OK);
        }catch (MyException e){
            return ResTool.resp(e.getConstantEnum());
        }
    }

    /**
     * 学生签到考勤
     * @param student
     * @param courseDtlId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/postResponseClocking", method = RequestMethod.POST)
    public JSONObject postResponseClocking(StudentInfo student, Long courseDtlId, Double longitude, Double latitude, String mac){
        if (null == student || null == student.getAccount() || null == courseDtlId || null == longitude || null == latitude || StringUtils.isEmpty(mac))
            return ResTool.resp(ConstantEnum.PARAM_NULL);
        try{
            boolean flag = clockingService.responseClocking(student.getAccount(), courseDtlId, longitude, latitude, mac);
            if (flag)
                return ResTool.resp(ConstantEnum.CLOCKING_NORMAL);
            else
                return ResTool.resp(ConstantEnum.CLOCKING_LATE);
        }catch (MyException e){
            e.printStackTrace();
            return ResTool.resp(e.getConstantEnum());
        }
    }

    /**
     * 教师获取考勤表
     * @param teacher
     * @param courseDtlId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getClockingTable", method = RequestMethod.GET)
    public JSONObject getClockingTable(TeacherInfo teacher, Long courseDtlId){
        if (null == teacher || null == courseDtlId)
            return ResTool.resp(ConstantEnum.PARAM_NULL);
        // TODO write the interface response Clocking
        return null;
    }

    /**
     * 学生获取正在进行考勤的课
     * @param studentAccount
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getClockingIng", method = RequestMethod.GET)
    public JSONObject getClockingIng(String studentAccount){
        if (StringUtils.isBlank(studentAccount))
            return ResTool.resp(ConstantEnum.PARAM_NULL);
        try{
            List<CourseDtlInfo> infos = clockingService.getStudentClockingIng(studentAccount);
            return ResTool.resp(ConstantEnum.OK, infos);
        }catch (MyException e){
            e.printStackTrace();
            return ResTool.resp(e.getConstantEnum());
        }
    }

    /**
     * 教师修改单一学生的签到状态
     * @param teacherAccount
     * @param courseDtlId
     * @param studentAccount
     * @param state
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "postModifyStudentRespState", method = RequestMethod.POST)
    public JSONObject postModifyStudentRespState(String teacherAccount, Long courseDtlId, String studentAccount, Integer state){
        if (StringUtils.isBlank(teacherAccount) || null == courseDtlId || StringUtils.isBlank(studentAccount) || null == state)
            return ResTool.resp(ConstantEnum.PARAM_NULL);
        switch (state){
            case 0:break;
            case 1:break;
            case 2:break;
            default:return ResTool.resp(ConstantEnum.CLOCKING_MODIFY_STATE);
        }
        try{
            clockingService.modifyStudentRespState(teacherAccount, courseDtlId, studentAccount, state);
            return ResTool.resp(ConstantEnum.OK);
        }catch (MyException e){
            e.printStackTrace();
            return ResTool.resp(e.getConstantEnum());
        }
    }

    /**
     * 学生获取某节课的签到结果 courseDtlId
     * 该次课是否成功签到
     * @param studentAccount
     * @param courseDtlId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getResultClockingState", method = RequestMethod.GET)
    public JSONObject getResultClockingState(String studentAccount, Long courseDtlId){
        if (StringUtils.isBlank(studentAccount) || null == courseDtlId)
            return ResTool.resp(ConstantEnum.PARAM_NULL);

        try {
            Integer state = clockingService.getClockingState(studentAccount, courseDtlId);
            if (null == state)
                return ResTool.resp(ConstantEnum.CLOCKING_QUERY);
            else
                return ResTool.resp(ConstantEnum.OK, state);
        } catch (MyException e) {
            e.printStackTrace();
            return ResTool.resp(e.getConstantEnum());
        }
    }

    // 学生获取某门课程的签到结果
    // 已签到的课程 和 未签到的课
    @ResponseBody
    @RequestMapping(value = "/getCourseClockingDtl", method = RequestMethod.GET)
    public JSONObject getCourseClockingDtl(String studentAccount, Long courseCode){
        if (StringUtils.isBlank(studentAccount) || null == courseCode)
            return ResTool.resp(ConstantEnum.PARAM_NULL);
        try {
            List<CourseClockingDTO> data = clockingService.getCourseClockingDtl(studentAccount, courseCode);
            return ResTool.resp(ConstantEnum.OK, data);
        } catch (MyException e) {
            e.printStackTrace();
            return ResTool.resp(e.getConstantEnum());
        }
    }

    // 老师获取某节课的考勤结果
    // 已签到的学生，未签到的学生，迟到的...
    @ResponseBody
    @RequestMapping(value = "/getResultCourseClocking", method = RequestMethod.GET)
    public JSONObject getResultCourseClocking(String teacherAccount, Long courseDtlId){
        if (StringUtils.isBlank(teacherAccount) || null == courseDtlId)
            return ResTool.resp(ConstantEnum.PARAM_NULL);
        try {
            List<StudentClockingDtlDTO> data = clockingService.getClockingDtlList(teacherAccount, courseDtlId);
            return ResTool.resp(ConstantEnum.OK, data);
        } catch (MyException e) {
            e.printStackTrace();
            return ResTool.resp(e.getConstantEnum());
        }
    }

    // 老师获取某门课程的考勤结果
    // 每一节课，签到的人数，未签到的人数，迟到的...
    @ResponseBody
    @RequestMapping(value = "/getResultCourseCodeClocking", method = RequestMethod.GET)
    public JSONObject getResultCourseCodeClocking(String teacherAccount, Long courseCode){
        if (StringUtils.isBlank(teacherAccount) || null == courseCode)
            return ResTool.resp(ConstantEnum.PARAM_NULL);
        try {
            List<ClockingDTO> data = clockingService.getCourseCodeClockingDtlList(teacherAccount, courseCode);
            return ResTool.resp(ConstantEnum.OK, data);
        } catch (MyException e) {
            e.printStackTrace();
            return ResTool.resp(e.getConstantEnum());
        }
    }

    /**
     * 学生 获取考勤历史
     * @param studentAccount
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getStudentClockingHistory", method = RequestMethod.GET)
    public JSONObject getStudentClockingHistory(String studentAccount, BasePageParameter param){

        if (StringUtils.isBlank(studentAccount))
            return ResTool.resp(ConstantEnum.PARAM_NULL);

        Pagination<StudentClockingDtlDTO> page = clockingService.pageClockingByStudent(studentAccount, param);

        return ResTool.resp(ConstantEnum.OK, page.getItems());
    }

    /**
     * 老师 获取考勤历史
     * @param teacherAccount
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTeacherClockingHistory", method = RequestMethod.GET)
    public JSONObject getTeacherClockingHistory(String teacherAccount, BasePageParameter param){
        if (StringUtils.isBlank(teacherAccount))
            return ResTool.resp(ConstantEnum.PARAM_NULL);

        Pagination<ClockingDTO> page = clockingService.pageClockingByTeacher(teacherAccount, param);

        return ResTool.resp(ConstantEnum.OK, page.getItems());
    }


    @RequestMapping(value = "/getPageList")
    public ModelAndView getPageList(ClockingDTO dto, BasePageParameter parm){
        ModelAndView mv=new ModelAndView("checkcms/clockingList");

        Pagination<ClockingDTO> page = clockingService.pageListVO(dto,parm);

        for(ClockingDTO info : page.getItems()){
            if(info.getDate()!=null){
                info.setDateStr(DateUtil2.timeToString_yyyy_MM_dd(info.getDate()));
            }

            if(info.getSubmitTime()!=null){
                info.setSubmitTimeStr(DateUtil2.timeToString_yyyy_MM_dd_HH_mm(info.getSubmitTime()));
            }
        }

        mv.addObject("pagination",page);
        mv.addObject("cur_nav", "clocking");
        mv.addObject("courseCode", dto.getCourseCode());
        mv.addObject("teacherAccount", dto.getTeacherAccount());
        return mv;
    }
}
