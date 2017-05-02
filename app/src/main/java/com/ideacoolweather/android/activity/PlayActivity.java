package com.ideacoolweather.android.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import butterknife.OnPageChange;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


public class PlayActivity extends BaseActivity {

    private VideoView mVideoView;
    private MediaController mMediaController;
    String path1 = "";   //播放地址

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_play);
        mVideoView = (VideoView) findViewById(R.id.video_view);
        mVideoView.setVideoPath(path1);//设置播放地址
        mMediaController = new MediaController(this);//实例化控制器
        mMediaController.show(5000);//控制器显示5s后自动隐藏
        mVideoView.setMediaController(mMediaController);//绑定控制器
        mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//设置播放画质 高画质
        mVideoView.requestFocus();//取得焦点
    }
}
