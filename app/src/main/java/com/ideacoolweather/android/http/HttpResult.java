package com.ideacoolweather.android.http;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by idea on 3/22 0022.
 */

public class HttpResult<T> {
    @SerializedName("HeWeather") @Expose
    public T results;
}
