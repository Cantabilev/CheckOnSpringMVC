package com.cms.mapper;

import com.cms.domain.SpecialtyInfo;
import com.cms.vo.SpecialtyVO;
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
 * @date 2018/4/8 8:37
 */
public interface SpecialtyMapper extends BaseMapper<SpecialtyInfo> {

    @Select("select t_specialty.*, t_academy.name AS academy_name from t_specialty, t_academy " +
            "WHERE t_specialty.academy_id = t_academy.id AND t_specialty.id = #{specialtyId}")
    @Results(value = {
            @Result(column = "academy_id", property = "academyId"),
            @Result(column = "academy_name", property = "academyName"),
            @Result(column = "specialty_code", property = "specialtyCode")})
    public SpecialtyVO specialtyVO(@Param("specialtyId") String specialtyId);

    /**
     * 列表
     *
     * @param query
     * @return
     */
    public List<SpecialtyVO> listVO(Query query);
    /**
     * 数量
     *
     * @param query
     * @return
     */
    public Integer countVO(Query query);
}
