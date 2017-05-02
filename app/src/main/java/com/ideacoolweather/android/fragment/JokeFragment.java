package com.ideacoolweather.android.fragment;

import com.ideacoolweather.android.R;
import com.ideacoolweather.android.base.BaseFragment;
import com.ideacoolweather.android.mvp.base.BasePresenter;

/**
 * Created by idea on 2/16 0016.
 */

public class JokeFragment extends BaseFragment{

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_joke;
    }
}
