package com.client.service;

import com.cms.domain.ClockingInfo;
import com.cms.domain.CourseDtlInfo;
import com.cms.dto.ClockingDTO;
import com.cms.dto.CourseClockingDTO;
import com.cms.dto.StudentClockingDtlDTO;
import com.cms.service.BaseService;
import com.cms.vo.BasePageParameter;
import com.core.exception.MyException;
import com.core.page.Pagination;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/25 10:51
 */
public interface ClockingService extends BaseService<ClockingInfo, ClockingDTO> {

    /**
     * 教师针对某一节课 开始考勤
     * @param teacherAccount
     * @param courseDtlId
     * @param longitude 经度
     * @param latitude 纬度
     * @throws MyException
     */
    public void startClocking(String teacherAccount, Long courseDtlId, Double longitude, Double latitude) throws MyException;

    /**
     * 学生针对某一节课 应答考勤
     * @param studentAccount
     * @param courseDtlId
     * @param longitude 经度
     * @param latitude 纬度
     * @throws MyException
     * @return true 正常提交   false 迟到提交
     */
    public boolean responseClocking(String studentAccount, Long courseDtlId, Double longitude, Double latitude, String mac) throws MyException;

    /**
     * 学生获取正在考勤的课程
     * @param studentAccount
     * @return
     */
    public List<CourseDtlInfo> getStudentClockingIng(String studentAccount) throws MyException;

    /**
     * 教师修改学生签到情况
     * @param teacherAccount
     * @param courseDtlId
     * @param studentAccount
     * @param state
     * @throws MyException
     */
    public void modifyStudentRespState(String teacherAccount, Long courseDtlId, String studentAccount, Integer state) throws MyException;

    /**
     * 学生查询课程签到状态
     * @param studentAccount
     * @param courseDtlId
     * @return
     */
    public Integer getClockingState(String studentAccount, Long courseDtlId) throws MyException;

    /**
     * 学生获取某门课程的签到结果
     * @param studentAccount
     * @param courseCode
     * @return
     * @throws MyException
     */
    public List<CourseClockingDTO> getCourseClockingDtl(String studentAccount, Long courseCode) throws MyException;

    /**
     * 老师获取某节课的考勤结果
     * @param teacherAccount
     * @param courseDtlId
     * @return
     * @throws MyException
     */
    public List<StudentClockingDtlDTO> getClockingDtlList(String teacherAccount, Long courseDtlId) throws MyException;

    /**
     * 老师获取某门课程的考勤结果
     * @param teacherAccount
     * @param courseCode
     * @return
     * @throws MyException
     */
    public List<ClockingDTO> getCourseCodeClockingDtlList(String teacherAccount, Long courseCode) throws MyException;


    public Pagination<ClockingDTO> pageClockingByTeacher(String teacherAccount, BasePageParameter basePageParameter);

    public Pagination<StudentClockingDtlDTO> pageClockingByStudent(String studentAccount, BasePageParameter basePageParameter);

}
