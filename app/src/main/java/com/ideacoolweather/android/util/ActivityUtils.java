package com.ideacoolweather.android.util;

import android.app.Activity;

import android.content.Intent;

import com.ideacoolweather.android.activity.PlayActivity;
import com.ideacoolweather.android.activity.WeatherActivity;

/**
 * Created by idea on 2016/3/8.
 */
public class ActivityUtils implements ConstsUtil{
    //进入天气详情界面
    public static void goWeatherActivity(Activity mActivity, String weatherId) {
        Intent intent = new Intent(mActivity, WeatherActivity.class);
        intent.putExtra(PARAM_KEY_WEATHERID, weatherId);
        mActivity.startActivity(intent);
    }
    //从首页直接进入天气详情界面 不传递如何参数
    public static void goWeatherActivity(Activity mActivity) {
        Intent intent = new Intent(mActivity, WeatherActivity.class);
        mActivity.startActivity(intent);
    }

    //进入播放
    public static void goPlayActivity(Activity mActivity) {
        Intent intent = new Intent(mActivity, PlayActivity.class);
        mActivity.startActivity(intent);
    }
}
