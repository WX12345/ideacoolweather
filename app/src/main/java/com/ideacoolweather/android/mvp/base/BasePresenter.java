package com.ideacoolweather.android.mvp.base;


/**
 * Created by idea on 6/6 0006.
 */
public abstract class BasePresenter<T> {
    public T mView;

    public void attach(T mView) {
        this.mView = mView;
    }

    public void dettach() {
        mView = null;
    }


//    public Message errorMessage(VolleyError error) {
//        Message msg = new Message();
//        msg.obj = error;
//        return msg;
//    }
}
