package com.github.retrofitlib;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 网络请求接口
 */
public interface RetrofitService {

    /**
     * 登录
     */
    @GET
    Observable<ResponseBody> login(
            @Url String url,
            @Query("login_type") String login_type,
            @Query("verifycode") String verifycode,
            @Query("tel") String tel,
            @Query("from") String from,
            @Query("deviceToken") String deviceToken,
            @Query("app_type") String app_type,
            @Query("front_key_name") String front_key_name
    );

}
