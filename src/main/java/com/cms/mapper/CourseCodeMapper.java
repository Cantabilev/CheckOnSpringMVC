package com.cms.mapper;

import com.cms.dao.CourseCodeDao;
import com.cms.domain.CourseCodeInfo;
import com.core.dao.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/8 8:31
 */
public interface CourseCodeMapper extends BaseMapper<CourseCodeInfo> {


    public void updateAllStudent(@Param("courseCode") Long courseCode, @Param("contains") String contains, @Param("exports") String exports);

}
