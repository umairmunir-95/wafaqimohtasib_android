package com.wafaqimohtasib.mohstasibapp.data.remote.models.EmailSender;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class To {
    @SerializedName("email")
    @Expose
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
