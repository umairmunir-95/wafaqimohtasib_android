package com.wafaqimohtasib.mohstasibapp.data.remote.models.Agencies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AgenciesResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("agencyName")
    @Expose
    private String agencyName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
}
