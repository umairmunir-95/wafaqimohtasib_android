package com.wafaqimohtasib.mohstasibapp.data.local.models;


public class MyRequests {
    String status,complaintAgainst,subject,date;

    public MyRequests() {
    }

    public MyRequests(String status, String complaintAgainst, String subject, String date) {
        this.status = status;
        this.complaintAgainst = complaintAgainst;
        this.subject = subject;
        this.date = date;
    }

    private Integer id;
    private String complaintantName;
    private String complaintantEmail;
    private String address;
    private String cnic;
    private String mobileNo;
    private String city;
    private String mohtasibOffice;
    private String description;
    private String attachment;
    private String dateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
