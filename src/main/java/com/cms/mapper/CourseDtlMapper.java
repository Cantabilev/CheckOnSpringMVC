package com.cms.mapper;

import com.cms.domain.CourseDtlInfo;
import com.cms.domain.CourseInfo;
import com.cms.vo.CourseDtlVO;
import com.cms.vo.StudentVO;
import com.core.dao.mapper.BaseMapper;
import com.core.dao.query.Query;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/8 8:36
 */
public interface CourseDtlMapper extends BaseMapper<CourseDtlInfo> {
    @Select("select view_course_dtl.* from view_course_dtl " +
            "WHERE view_course_dtl.id = #{id}")
    @Results(value = {
            @Result(column = "teacher_account", property = "teacherAccount"),
            @Result(column = "course_id", property = "courseId"),
            @Result(column = "course_name", property = "courseName"),
            @Result(column = "course_code", property = "courseCode"),
            @Result(column = "academic_year_id", property = "academicYearId"),
            @Result(column = "academic_year_name", property = "academicYearName"),
            @Result(column = "schedule_name", property = "scheduleName"),
            @Result(column = "teacher_name", property = "teacherName"),
            @Result(column = "class_id", property = "classIds"),
            @Result(column = "grade_id", property = "gradeId"),
            @Result(column = "specialty_id", property = "specialtyId"),
            @Result(column = "grade_name", property = "gradeName"),
            @Result(column = "specialty_name", property = "specialtyName"),
            @Result(column = "specialty_code", property = "specialtyCode"),
            @Result(column = "academy_id", property = "academyId"),
            @Result(column = "academy_name", property = "academyName")
            })
    public CourseDtlVO courseDtlVO(@Param("id") Long courseDtlId);

    public void updateAllPlace(@Param("courseCode") Long courseCode, @Param("place") String place);

    public List<CourseDtlVO> listVO(Query query);

    public Integer countVO(Query query);
}
