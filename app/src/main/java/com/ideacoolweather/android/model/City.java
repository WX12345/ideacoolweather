package com.ideacoolweather.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.litepal.crud.DataSupport;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by idea on 2/16 0016.
 * 城市
 */
@Entity
public class City{

    @Id(autoincrement = true)
    private Long cityId;      //数据库里记录城市的编号
    public int provinceId;   //记录当前城市所属省的id值
    @SerializedName("name")@Expose
    public String cityName;  //城市名字
    @SerializedName("id")@Expose
    public int cityCode;     //城市代号
    public int getCityCode() {
        return this.cityCode;
    }
    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }
    public String getCityName() {
        return this.cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public int getProvinceId() {
        return this.provinceId;
    }
    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
    public Long getCityId() {
        return this.cityId;
    }
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
    @Generated(hash = 1183614044)
    public City(Long cityId, int provinceId, String cityName, int cityCode) {
        this.cityId = cityId;
        this.provinceId = provinceId;
        this.cityName = cityName;
        this.cityCode = cityCode;
    }
    @Generated(hash = 750791287)
    public City() {
    }


}
