package com.ideacoolweather.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by idea on 4/27 0027.
 */

public class Girls {
    @SerializedName("error") @Expose
    public String error;
    @SerializedName("results") @Expose
    public List<Girl> results;

    public class Girl{
        @SerializedName("url") @Expose
        public String url;
    }
}
