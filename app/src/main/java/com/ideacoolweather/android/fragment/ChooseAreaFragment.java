package com.ideacoolweather.android.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ideacoolweather.android.R;
import com.ideacoolweather.android.activity.MainActivity;
import com.ideacoolweather.android.activity.WeatherActivity;
import com.ideacoolweather.android.adapter.MainAdapter;
import com.ideacoolweather.android.base.BaseFragment;
import com.ideacoolweather.android.model.City;
import com.ideacoolweather.android.model.County;
import com.ideacoolweather.android.model.Province;
import com.ideacoolweather.android.mvp.presenter.fragment.ChooseAreaPresenter;
import com.ideacoolweather.android.mvp.view.fragment.ChooseAreaView;
import com.ideacoolweather.android.util.ActivityUtils;
import com.ideacoolweather.android.view.MyDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import greendao.util.DaoUtils;

/**
 * Created by idea on 2/16 0016.
 */

public class ChooseAreaFragment extends BaseFragment<ChooseAreaView, ChooseAreaPresenter> implements ChooseAreaView {

    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.back_button)
    Button backButton;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private MainAdapter mAdapter;

    /**
     * 省列表
     */
    private List<Province> provinceList = new ArrayList<>();

    /**
     * 市列表
     */
    private List<City> cityList = new ArrayList<>();

    /**
     * 县列表
     */
    private List<County> countyList = new ArrayList<>();

    /**
     * 选中的省份
     */
    private Province selectedProvince;
    /**
     * 选中的城市
     */
    private City selectedCity;
    /**
     * 当前选中的级别
     */
    private int currentLevel;


    @Override
    public ChooseAreaPresenter initPresenter() {
        return new ChooseAreaPresenter();
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.choose_area;
    }

    @Override
    protected void initViews() {
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.addItemDecoration(new MyDecoration(mActivity, MyDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentLevel == LEVEL_COUNTY) {
                    presenter.queryCities(selectedProvince);
                } else if (currentLevel == LEVEL_CITY) {
                    presenter.queryProvinces();
                }
            }
        });
        DaoUtils.init(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.queryProvinces();
    }

    @Override
    public void setTitle(int type, String title) {
        titleText.setText(title);
        if (type == 0) {
            backButton.setVisibility(View.GONE);
        }
        if (type == 1) {
            backButton.setVisibility(View.VISIBLE);
        }
        if (type == 2) {
            backButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setRecyclerTitle(int type, List<String> mDatas) {
        if (type == 0) {
            currentLevel = LEVEL_PROVINCE;
        }
        if (type == 1) {
            currentLevel = LEVEL_CITY;
        }
        if (type == 2) {
            currentLevel = LEVEL_COUNTY;
        }
        mAdapter = new MainAdapter(mActivity, mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        mAdapter.setOnItemClickLitener(new MainAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                if (currentLevel == LEVEL_PROVINCE) {
                    selectedProvince = provinceList.get(position);
                    presenter.queryCities(selectedProvince);
                } else if (currentLevel == LEVEL_CITY) {
                    selectedCity = cityList.get(position);
                    presenter.queryCounties(selectedProvince.provinceCode, selectedCity);
                } else if (currentLevel == LEVEL_COUNTY) {
                    String weatherId = countyList.get(position).weatherId;
                    if (getActivity() instanceof MainActivity) {
                        ActivityUtils.goWeatherActivity(mActivity, weatherId);
                    } else if (getActivity() instanceof WeatherActivity) {
                        WeatherActivity activity = (WeatherActivity) getActivity();
                        activity.drawerLayout.closeDrawers();
                        activity.swipeRefresh.setRefreshing(true);
                        activity.presenter.getWeather(weatherId);
                    }
                }

            }
        });
    }

    @Override
    public void setProvinces(List<Province> provinceList) {
        this.provinceList = provinceList;
    }

    @Override
    public void setCitys(List<City> cityList) {
        this.cityList = cityList;
    }

    @Override
    public void setCountys(List<County> countyList) {
        this.countyList = countyList;
    }
}
