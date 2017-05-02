package com.ideacoolweather.android.mvp.view.fragment;

import android.os.Message;

import com.ideacoolweather.android.model.City;
import com.ideacoolweather.android.model.County;
import com.ideacoolweather.android.model.Province;
import com.ideacoolweather.android.mvp.base.BaseMvpView;

import java.util.List;

/**
 * Created by idea on 4/7 0007.
 * 提供接口
 */

public interface ChooseAreaView extends BaseMvpView {

    //显示title的信息
    void setTitle(int type, String title);

    //将获得的数据填充到适配器中
    void setRecyclerTitle(int type, List<String> mDatas);

    /**
     * 将获取到的省 市 县 传递到fragment
     */
    void setProvinces(List<Province> provinceList);

    void setCitys(List<City> cityList);

    void setCountys(List<County> countyList);
}
