package com.core.spring;

import com.core.dao.model.BaseEntity;
import com.core.util.ResTool;
import com.core.util.enums.ConstantEnum;
import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/3/18 18:16
 */
public class JsonView {
    //错误代码 0-成功
    private Integer code = 0;

    // 消息
    private String msg;

    // 数据
    private Object data = "";

    public JsonView(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public JsonView() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toString(){
        return JSONObject.fromObject(this).toString();
    }

    public String toIso8859String() throws UnsupportedEncodingException {
        return new String(this.toString().getBytes(), "ISO-8859-1");
    }

    public static void main(String[] args){

        JsonView res = new JsonView();
        res.setCode(0);
        res.setMsg("");
        res.setData(new JsonView(0, "1", "2"));
        List<String> lists = new ArrayList<>();
        lists.add("A");
        lists.add("B");
        lists.add("C");
        lists.add("D");
        //System.out.println(JSONObject.fromObject(res));
        System.out.println(ResTool.resp(ConstantEnum.OK, lists));

        System.out.println( res instanceof JsonView);
    }

}
