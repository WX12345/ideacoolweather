package com.ideacoolweather.android.mvp.presenter.activity;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ideacoolweather.android.activity.WeatherActivity;
import com.ideacoolweather.android.gson.Weather;
import com.ideacoolweather.android.mvp.base.BasePresenter;
import com.ideacoolweather.android.mvp.view.activity.WeatherView;
import com.ideacoolweather.android.service.ApiService;
import com.ideacoolweather.android.util.ConstsUtil;
import com.ideacoolweather.android.util.EmptyUtils;
import com.ideacoolweather.android.util.HttpUtil;
import com.ideacoolweather.android.util.RetrofitUtil;
import com.ideacoolweather.android.util.TransformUtils;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by idea on 4/28 0028.
 * 获取天气详情数据的Presenter
 */

public class WeatherPresenter extends BasePresenter<WeatherView> {

    /**
     * 根据天气id请求城市天气信息
     */
    public void getWeather(final String weatherId) {
        RetrofitUtil.getInstance().getApiService().getWeather(weatherId, ConstsUtil.KEY)
                .flatMap(new Func1<Weather, Observable<Weather.HeWeather>>() {
                    @Override
                    public Observable<Weather.HeWeather> call(Weather weather) {
                        return Observable.from(weather.heWeathers);
                    }
                }).compose(TransformUtils.<Weather.HeWeather>defaultSchedulers())
                .subscribe(new Subscriber<Weather.HeWeather>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.weatherError();
                    }
                    @Override
                    public void onNext(Weather.HeWeather heWeather) {
                        if (EmptyUtils.isNotEmpty(heWeather) && "ok".
                                equals(heWeather.status)) {
                            mView.showWeatherInfo(heWeather,weatherId);
                        } else {
                            mView.weatherError();
                        }
                    }
                });
    }

    /**
     * 加载必应每日一图
     */
    public void loadBingPic() {
        RetrofitUtil.getInstance().getApiService().getBingPic()
                .compose(TransformUtils.<String>defaultSchedulers())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(String bingPicUrl) {
                        mView.bingPicSuccess(bingPicUrl);
                    }
                });
    }

}
