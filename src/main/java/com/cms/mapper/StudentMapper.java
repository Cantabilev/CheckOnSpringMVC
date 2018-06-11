package com.cms.mapper;

import com.cms.domain.StudentInfo;
import com.cms.vo.StudentVO;
import com.cms.vo.TeacherVO;
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
 * @date 2018/4/2 9:16
 */
public interface StudentMapper extends BaseMapper<StudentInfo> {

    @Select("select view_student.* from view_student " +
            "WHERE view_student.account = #{account}")
    @Results(value = {
            @Result(column = "grade_id", property = "gradeId"),
            @Result(column = "academy_id", property = "academyId"),
            @Result(column = "specialty_id", property = "specialtyId"),
            @Result(column = "class_id", property = "classId"),
            @Result(column = "grade_name", property = "gradeName"),
            @Result(column = "academy_name", property = "academyName"),
            @Result(column = "specialty_name", property = "specialtyName"),
            @Result(column = "specialty_code", property = "specialtyCode"),
            @Result(column = "class_name", property = "className"),
            @Result(column = "class_code", property = "classCode")})
    public StudentVO studentVO(@Param("account") String account);

    /**
     * 列表
     *
     * @param query
     * @return
     */
    public List<StudentVO> listVO(Query query);
    /**
     * 数量
     *
     * @param query
     * @return
     */
    public Integer countVO(Query query);
}
