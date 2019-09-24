package com.wafaqimohtasib.mohstasibapp.data.remote.models.EmailSender;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Personalization {
    @SerializedName("to")
    @Expose
    private List<To> to = null;
    @SerializedName("subject")
    @Expose
    private String subject;

    public List<To> getTo() {
        return to;
    }

    public void setTo(List<To> to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
