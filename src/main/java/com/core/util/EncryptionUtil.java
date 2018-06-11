package com.core.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/3/30 10:22
 */
public class EncryptionUtil {

    public static String encryptMD5(String strPasswd) {
        if (strPasswd == null || strPasswd.equals("")) {
            return "";
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return Base64.encodeBase64String(md.digest(strPasswd.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(encryptMD5("123456"));//4QrcOUm6Wau+VuBX8g+IPg==

        String str = "";
        String[] ss = str.split(",");

        for (String s : ss)
            System.out.println("index-> "+s);
    }
}
