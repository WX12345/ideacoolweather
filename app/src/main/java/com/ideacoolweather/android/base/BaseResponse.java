package com.ideacoolweather.android.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by idea on 3/8 0008.
 * 统一的返回数据格式，采用泛型
 */

public class BaseResponse<T> implements Serializable {

    @SerializedName("status")
    @Expose
    public int status;
    @SerializedName("data")
    @Expose
    public T data;

}
