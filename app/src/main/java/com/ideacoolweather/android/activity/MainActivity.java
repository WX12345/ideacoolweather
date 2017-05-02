package com.ideacoolweather.android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ideacoolweather.android.R;
import com.ideacoolweather.android.adapter.ViewPagerAdapter;
import com.ideacoolweather.android.base.BaseActivity;
import com.ideacoolweather.android.base.BaseFragment;
import com.ideacoolweather.android.fragment.ChooseAreaFragment;
import com.ideacoolweather.android.fragment.JokeFragment;
import com.ideacoolweather.android.fragment.MyFragment;
import com.ideacoolweather.android.fragment.PlayFragment;
import com.ideacoolweather.android.mvp.base.BasePresenter;
import com.ideacoolweather.android.util.ActivityUtils;
import com.ideacoolweather.android.util.EmptyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnPageChange;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tab_weather)
    RadioButton tabWeather;
    @BindView(R.id.tab_play)
    RadioButton tabPlay;
    @BindView(R.id.tab_joke)
    RadioButton tabJoke;
    @BindView(R.id.tab_my)
    RadioButton tabMy;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    private BaseFragment mChooseAreaFragment, mPlayFragment, mJokeFragment, mMyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mChooseAreaFragment = new ChooseAreaFragment();
        mPlayFragment = new PlayFragment();
        mJokeFragment = new JokeFragment();
        mMyFragment = new MyFragment();
        List<Fragment> listFragment = new ArrayList<>();
        listFragment.add(mChooseAreaFragment);
        listFragment.add(mPlayFragment);
        listFragment.add(mJokeFragment);
        listFragment.add(mMyFragment);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), listFragment));
        //ViewPager显示第一个Fragment
        viewPager.setCurrentItem(0);
        tabWeather.setChecked(true);
        radioGroup.setOnCheckedChangeListener(this);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (EmptyUtils.isNotEmpty(prefs.getString("weather", null))) {
            ActivityUtils.goWeatherActivity(mActivity);
        }
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }


    @OnPageChange(value = R.id.viewPager, callback = OnPageChange.Callback.PAGE_SCROLLED)
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @OnPageChange(value = R.id.viewPager, callback = OnPageChange.Callback.PAGE_SELECTED)
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                radioGroup.check(R.id.tab_weather);
                break;
            case 1:
                radioGroup.check(R.id.tab_play);
                break;
            case 2:
                radioGroup.check(R.id.tab_joke);
                break;
            case 3:
                radioGroup.check(R.id.tab_my);
                break;
        }
    }

    @OnPageChange(value = R.id.viewPager, callback = OnPageChange.Callback.PAGE_SCROLL_STATE_CHANGED)
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.tab_weather:
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.tab_play:
                viewPager.setCurrentItem(1, false);
                break;
            case R.id.tab_joke:
                viewPager.setCurrentItem(2, false);
                break;
            case R.id.tab_my:
                viewPager.setCurrentItem(3, false);
                break;
        }
    }

    /**
     * RadioGroup选中状态改变监听
     * setCurrentItem第二个参数控制页面切换动画
     * true:打开/false:关闭
     */

//    @OnCheckedChanged({R.id.tab_weather, R.id.tab_play, R.id.tab_joke, R.id.tab_my})
//    public void onCheckedChanged(boolean checked) {
//            switch (radioGroup.getId()) {
//                case R.id.tab_weather:
//                    viewPager.setCurrentItem(0, false);
//                    break;
//                case R.id.tab_play:
//                    viewPager.setCurrentItem(1, false);
//                    break;
//                case R.id.tab_joke:
//                    viewPager.setCurrentItem(2, false);
//                    break;
//                case R.id.tab_my:
//                    viewPager.setCurrentItem(3, false);
//                    break;
//        }
//    }

}
