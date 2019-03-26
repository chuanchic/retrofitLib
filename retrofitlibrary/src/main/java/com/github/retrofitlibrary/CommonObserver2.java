package com.github.retrofitlibrary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

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
//        boolean isSuccess = false;
        try {
            joResult = new JSONObject(value.string());
//            isSuccess = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        onHandleResult(joResult, null);
    }

    @Override
    public void onError(Throwable throwable) {
//        boolean isSuccess = false;
        ErrorMsgEntity errorMsgEntity = null;
        if (throwable instanceof HttpException){
            Response response = ((HttpException) throwable).response();
            try {
//                isSuccess = response.isSuccessful();
                if(response.errorBody() != null){
                    String errorBody = response.errorBody().string();

                    errorMsgEntity = jsonToObj(errorBody, ErrorMsgEntity.class);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        onHandleResult(null, errorMsgEntity);
    }

    @Override
    public void onComplete() {
    }

    /**
     * 将json转化为实体类
     */
    private <T> T jsonToObj(String json, Class<T> obj) {
        T t = null;
        try {
            Gson gson = new GsonBuilder().create();
            t = gson.fromJson(json, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    protected abstract void onHandleResult(JSONObject joResult, ErrorMsgEntity errorMsgEntity);
}
