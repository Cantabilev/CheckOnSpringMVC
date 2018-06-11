package com.cms.service.impl;

import com.cms.dao.AdminInfoDao;
import com.cms.domain.AdminInfo;
import com.cms.service.AdminInfoService;
import com.core.dao.query.FilterMap;
import com.core.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/3/21 10:12
 */
@Service
public class AdminInfoServiceImpl implements AdminInfoService {

    @Autowired
    AdminInfoDao adminInfoDao;

    @Override
    public boolean login(AdminInfo info) {
        try {
            FilterMap map=new FilterMap();
            map.eq("account",info.getAccount());
            map.eq("password", EncryptionUtil.encryptMD5(info.getPassword()));
            AdminInfo resultInfo= adminInfoDao.findOne(map);
            if(resultInfo!=null){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
