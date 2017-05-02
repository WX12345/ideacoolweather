package com.ideacoolweather.android.gson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by idea on 2/17 0017.
 */

public class Now {

    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More{
        @SerializedName("txt")
        public String info;
    }

}
