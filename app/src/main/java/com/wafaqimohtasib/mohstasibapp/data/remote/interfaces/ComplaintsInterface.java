package com.wafaqimohtasib.mohstasibapp.data.remote.interfaces;

import com.wafaqimohtasib.mohstasibapp.App;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Agencies.AgenciesResponse;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Cities.CitiesResponse;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Complaints.ComplaintApproveReject.ApproveRejectRequest;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Complaints.ComplaintApproveReject.ApproveRejectResponse;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Complaints.CreateComplaint.CreateComplaintRequest;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Complaints.CreateComplaint.CreateComplaintResponse;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Complaints.MyComplaints.MyComplaintsResponse;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.MohtasibOffices.MOhtasibOfficesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

public interface ComplaintsInterface {

    @GET
    Call<List<CitiesResponse>> getAllCities(@Url String url);

    @GET
    Call<List<MOhtasibOfficesResponse>> getAllMOhtasibOffices(@Url String url);

    @GET
    Call<List<AgenciesResponse>> getAllAgencies(@Url String url);

    @GET
    Call<List<MyComplaintsResponse>> getMyComplaints(@Url String url);

    @POST(App.CREATE_COMPLAINT)
    Call<CreateComplaintResponse> createComplaint(@Body CreateComplaintRequest createComplaintRequest);

    @PUT
    Call<ApproveRejectResponse> approveRejectComplaint(@Body ApproveRejectRequest approveRejectRequest,@Url String url);

}
