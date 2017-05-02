package com.ideacoolweather.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.litepal.crud.DataSupport;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by idea on 2/16 0016.
 */
@Entity
public class County extends DataSupport{
    @SerializedName("id") @Expose
    public int CountyId;
    @SerializedName("name") @Expose
    public String countyName; //县的名字
    @SerializedName("weather_id") @Expose
    public String weatherId;  //县所对应的天气id

    private int cityId;        //县所属市的id

    public int getCityId() {
        return this.cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getWeatherId() {
        return this.weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public String getCountyName() {
        return this.countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getCountyId() {
        return this.CountyId;
    }

    public void setCountyId(int CountyId) {
        this.CountyId = CountyId;
    }

    @Generated(hash = 575516517)
    public County(int CountyId, String countyName, String weatherId, int cityId) {
        this.CountyId = CountyId;
        this.countyName = countyName;
        this.weatherId = weatherId;
        this.cityId = cityId;
    }

    @Generated(hash = 1991272252)
    public County() {
    }

}
