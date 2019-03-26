package com.github.retrofitlibrary;

import java.io.Serializable;

/**
 * 错误信息实体类
 */
public class ErrorMsgEntity implements Serializable {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
