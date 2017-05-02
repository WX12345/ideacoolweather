package com.ideacoolweather.android.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by idea on 2/16 0016.
 * 存放省的信息
 */
@Entity
public class Province{
    @Id(autoincrement = true)
    private Long provinceId;
    @SerializedName("name") @Expose
    public String provinceName;  //省名
    @SerializedName("id") @Expose
    public int provinceCode;     //省的代号
    public int getProvinceCode() {
        return this.provinceCode;
    }
    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
    public String getProvinceName() {
        return this.provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    public Long getProvinceId() {
        return this.provinceId;
    }
    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }
    @Generated(hash = 428654564)
    public Province(Long provinceId, String provinceName, int provinceCode) {
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.provinceCode = provinceCode;
    }
    @Generated(hash = 1309009906)
    public Province() {
    }


}
