package com.wafaqimohtasib.mohstasibapp.data.remote.models.Complaints.ComplaintApproveReject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApproveRejectRequest {

    @SerializedName("status")
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
