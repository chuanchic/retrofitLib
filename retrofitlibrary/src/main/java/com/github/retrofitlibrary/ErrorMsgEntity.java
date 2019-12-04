package com.github.retrofitlibrary;

import java.io.Serializable;

/**
 * 错误信息实体类
 */
public class ErrorMsgEntity implements Serializable {
    /**
     * 错误码
     */
    public static final int Code_Error_Api = 1;//接口错误
    public static final int Code_Error_Network = 2;//网络错误
    public static final int Code_Error_Parse = 3;//解析错误
    public static final int Code_Error_SSLHandshake = 4;//证书验证错误
    public static final int Code_Error_Unknown = 5;//未知错误

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
