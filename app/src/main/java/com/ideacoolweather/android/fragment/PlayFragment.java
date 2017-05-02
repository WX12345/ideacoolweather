package com.ideacoolweather.android.fragment;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.ideacoolweather.android.R;
import com.ideacoolweather.android.activity.MainActivity;
import com.ideacoolweather.android.activity.WeatherActivity;
import com.ideacoolweather.android.adapter.PlayAdapter;
import com.ideacoolweather.android.base.BaseFragment;
import com.ideacoolweather.android.model.Girls;
import com.ideacoolweather.android.mvp.presenter.fragment.PlayPresenter;
import com.ideacoolweather.android.mvp.view.fragment.PlayView;
import com.ideacoolweather.android.util.ActivityUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by idea on 2/16 0016.
 */

public class PlayFragment extends BaseFragment<PlayView, PlayPresenter> implements PlayView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swipe_target)
    RecyclerView mRecyclerView;
    @BindView(R.id.grid_coordinatorLayout)
    CoordinatorLayout gridCoordinatorLayout;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    private GridLayoutManager mLayoutManager;
    private PlayAdapter mPlayAdapter;

    private int REFRESH=1;  //刷新
    private int LOADMORE=2;  //加载
    private int ONE_DATA=0;   //第一次进入界面获取数据

    private int page=1;      //当前页码
    @Override
    public PlayPresenter initPresenter() {
        return new PlayPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_play;
    }

    @Override
    protected void initViews(){
        super.initViews();
        mLayoutManager = new GridLayoutManager(mActivity, 3, GridLayoutManager.VERTICAL, false);//设置为一个3列的纵向网格布局
        mRecyclerView.setLayoutManager(mLayoutManager);

        //下拉刷新
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=page+1;
                presenter.getGirl(REFRESH,page);
            }
        });
        //上拉加载
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page=page+1;
                presenter.getGirl(LOADMORE,page);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getGirl(ONE_DATA,page);
    }


    @Override
    public void setRecyclerGirl(int type,List<Girls.Girl> girls) {
        mPlayAdapter = new PlayAdapter(mActivity, girls);
        if (type==0){
            mRecyclerView.setAdapter(mPlayAdapter);
        }else if (type==1){  //下拉刷新

            mPlayAdapter.notifyDataSetChanged();
            swipeToLoadLayout.setRefreshing(false);
        }else{                //上拉加载
            mPlayAdapter.notifyDataSetChanged();
            swipeToLoadLayout.setLoadingMore(false);
        }
        mPlayAdapter.setOnItemClickLitener(new PlayAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mActivity, "暂时没有播放地址",
                        Toast.LENGTH_SHORT).show();
                //ActivityUtils.goPlayActivity(mActivity);
            }
        });
    }
}
