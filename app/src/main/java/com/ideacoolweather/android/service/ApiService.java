package com.ideacoolweather.android.service;

import com.ideacoolweather.android.gson.Weather;
import com.ideacoolweather.android.model.City;
import com.ideacoolweather.android.model.County;
import com.ideacoolweather.android.model.Girls;
import com.ideacoolweather.android.model.Province;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by idea on 3/8 0008.
 */

public interface ApiService {

    /**
     * 获取省的信息
     */

    @GET("api/china/")
    Observable<List<Province>> getProvince();

    /**
     * 获取城市的信息
     *
     * @param provinceCode
     */
    @GET("api/china/{provinceCode}")
    Observable<List<City>> getCity(@Path("provinceCode") int provinceCode);

    /**
     * 获取县的信息
     */

    @GET("api/china/{provinceCode}/{cityCode}")
    Observable<List<County>> getCounties(@Path("provinceCode") int provinceCode,
                                         @Path("cityCode") int cityCode);

    /**
     * 获取天气的信息
     * key=856c0a0484a4440eb90c3f0f4f36f194;
     */
    @GET("api/weather")
    Observable<Weather> getWeather(@Query("cityid") String weatherId,
                                     @Query("key") String key);

    /**
     * 获取妹子的图片
     * @return
     */
    @GET("api/data/福利/15/{page}")
    Observable<Girls> getGirls(@Path("page") int page);

    /**
     * 获取必应每日一图
     */
    @GET("api/bing_pic")
    Observable<String> getBingPic();
}
