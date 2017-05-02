package com.ideacoolweather.android.mvp.view.fragment;

import com.ideacoolweather.android.model.City;
import com.ideacoolweather.android.model.County;
import com.ideacoolweather.android.model.Girls;
import com.ideacoolweather.android.model.Province;
import com.ideacoolweather.android.mvp.base.BaseMvpView;

import java.util.List;

/**
 * Created by idea on 4/7 0007.
 * 提供接口
 */

public interface PlayView extends BaseMvpView {
    //将获得的图片填充到适配器中
    void setRecyclerGirl(int type,List<Girls.Girl> girls);
}
