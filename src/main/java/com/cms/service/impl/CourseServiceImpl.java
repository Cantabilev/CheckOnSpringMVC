package com.cms.service.impl;

import com.cms.dao.CourseDao;
import com.cms.domain.CourseInfo;
import com.cms.domain.TeacherInfo;
import com.cms.mapper.CourseMapper;
import com.cms.service.CourseService;
import com.cms.vo.BasePageParameter;
import com.cms.vo.CourseVO;
import com.cms.vo.TeacherVO;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.page.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/20 17:35
 */
@Service
public class CourseServiceImpl extends BaseServiceImpl<CourseInfo, CourseVO, CourseMapper, CourseDao> implements CourseService {

    @Override
    public Pagination<CourseVO> pageListVO(CourseVO vo, BasePageParameter basePageParameter) {
        FilterMap map=new FilterMap();
        if(StringUtils.isNotBlank(vo.getName())){
            map.like("t_course.name","%"+vo.getName()+"%");
        }
        if(null != vo.getAcademyId()){
            map.eq("academy_id", vo.getAcademyId());
        }
        if(null != vo.getState()){
            map.eq("state", vo.getState());
        }
        OrderMap orderMap=new OrderMap();
        orderMap.desc("id");
        Pagination<CourseVO> page  =dao.pageVO(map, orderMap,
                basePageParameter.getPageNum(),
                basePageParameter.getPageSize());
        return page;
    }

    public void updateState(Long id, Integer state) {
        CourseInfo info = dao.findOne(id);
        info.setState(state);
        dao.update(info);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public void offOrOn(Long id, Integer state) {
        updateState(id, state);
    }
}
