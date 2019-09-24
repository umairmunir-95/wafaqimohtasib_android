package com.wafaqimohtasib.mohstasibapp.ui.viewmodels;

import android.app.Application;
import android.content.Context;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.wafaqimohtasib.mohstasibapp.data.remote.models.Complaints.ComplaintApproveReject.ApproveRejectRequest;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Complaints.ComplaintApproveReject.ApproveRejectResponse;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Complaints.CreateComplaint.CreateComplaintRequest;
import com.wafaqimohtasib.mohstasibapp.data.repositories.ComplaintsRepository;


public class ComplaintsVIewModel extends AndroidViewModel {

    private ComplaintsRepository complaintsRepository;

    public ComplaintsVIewModel(@NonNull Application application)
    {
        super(application);
        complaintsRepository= new ComplaintsRepository(application);
    }


    public void getAllCities(Context context, ProgressBar progressBar, String url, Spinner spinner)
    {
        complaintsRepository.getAllCities(context,progressBar,url,spinner);
    }

    public void getAllMohtasibOffices(Context context, ProgressBar progressBar, String url, Spinner spinner)
    {
        complaintsRepository.getAllMohtasibOffices(context,progressBar,url,spinner);
    }
    public void getAllAgencies(Context context, ProgressBar progressBar, String url, Spinner spinner)
    {
        complaintsRepository.getAllAgencies(context,progressBar,url,spinner);
    }

    public void createComplaint(Context context, ProgressBar progressBar, CreateComplaintRequest createComplaintRequest)
    {
        complaintsRepository.createComplaint(context,progressBar,createComplaintRequest);
    }

    public void approveRejectComplaint(Context context, ProgressBar progressBar, ApproveRejectRequest approveRejectRequest,String url)
    {
        complaintsRepository.approveRejectComplaint(context,progressBar,approveRejectRequest,url);
    }

    public void getMyComplaints(Context context, ProgressBar progressBar, String url, RecyclerView recyclerView)
    {
        complaintsRepository.getMyComplaints(context,progressBar,url,recyclerView);
    }
}

