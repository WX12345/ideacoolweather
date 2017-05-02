package com.ideacoolweather.android.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by idea on 2/17 0017.
 */

public class Basic {

    @SerializedName("city")
    @Expose
    public String cityName;  //城市id
    @SerializedName("id")
    @Expose
    public String weatherId;  //天气id
    @SerializedName("update")
    @Expose
    public Update update;

    public class Update {
        @SerializedName("loc")
        @Expose
        public String updateTime;
    }
}

