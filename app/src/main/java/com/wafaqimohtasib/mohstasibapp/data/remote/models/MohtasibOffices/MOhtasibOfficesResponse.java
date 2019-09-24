package com.wafaqimohtasib.mohstasibapp.data.remote.models.MohtasibOffices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MOhtasibOfficesResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("officeName")
    @Expose
    private String officeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

}
