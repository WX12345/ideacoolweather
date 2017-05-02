package com.ideacoolweather.android.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ideacoolweather.android.mvp.base.BaseMvpView;
import com.ideacoolweather.android.mvp.base.BasePresenter;
import com.ideacoolweather.android.util.EmptyUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment implements BaseMvpView {

    private static final String TAG = "BaseFragment";
    private View mView;                       // 控件句柄
    protected BaseActivity mActivity;         // 对应的Activity
    public T presenter;                       //对应的Presenter
    protected boolean bInitView = false;      // 是否初始化了控件
    private boolean isImplementInterface;     //判断当前的V是否继承MvpPlayView
    private Unbinder unbinder;                //用来调用ButterKnife
    private ProgressDialog progressDialog;    //加载提示框

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bInitView = true;
        mView = inflater.inflate(getLayoutResID(), container, false);
        unbinder = ButterKnife.bind(this, mView);
        initViews();
        presenter = initPresenter();
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.attach((V) this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (presenter != null) {
            presenter.dettach();
        }
    }

    //实例化 Presenter
    public abstract T initPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 设置布局文件
     */
    protected int getLayoutResID() {
        return 0;
    }

    /**
     * 初始化布局控件
     */
    protected void initViews() {

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

    /**
     * 查找控件
     */
    protected View findViewById(int id) {
        return mView.findViewById(id);
    }
}
