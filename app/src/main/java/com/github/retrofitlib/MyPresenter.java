package com.github.retrofitlib;

import com.github.retrofitlibrary.CommonObserver2;
import com.github.retrofitlibrary.ErrorMsgEntity;
import com.trello.rxlifecycle2.LifecycleTransformer;

import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyPresenter {
    /**
     * 线程调度
     */
    private static <T> ObservableTransformer<T, T> threadSchedulers(final LifecycleTransformer<T> lifecycleTransformer) {
        if(lifecycleTransformer == null){
            return new ObservableTransformer<T, T>() {
                @Override
                public ObservableSource<T> apply(Observable<T> observable) {
                    return observable
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
                }
            };
        }else{
            return new ObservableTransformer<T, T>() {
                @Override
                public ObservableSource<T> apply(Observable<T> observable) {
                    return observable
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .compose(lifecycleTransformer);
                }
            };
        }
    }

    /**
     * 登录
     */
    public static void login(LifecycleTransformer lifecycleTransformer, String phoneNum, String authCode, String loginType, String front_key_name, final LoginCallback callback){
        String deviceToken = "abcdefg";
        String url = MyUrlConfig.BASE_URL_1 + "api/user/login";

        RetrofitFactory.getService()
                .login(url, loginType, authCode, phoneNum, "", deviceToken, "android", front_key_name)
                .compose(threadSchedulers(lifecycleTransformer))
                .subscribe(new CommonObserver2() {
                    @Override
                    protected void onHandleResult(JSONObject joResult, ErrorMsgEntity errorMsgEntity) {
                        if(callback != null){
                            callback.callback(joResult);
                        }
                    }
                });
    }

    public interface LoginCallback{
        void callback(JSONObject joResult);
    }
}
