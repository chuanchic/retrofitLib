package com.github.retrofitlib;

/**
 * 我的Url配置：接口地址统一配置
 */
public class MyUrlConfig {
    /**
     * Url相关
     */
    public static String BASE_URL_1;
    public static String BASE_URL_2;
    public static String BASE_URL_3;
    public static String BASE_URL_4;
    public static String BASE_URL_5;
    public static String BASE_URL_6;
    public static String BASE_URL_7;

    static {//静态代码块
        if(true){//线下环境
            BASE_URL_1 = "http://test.user.suixingkang.com/";
            BASE_URL_2 = "http://test.tiantiance.suixingkang.com/";
            BASE_URL_3 = "http://trade.suixingkang.com/test/h5/index.html";
            BASE_URL_4 = "http://test.medical.suixingkang.com/";
            BASE_URL_5 = "http://test.index.suixingkang.com/";
            BASE_URL_6 = "http://test.clock.suixingkang.com/";
            BASE_URL_7 = "http://test.news.suixingkang.com/";
        }else{//线上环境
            BASE_URL_1 = "http://user.suixingkang.com/";
            BASE_URL_2 = "http://tiantiance.suixingkang.com/";
            BASE_URL_3 = "http://trade.suixingkang.com/h5/index.html";
            BASE_URL_4 = "http://medical.suixingkang.com/";
            BASE_URL_5 = "http://index.suixingkang.com/";
            BASE_URL_6 = "http://clock.suixingkang.com/";
            BASE_URL_7 = "http://news.suixingkang.com/";
        }
    }


}
