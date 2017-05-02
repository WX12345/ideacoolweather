package com.ideacoolweather.android.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentActivity;

import com.ideacoolweather.android.IdeaApplication;
import com.ideacoolweather.android.mvp.base.BaseMvpView;
import com.ideacoolweather.android.mvp.base.BasePresenter;
import com.ideacoolweather.android.util.EmptyUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by idea on 3/23 0023.
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends FragmentActivity implements BaseMvpView {
    protected BaseActivity mActivity;
    private ProgressDialog progressDialog;    //加载提示框
    public T presenter;                       //对应的Presenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        presenter = initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.attach((V) this);
        }
    }

    //实例化 Presenter
    public abstract T initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettach();
        }
    }

    /**
     * 显示进度对话框
     */
    @Override
    public void showLoading() {
        if (EmptyUtils.isEmpty(progressDialog)) {
            progressDialog = new ProgressDialog(mActivity);
            progressDialog.setMessage("正在加载......");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    /**
     * 关闭进度对话框
     */
    @Override
    public void hideLoading() {
        if (EmptyUtils.isNotEmpty(progressDialog)) {
            progressDialog.dismiss();
        }
    }

}
