package com.ideacoolweather.android.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by idea on 2/17 0017.
 */

public class Suggestion {
    @SerializedName("comf")@Expose
    public Comfort comfort;

    @SerializedName("cw")@Expose
    public CarWash carWash;
    @SerializedName("sport")@Expose
    public Sport sport;

    public class Comfort{
        @SerializedName("txt")@Expose
        public String info;
    }
    public class CarWash{
        @SerializedName("txt")@Expose
        public String info;
    }
    public class Sport{
        @SerializedName("txt")@Expose
        public String info;
    }
}
