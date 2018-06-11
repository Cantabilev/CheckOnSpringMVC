package com.core.util;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/5 18:23
 */
public class CalcUtil {

    private static double EARTH_RADIUS = 6371.393;
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     *
     * @param longitude1
     * @param latitude1
     * @param longitude2
     * @param latitude2
     * @return 单位 m
     */
    public static double getDistance(Double longitude1, Double latitude1, Double longitude2, Double latitude2){
        // TODO write the function getDistance for longitude and latitude

        double radLat1 = rad(latitude1);
        double radLat2 = rad(latitude2);
        double a = radLat1 - radLat2;
        double b = rad(longitude1) - rad(longitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 1000);
        return s;
    }

    public static Long getTimeGap(Long timeStamp1, Long timeStamp2){

        return Math.abs(timeStamp1 - timeStamp2);
    }

    public static void main(String[] args) {
        System.out.println(CalcUtil.getDistance(106.486654,29.490295,106.581515,29.615467));
    }
}
