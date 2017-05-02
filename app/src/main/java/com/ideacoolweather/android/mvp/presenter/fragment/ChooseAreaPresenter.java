package com.ideacoolweather.android.mvp.presenter.fragment;

import com.ideacoolweather.android.model.City;
import com.ideacoolweather.android.model.County;
import com.ideacoolweather.android.model.Province;
import com.ideacoolweather.android.mvp.base.BasePresenter;
import com.ideacoolweather.android.mvp.view.fragment.ChooseAreaView;
import com.ideacoolweather.android.util.RetrofitUtil;
import com.ideacoolweather.android.util.TransformUtils;

import java.util.ArrayList;
import java.util.List;

import greendao.util.DaoUtils;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by idea on 4/7 0007.
 * 控制省 市 县数据的Presenter类
 */

public class ChooseAreaPresenter extends BasePresenter<ChooseAreaView> {

    private static int PROVICE=0;
    private static int CITY=1;
    private static int COUNTY=2;

    /**
     * 用来装载省 市 县的名字的集合
     */
    private List<String> dataList = new ArrayList<>();

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
     * 查询全国所有的省,优先从数据库查询，如果没有查询到再去服务器上查询
     */
    public void queryProvinces() {
        mView.setTitle(PROVICE,"中国");
        provinceList = DaoUtils.getProvinceManager().queryAll(Province.class);
        if (provinceList.size() > 0) {
            dataList.clear();
            for (Province province : provinceList) {
                dataList.add(province.provinceName);
            }
            mView.setProvinces(provinceList);
            mView.setRecyclerTitle(PROVICE,dataList);
        } else {
            getProvinceNet();
        }
    }

    /**
     * 获取省的数据
     */
    public void getProvinceNet() {
        mView.showLoading();
        RetrofitUtil.getInstance().getApiService().getProvince()
                .flatMap(new Func1<List<Province>, Observable<Province>>() {
                    @Override
                    public Observable<Province> call(List<Province> provinces) {
                        return Observable.from(provinces);
                    }
                }).compose(TransformUtils.<Province>defaultSchedulers())
                .subscribe(new Subscriber<Province>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                        queryProvinces();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(Province province) {
                        Province mProvince = new Province(null, province.provinceName, province.provinceCode);
                        DaoUtils.getProvinceManager().insertObject(mProvince);
                    }
                });
    }

    /**
     * 查询选中省内所有的市，优先从数据库查询，如果没有查询到再去服务器上查询
     */
    public void queryCities(Province selectedProvince) {
        mView.setTitle(CITY,selectedProvince.provinceName);
        cityList = DaoUtils.getCityManager().queryObject(City.class,
                "WHERE T.PROVINCE_ID=?", String.valueOf(selectedProvince.provinceCode));
        if (cityList.size() > 0) {
            dataList.clear();
            for (City city : cityList) {
                dataList.add(city.cityName);
            }
            mView.setCitys(cityList);
            mView.setRecyclerTitle(CITY,dataList);
        } else {
            getCityNet(selectedProvince);
        }
    }

    /**
     * 获取城市的数据
     */
    private void getCityNet(final Province selectedProvince) {
        mView.showLoading();
        RetrofitUtil.getInstance().getApiService().getCity(selectedProvince.provinceCode)
                .flatMap(new Func1<List<City>, Observable<City>>() {
                    @Override
                    public Observable<City> call(List<City> cities) {
                        return Observable.from(cities);
                    }
                }).compose(TransformUtils.<City>defaultSchedulers())
                .subscribe(new Subscriber<City>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                        queryCities(selectedProvince);
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(City city) {
                        City mCity = new City(null, selectedProvince.provinceCode, city.cityName, city.cityCode);
                        DaoUtils.getCityManager().insertObject(mCity);
                    }
                });

    }

    /**
     * 查询选中市内所有的县，优先从数据库查询，如果没有查询到再去服务器上查询
     */
    public void queryCounties(int provinceCode,City selectedCity) {
        mView.setTitle(COUNTY,selectedCity.cityName);
        countyList = DaoUtils.getCountryManager().queryObject(County.class,
                "WHERE T.CITY_ID=?", String.valueOf(selectedCity.cityCode));
        if (countyList.size() > 0) {
            dataList.clear();
            for (County county : countyList) {
                dataList.add(county.countyName);
            }
            mView.setCountys(countyList);
            mView.setRecyclerTitle(COUNTY,dataList);
        } else {
            getCounties(provinceCode, selectedCity);
        }
    }

    /**
     * 获取县的数据
     */
    private void getCounties(final int provinceCode, final City selectedCity) {
        mView.showLoading();
        RetrofitUtil.getInstance().getApiService().getCounties(provinceCode, selectedCity.cityCode)
                .flatMap(new Func1<List<County>, Observable<County>>() {
                    @Override
                    public Observable<County> call(List<County> counties) {
                        return Observable.from(counties);
                    }
                }).compose(TransformUtils.<County>defaultSchedulers())
                .subscribe(new Subscriber<County>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                        queryCounties(provinceCode,selectedCity);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(County county) {
                        County mCounty = new County(county.CountyId, county.countyName, county.weatherId, selectedCity.cityCode);
                        DaoUtils.getCountryManager().insertObject(mCounty);
                    }
                });
    }

}
