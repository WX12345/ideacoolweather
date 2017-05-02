package com.ideacoolweather.android.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ideacoolweather.android.R;
import com.ideacoolweather.android.base.BaseActivity;

/**
 * Created by wx on 2016/1/15.
 */
public class ImageLoaderUtils implements Handler.Callback {
    /**
     * 显示图片 - 小
     * 60 * 60
     */
 //   @BindingAdapter({"image"})
    public static void show(Context mContext,ImageView iv, String uri) {
        Glide.with(mContext)
                .load(uri)
               // .centerCrop()
               // .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .crossFade()
                .into(iv);
    }

    public static void showPictureWithApplication(Context mContext, String uri, ImageView iv) {
        if (mContext instanceof BaseActivity) {
            if (((BaseActivity) mContext).isFinishing())
                return;
        }
        Glide.with(mContext)
                .load(uri)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .crossFade()
                .into(iv);
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }


}
