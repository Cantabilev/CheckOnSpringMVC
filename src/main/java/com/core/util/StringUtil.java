package com.core.util;

import com.core.exception.MyException;
import com.core.util.enums.ConstantEnum;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/19 16:03
 */
public class StringUtil {

    public static String formatLimit2(Integer num) throws MyException {
        if (num >= 100)
            throw new MyException(ConstantEnum.OUT_OF_MAX_INDEX);
        return String.format("%02d", num);
    }

    public static String formatLimitGrade(Long num) throws MyException {
        if (num <= 2000)
            throw new MyException(ConstantEnum.GRADE_ROLE);
        return String.format("%03d", num - 1900);
    }

    public static void main(String[] args){
        try {
            System.out.println(StringUtil.formatLimitGrade(2015L));
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
