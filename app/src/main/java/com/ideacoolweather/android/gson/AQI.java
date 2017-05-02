package com.ideacoolweather.android.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by idea on 2/17 0017.
 */

public class AQI{
    @SerializedName("city")@Expose
    public AQICity city;
    public class AQICity{
        @SerializedName("aqi")@Expose
        public String aqi;
        @SerializedName("pm25")@Expose
        public String pm25;
    }
}
