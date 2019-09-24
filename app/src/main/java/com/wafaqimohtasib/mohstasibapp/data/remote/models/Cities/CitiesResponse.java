package com.wafaqimohtasib.mohstasibapp.data.remote.models.Cities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CitiesResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cityName")
    @Expose
    private String cityName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
