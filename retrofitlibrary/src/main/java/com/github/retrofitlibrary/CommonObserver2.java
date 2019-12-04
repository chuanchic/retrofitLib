package com.github.retrofitlibrary;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * 通用观察者：后台返回响应码、返回body的情况下用
 */
public abstract class CommonObserver2 implements Observer<ResponseBody> {

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(ResponseBody value) {
        JSONObject joResult = null;
        try {
            joResult = new JSONObject(value.string());
        }catch (Exception e){
            e.printStackTrace();
        }
        onHandleResult(joResult, null);
    }

    @Override
    public void onError(Throwable throwable) {
        ErrorMsgEntity errorMsgEntity = new ErrorMsgEntity();
        try {
            if (throwable instanceof HttpException) {
                Response response = ((HttpException) throwable).response();
                JSONObject joBody = new JSONObject(response.errorBody().string());

                errorMsgEntity.setCode(ErrorMsgEntity.Code_Error_Api);
                errorMsgEntity.setMsg(joBody.getString("msg"));
            } else if (throwable instanceof ConnectException
                    || throwable instanceof SocketTimeoutException
                    || throwable instanceof UnknownHostException) {
                errorMsgEntity.setCode(ErrorMsgEntity.Code_Error_Network);
                errorMsgEntity.setMsg("网络错误");
            } else if (throwable instanceof JsonParseException
                    || throwable instanceof JSONException
                    || throwable instanceof ParseException) {
                errorMsgEntity.setCode(ErrorMsgEntity.Code_Error_Parse);
                errorMsgEntity.setMsg("解析错误");
            } else if (throwable instanceof SSLHandshakeException) {
                errorMsgEntity.setCode(ErrorMsgEntity.Code_Error_SSLHandshake);
                errorMsgEntity.setMsg("证书验证失败");
            } else {
                errorMsgEntity.setCode(ErrorMsgEntity.Code_Error_Unknown);
                errorMsgEntity.setMsg("未知错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorMsgEntity.setCode(ErrorMsgEntity.Code_Error_Unknown);
            errorMsgEntity.setMsg("未知错误");
        }
        onHandleResult(null, errorMsgEntity);
    }

    @Override
    public void onComplete() {
    }

    protected abstract void onHandleResult(JSONObject joResult, ErrorMsgEntity errorMsgEntity);
}
