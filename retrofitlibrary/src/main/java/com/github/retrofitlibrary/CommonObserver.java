package com.github.retrofitlibrary;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 通用观察者
 */
public abstract class CommonObserver<T> implements Observer<BaseEntity<T>> {
    private static final String TAG = CommonObserver.class.getName();

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(BaseEntity<T> value) {
        if (value.isSuccess()) {//请求成功
            onHandleSuccess(value.getData());
        } else {//请求失败
            onHandleError(value.getMsg());
        }
    }

    @Override
    public void onError(Throwable e) {
        onHandleError(e.toString());
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete");
    }

    protected abstract void onHandleSuccess(T t);
    protected abstract void onHandleError(String msg);
}
