package com.ideacoolweather.android.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by idea on 2/17 0017.
 */

public class Forecast {
    @SerializedName("date")@Expose
    public String date;

    @SerializedName("tmp")@Expose
    public Temperature temperature;

    @SerializedName("cond")@Expose
    public More more;

    public class Temperature{
        @SerializedName("max")@Expose
        public String max;
        @SerializedName("min")@Expose
        public String min;
    }
    public class More{
        @SerializedName("txt_d")@Expose
        public String info;
    }
}
