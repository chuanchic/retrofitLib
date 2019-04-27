package com.github.retrofitlib;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求工厂
 */
public class RetrofitFactory extends com.github.retrofitlibrary.RetrofitFactory {

    private static RetrofitService retrofitService = new Retrofit.Builder()
            .baseUrl(MyUrlConfig.BASE_URL_1)
            // 添加Gson转换器
            .addConverterFactory(GsonConverterFactory.create())
            // 添加Retrofit到RxJava的转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
            .create(RetrofitService.class);

    public static RetrofitService getService() {
        return retrofitService;
    }
}
