package com.cms.mapper;

import com.cms.domain.ClassInfo;
import com.cms.vo.ClassVO;
import com.cms.vo.SpecialtyVO;
import com.core.dao.mapper.BaseMapper;
import com.core.dao.query.Query;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/8 8:33
 */
public interface ClassMapper extends BaseMapper<ClassInfo> {

    /**
     * 列表
     *
     * @param query
     * @return
     */
    public List<ClassVO> listVO(Query query);

    public Integer countVO(Query query);
}
