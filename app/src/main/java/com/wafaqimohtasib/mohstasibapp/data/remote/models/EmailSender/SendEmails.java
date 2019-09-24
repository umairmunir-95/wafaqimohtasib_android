package com.wafaqimohtasib.mohstasibapp.data.remote.models.EmailSender;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SendEmails {
    @SerializedName("personalizations")
    @Expose
    private List<Personalization> personalizations = null;
    @SerializedName("content")
    @Expose
    private List<Content> content = null;
    @SerializedName("from")
    @Expose
    private From from;
    @SerializedName("reply_to")
    @Expose
    private ReplyTo replyTo;

    public List<Personalization> getPersonalizations() {
        return personalizations;
    }

    public void setPersonalizations(List<Personalization> personalizations) {
        this.personalizations = personalizations;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public ReplyTo getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(ReplyTo replyTo) {
        this.replyTo = replyTo;
    }
}
