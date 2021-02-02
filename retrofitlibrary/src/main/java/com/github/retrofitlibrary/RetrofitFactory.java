package com.github.retrofitlibrary;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 网络请求工厂
 */
public class RetrofitFactory {
    protected static final String TAG = RetrofitFactory.class.getName();
    protected static final long TIMEOUT = 30;

    // Retrofit是基于OkHttpClient的，可以创建一个OkHttpClient进行一些配置
    protected static OkHttpClient httpClient = new OkHttpClient.Builder()
            // 添加通用的Header
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder builder = chain.request().newBuilder();
                    //builder.addHeader("token", "123");// 替换为自己的token
                    //builder.addHeader("USER_AGENT", "SXK_ANDROID");
                    return chain.proceed(builder.build());
                }
            })
            /*
            这里可以添加一个HttpLoggingInterceptor，因为Retrofit封装好了从Http请求到解析，
            出了bug很难找出来问题，添加HttpLoggingInterceptor拦截器方便调试接口
             */
            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.i(TAG, "HttpLoggingInterceptor  log=" + message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BASIC))
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build();

}
