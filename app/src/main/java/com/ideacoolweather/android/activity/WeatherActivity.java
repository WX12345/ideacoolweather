package com.ideacoolweather.android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ideacoolweather.android.R;
import com.ideacoolweather.android.base.BaseActivity;
import com.ideacoolweather.android.gson.Forecast;
import com.ideacoolweather.android.gson.Weather;
import com.ideacoolweather.android.mvp.presenter.activity.WeatherPresenter;
import com.ideacoolweather.android.mvp.view.activity.WeatherView;
import com.ideacoolweather.android.service.AutoUpdateService;
import com.ideacoolweather.android.util.ConstsUtil;
import com.ideacoolweather.android.util.EmptyUtils;
import com.ideacoolweather.android.util.HttpUtil;
import com.ideacoolweather.android.util.ImageLoaderUtils;
import com.ideacoolweather.android.util.RetrofitUtil;
import com.ideacoolweather.android.util.TransformUtils;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class WeatherActivity extends BaseActivity<WeatherView, WeatherPresenter> implements WeatherView {


    @BindView(R.id.title_city)
    TextView titleCity;
    @BindView(R.id.title_update_time)
    TextView titleUpdateTime;
    @BindView(R.id.forecast_layout)
    LinearLayout forecastLayout;
    @BindView(R.id.aqi_text)
    TextView aqiText;
    @BindView(R.id.pm25_text)
    TextView pm25Text;
    @BindView(R.id.comfort_text)
    TextView comfortText;
    @BindView(R.id.car_wash_text)
    TextView carWashText;
    @BindView(R.id.sport_text)
    TextView sportText;
    @BindView(R.id.weather_layout)
    ScrollView weatherLayout;
    @BindView(R.id.degree_text)
    TextView degreeText;
    @BindView(R.id.weather_info_text)
    TextView weatherInfoText;
    @BindView(R.id.bing_pic_img)
    ImageView bingPicImg;
    @BindView(R.id.swipe_refresh)
    public SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.nav_button)
    Button navButton;
    @BindView(R.id.drawer_layout)
    public DrawerLayout drawerLayout;
    public String weatherId;
    private String bingPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        //5.0以上的系统会支持将状态栏设置成透明色
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //获取每日必应的图片
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        bingPic = prefs.getString("bing_pic", null);
        //获取weatherId
        weatherId = getIntent().getStringExtra("weather_id");

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        weatherLayout.setVisibility(View.INVISIBLE);
        //下拉刷新
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getWeather(weatherId);
            }
        });
        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public WeatherPresenter initPresenter() {
        return new WeatherPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getWeather(weatherId);
        if (EmptyUtils.isNotEmpty(bingPic)) {
           ImageLoaderUtils.show(mActivity,bingPicImg,bingPic);
        } else {
            presenter.loadBingPic();
        }
    }


    /**
     * 获取天气详情信息出错
     */
    @Override
    public void weatherError() {
        Toast.makeText(getApplicationContext(), "获取天气信息失败",
                Toast.LENGTH_SHORT).show();
        swipeRefresh.setRefreshing(false);
    }

    /**
     * 处理并展示Weather实体类中的数据
     */
    @Override
    public void showWeatherInfo(Weather.HeWeather heWeather,String weatherId) {
        this.weatherId=weatherId;
        if (EmptyUtils.isNotEmpty(heWeather) && "ok".equals(heWeather.status)) {
            String cityName = heWeather.basic.cityName;
            String updateName = heWeather.basic.update.updateTime.split(" ")[1];
            String degree = heWeather.now.temperature + "℃";
            String weatherInfo = heWeather.now.more.info;

            titleCity.setText(cityName);
            titleUpdateTime.setText(updateName);
            degreeText.setText(degree);
            weatherInfoText.setText(weatherInfo);
            forecastLayout.removeAllViews();
            for (Forecast forecast : heWeather.forecastList) {
                View view = LayoutInflater.from(mActivity).inflate(R.layout.forecast_item,
                        forecastLayout, false);

                TextView dateText = (TextView) view.findViewById(R.id.date_text);
                TextView infoText = (TextView) view.findViewById(R.id.info_text);
                TextView maxText = (TextView) view.findViewById(R.id.max_text);
                TextView minText = (TextView) view.findViewById(R.id.min_text);

                dateText.setText(forecast.date);
                infoText.setText(forecast.more.info);
                maxText.setText(forecast.temperature.max);
                minText.setText(forecast.temperature.min);
                forecastLayout.addView(view);
            }
            if (EmptyUtils.isNotEmpty(heWeather.aqi)) {
                aqiText.setText(heWeather.aqi.city.aqi);
                pm25Text.setText(heWeather.aqi.city.pm25);
            }
            String comfort = "舒适度: " + heWeather.suggestion.comfort.info;
            String carWash = "洗车指数: " + heWeather.suggestion.carWash.info;
            String sport = "运动建议: " + heWeather.suggestion.sport.info;

            comfortText.setText(comfort);
            carWashText.setText(carWash);
            sportText.setText(sport);
            weatherLayout.setVisibility(View.VISIBLE);
            Intent intent = new Intent(this, AutoUpdateService.class);
            startService(intent);
        } else {
            Toast.makeText(WeatherActivity.this, "获取天气信息失败",
                    Toast.LENGTH_SHORT).show();
        }
        swipeRefresh.setRefreshing(false);
    }

    /**
     * 获取必应每日一图
     *
     */
    @Override
    public void bingPicSuccess(String bingPicUrl) {
        SharedPreferences.Editor editor = PreferenceManager.
                getDefaultSharedPreferences(mActivity).edit();
        editor.putString("bing_pic", bingPicUrl);
        editor.apply();
        ImageLoaderUtils.show(mActivity,bingPicImg,bingPicUrl);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
