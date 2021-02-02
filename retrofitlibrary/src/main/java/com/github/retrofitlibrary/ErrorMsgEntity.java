package com.github.retrofitlibrary;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;
import retrofit2.Response;

/**
 * 错误 消息 实体类
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

    private int code = ErrorMsgEntity.Code_Error_Unknown;
    private String msg = "未知错误";

    public ErrorMsgEntity(Throwable throwable){
        try {
            if (throwable instanceof HttpException) {
                Response response = ((HttpException) throwable).response();
                JSONObject joBody = new JSONObject(response.errorBody().string());
                setCode(ErrorMsgEntity.Code_Error_Api);
                setMsg(joBody.getString("msg"));
            } else if (throwable instanceof ConnectException
                    || throwable instanceof SocketTimeoutException
                    || throwable instanceof UnknownHostException) {
                setCode(ErrorMsgEntity.Code_Error_Network);
                setMsg("网络错误");
            } else if (throwable instanceof JsonParseException
                    || throwable instanceof JSONException
                    || throwable instanceof ParseException) {
                setCode(ErrorMsgEntity.Code_Error_Parse);
                setMsg("解析错误");
            } else if (throwable instanceof SSLHandshakeException) {
                setCode(ErrorMsgEntity.Code_Error_SSLHandshake);
                setMsg("证书验证失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
