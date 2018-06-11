package com.cms.mapper;

import com.cms.domain.TeacherInfo;
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
public interface TeacherMapper extends BaseMapper<TeacherInfo> {

    @Select("select t_teacher.*, t_academy.name AS academy_name from t_teacher, t_academy " +
            "WHERE t_teacher.academy_id = t_academy.id AND t_teacher.account = #{account}")
    @Results(value = {
            @Result(column = "academy_id", property = "academyId"),
            @Result(column = "academy_name", property = "academyName")})
    public TeacherVO teacherVO(@Param("account") String account);

    /**
     * 列表
     *
     * @param query
     * @return
     */
    public List<TeacherVO> listVO(Query query);
    /**
     * 数量
     *
     * @param query
     * @return
     */
    public Integer countVO(Query query);
}
