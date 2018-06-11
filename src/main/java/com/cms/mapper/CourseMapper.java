package com.cms.mapper;

import com.cms.domain.CourseInfo;
import com.cms.vo.CourseVO;
import com.cms.vo.TeacherVO;
import com.core.dao.mapper.BaseMapper;
import com.core.dao.query.Query;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/8 8:36
 */
public interface CourseMapper extends BaseMapper<CourseInfo> {

    /**
     * 列表
     *
     * @param query
     * @return
     */
    public List<CourseVO> listVO(Query query);
    /**
     * 数量
     *
     * @param query
     * @return
     */
    public Integer countVO(Query query);
}
