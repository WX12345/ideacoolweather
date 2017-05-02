package com.ideacoolweather.android.mvp.presenter.fragment;

import com.ideacoolweather.android.model.Girls;
import com.ideacoolweather.android.mvp.base.BasePresenter;
import com.ideacoolweather.android.mvp.view.fragment.PlayView;
import com.ideacoolweather.android.service.ApiService;
import com.ideacoolweather.android.util.ConstsUtil;
import com.ideacoolweather.android.util.TransformUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by idea on 4/7 0007.
 * 控制省 市 县数据的Presenter类
 */

public class PlayPresenter extends BasePresenter<PlayView> {

    private List<Girls.Girl> girlList = new ArrayList<>();

    public void getGirl(final int type, int page) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        ApiService mApiService = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(ConstsUtil.URL_GIRL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(ApiService.class);
        mApiService.getGirls(page).flatMap(new Func1<Girls, Observable<Girls.Girl>>() {
            @Override
            public Observable<Girls.Girl> call(Girls girls) {
                return Observable.from(girls.results);
            }
        }).compose(TransformUtils.<Girls.Girl>defaultSchedulers())
                .subscribe(new Subscriber<Girls.Girl>() {
                    @Override
                    public void onCompleted() {
                        mView.setRecyclerGirl(type, girlList);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Girls.Girl girl) {
                        if (type==1){
                            girlList.add(0,girl);
                        }else{
                            girlList.add(girl);
                        }
                    }
                });
    }


}
