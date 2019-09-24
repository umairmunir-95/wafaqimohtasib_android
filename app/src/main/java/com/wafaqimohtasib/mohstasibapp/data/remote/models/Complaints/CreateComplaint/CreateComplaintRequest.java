package com.wafaqimohtasib.mohstasibapp.data.remote.models.Complaints.CreateComplaint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateComplaintRequest {
    @SerializedName("complaintantName")
    @Expose
    private String complaintantName;
    @SerializedName("complaintantEmail")
    @Expose
    private String complaintantEmail;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("cnic")
    @Expose
    private String cnic;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("mohtasibOffice")
    @Expose
    private String mohtasibOffice;
    @SerializedName("complaintAgainst")
    @Expose
    private String complaintAgainst;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("attachment")
    @Expose
    private String attachment;
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("status")
    @Expose
    private String status;

    public String getComplaintantName() {
        return complaintantName;
    }

    public void setComplaintantName(String complaintantName) {
        this.complaintantName = complaintantName;
    }

    public String getComplaintantEmail() {
        return complaintantEmail;
    }

    public void setComplaintantEmail(String complaintantEmail) {
        this.complaintantEmail = complaintantEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMohtasibOffice() {
        return mohtasibOffice;
    }

    public void setMohtasibOffice(String mohtasibOffice) {
        this.mohtasibOffice = mohtasibOffice;
    }

    public String getComplaintAgainst() {
        return complaintAgainst;
    }

    public void setComplaintAgainst(String complaintAgainst) {
        this.complaintAgainst = complaintAgainst;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
