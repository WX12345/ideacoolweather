package com.ideacoolweather.android.mvp.view.activity;

import com.ideacoolweather.android.gson.Weather;
import com.ideacoolweather.android.mvp.base.BaseMvpView;

/**
 * Created by idea on 4/28 0028.
 * 天气详情界面的View
 */

public interface WeatherView extends BaseMvpView{

    void weatherError();
    void showWeatherInfo(Weather.HeWeather heWeather,String weatherId);
    void bingPicSuccess(String bingPicUrl);


}
