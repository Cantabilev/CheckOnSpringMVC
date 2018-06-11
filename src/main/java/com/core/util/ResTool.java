package com.core.util;

import com.core.spring.JsonView;
import com.core.util.enums.ConstantEnum;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/2 14:02
 *
 * 响应基础对象
 */
public class ResTool {

    public static JSONObject resp(ConstantEnum constantEnum, Object obj) {
        JSONObject result = new JSONObject();
        result.put("code", constantEnum.getCode());
        if(constantEnum.getMsg()!=null)
            result.put("msg", constantEnum.getMsg());
        if(obj != null)
            result.put("data", obj);
        System.out.println("---------------返回数据-----------\n"+result);
        return result;
    }
    public static JSONObject resp(ConstantEnum constantEnum) {
        JSONObject result = new JSONObject();
        result.put("code", constantEnum.getCode());
        if(constantEnum.getMsg()!=null)
            result.put("msg", constantEnum.getMsg());
        System.out.println("---------------返回数据-----------\n"+result);
        return result;
    }
    public static JSONObject respErr(String msg) {
        JSONObject result = new JSONObject();
        result.put("code", ConstantEnum.FAIL.getCode());
        if(StringUtils.isNotBlank(msg))
            result.put("msg", msg);
        System.out.println("---------------返回数据-----------\n"+result);
        return result;
    }
}
