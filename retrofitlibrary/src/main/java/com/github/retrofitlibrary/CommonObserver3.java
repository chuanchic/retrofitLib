package com.github.retrofitlibrary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * 通用观察者：后台只返回响应码，不返回body的情况下用
 */
public abstract class CommonObserver3 implements Observer<Response<Void>> {

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(Response<Void> value) {
        boolean isSuccess = false;
        ErrorMsgEntity errorMsgEntity = null;
        try {
            isSuccess = value.isSuccessful();
            if(value.errorBody() != null){
                String errorBody = value.errorBody().string();
                errorMsgEntity = jsonToObj(errorBody, ErrorMsgEntity.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        onHandleResult(isSuccess, errorMsgEntity);
    }

    @Override
    public void onError(Throwable throwable) {
        boolean isSuccess = false;
        ErrorMsgEntity errorMsgEntity = null;
        if (throwable instanceof HttpException){
            Response response = ((HttpException) throwable).response();
            try {
                isSuccess = response.isSuccessful();
                if(response.errorBody() != null){
                    String errorBody = response.errorBody().string();
                    errorMsgEntity = jsonToObj(errorBody, ErrorMsgEntity.class);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        onHandleResult(isSuccess, errorMsgEntity);
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

    protected abstract void onHandleResult(boolean isSuccess, ErrorMsgEntity errorMsgEntity);
}
