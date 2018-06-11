package com.cms.controller;

import com.alibaba.fastjson.JSONObject;
import com.cms.domain.AcademyInfo;
import com.cms.domain.GradeInfo;
import com.cms.service.GradeService;
import com.core.util.ResTool;
import com.core.util.enums.ConstantEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/16 23:53
 */
@Controller
@RequestMapping(value = "/grade")
public class GradeController {

    @Resource
    GradeService gradeService;

    @ResponseBody
    @RequestMapping(value = "/getAllGrade", method = RequestMethod.GET)
    public JSONObject getAllGrade(){
        List<GradeInfo> list = gradeService.getAllGrade();
        return ResTool.resp(ConstantEnum.OK, list);
    }
}
