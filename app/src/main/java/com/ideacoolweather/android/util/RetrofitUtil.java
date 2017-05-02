package com.ideacoolweather.android.util;

import com.ideacoolweather.android.model.Province;
import com.ideacoolweather.android.service.ApiService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

/**
 * Created by idea on 3/8 0008.
 */

public class RetrofitUtil implements ConstsUtil{

    public static final int DEFAULT_TIMEOUT = 5;
    private Retrofit mRetrofit;
    private ApiService mApiService;
    private static RetrofitUtil mInstance;

    /**
     * 私有构造方法
     */
    private RetrofitUtil(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mApiService = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                 .build()
                .create(ApiService.class);
    }

    public static RetrofitUtil getInstance(){
        if (mInstance == null){
            synchronized (RetrofitUtil.class){
                mInstance = new RetrofitUtil();
            }
        }
        return mInstance;
    }


    public ApiService getApiService() {
        return mApiService;
    }
}
