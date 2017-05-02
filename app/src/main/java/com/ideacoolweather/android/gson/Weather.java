package com.ideacoolweather.android.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by idea on 2/17 0017.
 */

public class Weather{

        @SerializedName("HeWeather") @Expose
        public List<HeWeather> heWeathers=new ArrayList<>();

        public class HeWeather {
                @SerializedName("status")
                @Expose
                public String status;

                @SerializedName("basic")
                @Expose
                public Basic basic;

                @SerializedName("aqi")
                @Expose
                public AQI aqi;

                @SerializedName("now")
                @Expose
                public Now now;

                @SerializedName("suggestion")
                @Expose
                public Suggestion suggestion;

                @SerializedName("daily_forecast")
                @Expose
                public List<Forecast> forecastList;
        }
}

