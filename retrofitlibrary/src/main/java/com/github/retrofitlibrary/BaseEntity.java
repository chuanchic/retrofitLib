package com.github.retrofitlibrary;

import com.google.gson.annotations.SerializedName;

/**
 * 服务器返回通用的数据格式
 */
public class BaseEntity<E> {
    @SerializedName("ret")
    private int ret;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private E data;

    public boolean isSuccess() {
        return ret == 0;
    }

    public int getCode() {
        return ret;
    }

    public String getMsg() {
        return msg;
    }

    public E getData() {
        return data;
    }
}
