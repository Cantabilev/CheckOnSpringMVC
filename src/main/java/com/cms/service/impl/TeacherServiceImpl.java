package com.cms.service.impl;

import com.cms.dao.TeacherDao;
import com.cms.domain.TeacherInfo;
import com.cms.mapper.TeacherMapper;
import com.cms.service.TeacherService;
import com.cms.vo.BasePageParameter;
import com.cms.vo.TeacherVO;
import com.core.dao.query.FilterMap;
import com.core.dao.query.OrderMap;
import com.core.page.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/9 20:59
 */
@Service
public class TeacherServiceImpl extends BaseServiceImpl<TeacherInfo, TeacherVO, TeacherMapper, TeacherDao> implements TeacherService {

    @Override
    public Pagination<TeacherVO> pageListVO(TeacherVO vo, BasePageParameter basePageParameter) {
        FilterMap map=new FilterMap();
        if(StringUtils.isNotBlank(vo.getAccount())){
            map.like("account","%"+vo.getAccount()+"%");
        }
        if(StringUtils.isNotBlank(vo.getName())){
            map.like("t_teacher.name","%"+vo.getName()+"%");
        }
        if(null != vo.getAcademyId()){
            map.eq("academy_id", vo.getAcademyId());
        }
        if(null != vo.getState()){
            map.eq("state", vo.getState());
        }
        OrderMap orderMap=new OrderMap();
        orderMap.desc("id");
        Pagination<TeacherVO> page  =dao.pageVO(map, orderMap,
                basePageParameter.getPageNum(),
                basePageParameter.getPageSize());
        return page;
    }

    public void updateState(Long id, Integer state) {
        TeacherInfo info = dao.findOne(id);
        info.setState(state);
        dao.update(info);
    }

    // TODO 封禁教师的同时 对其所有课程 封禁
    @Transactional(rollbackFor=Exception.class)
    @Override
    public void offOrOn(Long id, Integer state) {
        updateState(id, state);
//        if(status.equals( HhConstants.coachInfo.Status.off.getCode())){
    }

    @Override
    public TeacherInfo findOne(String account) {
        FilterMap map = new FilterMap();
        map.eq("account", account);
        return dao.findOne(map);
    }
}
