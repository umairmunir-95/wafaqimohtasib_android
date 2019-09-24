package com.wafaqimohtasib.mohstasibapp.data.repositories;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wafaqimohtasib.mohstasibapp.R;
import com.wafaqimohtasib.mohstasibapp.data.local.models.MyRequests;
import com.wafaqimohtasib.mohstasibapp.data.local.preferences.SharedPreferencesHelper;
import com.wafaqimohtasib.mohstasibapp.data.remote.ApiClient;
import com.wafaqimohtasib.mohstasibapp.data.remote.interfaces.ComplaintsInterface;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Agencies.AgenciesResponse;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Cities.CitiesResponse;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Complaints.ComplaintApproveReject.ApproveRejectRequest;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Complaints.ComplaintApproveReject.ApproveRejectResponse;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Complaints.CreateComplaint.CreateComplaintRequest;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Complaints.CreateComplaint.CreateComplaintResponse;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Complaints.MyComplaints.MyComplaintsResponse;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.MohtasibOffices.MOhtasibOfficesResponse;
import com.wafaqimohtasib.mohstasibapp.ui.activities.ComplaintDetailsActivity;
import com.wafaqimohtasib.mohstasibapp.ui.activities.ComplaintRegistrationActivity;
import com.wafaqimohtasib.mohstasibapp.ui.adapters.MyComplaintsAdapter;
import com.wafaqimohtasib.mohstasibapp.utils.DialogManager;
import com.wafaqimohtasib.mohstasibapp.utils.Helpers;
import com.wafaqimohtasib.mohstasibapp.utils.RecyclerViewTouchListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplaintsRepository {

    public ComplaintsRepository(Application application) {
    }

    public void getAllCities(final Context context, final ProgressBar progressBar, String url, final Spinner spinner) {
        final ArrayList<String> cities=new ArrayList<>();
        ComplaintsInterface complaintsInterface = ApiClient.getClient().create(ComplaintsInterface.class);
        Call<List<CitiesResponse>> call = complaintsInterface.getAllCities(url);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<List<CitiesResponse>>() {
            @Override
            public void onResponse(Call<List<CitiesResponse>> call, Response<List<CitiesResponse>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        cities.add(response.body().get(i).getCityName());
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,cities);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(dataAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<CitiesResponse >>call, Throwable t) {

                Log.d("TAG", "Error : " + t.toString());
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void getAllMohtasibOffices(final Context context, final ProgressBar progressBar, String url, final Spinner spinner) {
        final ArrayList<String> offices=new ArrayList<>();
        ComplaintsInterface complaintsInterface = ApiClient.getClient().create(ComplaintsInterface.class);
        Call<List<MOhtasibOfficesResponse>> call = complaintsInterface.getAllMOhtasibOffices(url);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<List<MOhtasibOfficesResponse>>() {
            @Override
            public void onResponse(Call<List<MOhtasibOfficesResponse>> call, Response<List<MOhtasibOfficesResponse>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        offices.add(response.body().get(i).getOfficeName());
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,offices);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(dataAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<MOhtasibOfficesResponse >>call, Throwable t) {
                Log.d("TAG", "Error : " + t.toString());
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void getAllAgencies(final Context context, final ProgressBar progressBar, String url, final Spinner spinner) {
        final ArrayList<String> agencies=new ArrayList<>();
        ComplaintsInterface complaintsInterface = ApiClient.getClient().create(ComplaintsInterface.class);
        Call<List<AgenciesResponse>> call = complaintsInterface.getAllAgencies(url);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<List<AgenciesResponse>>() {
            @Override
            public void onResponse(Call<List<AgenciesResponse>> call, Response<List<AgenciesResponse>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        agencies.add(response.body().get(i).getAgencyName());
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,agencies);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(dataAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<AgenciesResponse>>call, Throwable t) {
                Log.d("TAG", "Error : " + t.toString());
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void createComplaint(final Context context, final ProgressBar progressBar, CreateComplaintRequest createComplaintRequest) {
        ComplaintsInterface complaintsInterface = ApiClient.getClient().create(ComplaintsInterface.class);
        Call<CreateComplaintResponse> call = complaintsInterface.createComplaint(createComplaintRequest);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<CreateComplaintResponse>() {
            @Override
            public void onResponse(Call<CreateComplaintResponse> call, Response<CreateComplaintResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    DialogManager.showNetworkInfo(context, "", response.body().getMessage(), "");
                } else {
                    DialogManager.showNetworkInfo(context, "", response.message(), "");
                }
            }

            @Override
            public void onFailure(Call<CreateComplaintResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                DialogManager.showNetworkInfo(context, "", t.toString(), "");
            }
        });
    }

    public void approveRejectComplaint(final Context context, final ProgressBar progressBar, ApproveRejectRequest approveRejectRequest,String url) {
        ComplaintsInterface complaintsInterface = ApiClient.getClient().create(ComplaintsInterface.class);
        Call<ApproveRejectResponse> call = complaintsInterface.approveRejectComplaint(approveRejectRequest,url);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<ApproveRejectResponse>() {
            @Override
            public void onResponse(Call<ApproveRejectResponse> call, Response<ApproveRejectResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    DialogManager.showNetworkInfo(context, "", response.body().getMessage(), "approveReject");
                } else {
                    DialogManager.showNetworkInfo(context, "", response.message(), "approveReject");
                }
            }

            @Override
            public void onFailure(Call<ApproveRejectResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                DialogManager.showNetworkInfo(context, "", t.toString(), "approveReject");
            }
        });
    }

    public void getMyComplaints(final Context context, final ProgressBar progressBar, String url, final RecyclerView recyclerView) {
        final ArrayList<MyRequests> complaints=new ArrayList<>();
        final SharedPreferencesHelper sharedPreferencesHelper=new SharedPreferencesHelper(context);
        ComplaintsInterface complaintsInterface = ApiClient.getClient().create(ComplaintsInterface.class);
        Call<List<MyComplaintsResponse>> call = complaintsInterface.getMyComplaints(url);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<List<MyComplaintsResponse>>() {
            @Override
            public void onResponse(Call<List<MyComplaintsResponse>> call, Response<List<MyComplaintsResponse>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        MyRequests myRequests=new MyRequests();
                        myRequests.setId(response.body().get(i).getId());
                        myRequests.setComplaintantName(response.body().get(i).getComplaintantName());
                        myRequests.setComplaintantEmail(response.body().get(i).getComplaintantEmail());
                        myRequests.setAddress(response.body().get(i).getAddress());
                        myRequests.setCnic(response.body().get(i).getCnic());
                        myRequests.setMobileNo(response.body().get(i).getMobileNo());
                        myRequests.setCity(response.body().get(i).getCity());
                        myRequests.setMohtasibOffice(response.body().get(i).getMohtasibOffice());
                        myRequests.setComplaintAgainst(response.body().get(i).getComplaintAgainst());
                        myRequests.setSubject(response.body().get(i).getSubject());
                        myRequests.setDescription(response.body().get(i).getDescription());
                        myRequests.setStatus(response.body().get(i).getStatus());
                        myRequests.setDate(response.body().get(i).getDateTime());
                        complaints.add(myRequests);
                    }
                    initRecyclerView(context,recyclerView,complaints);

                    recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(context, recyclerView, new RecyclerViewTouchListener.ClickListener() {
                        @Override
                        public void onClick(View view, int position) {

                            if (Helpers.getPreferenceValues(context, context.getResources().getString(R.string.user_type)).equals("admin")) {
                                sharedPreferencesHelper.setString("id", complaints.get(position).getId() + "");
                                sharedPreferencesHelper.setString("name", complaints.get(position).getComplaintantName());
                                sharedPreferencesHelper.setString("email", complaints.get(position).getComplaintantEmail());
                                sharedPreferencesHelper.setString("address", complaints.get(position).getAddress());
                                sharedPreferencesHelper.setString("cnic", complaints.get(position).getCnic());
                                sharedPreferencesHelper.setString("mobileNo", complaints.get(position).getMobileNo());
                                sharedPreferencesHelper.setString("city", complaints.get(position).getCity());
                                sharedPreferencesHelper.setString("mohtasibOffice", complaints.get(position).getMohtasibOffice());
                                sharedPreferencesHelper.setString("complaintAgainst", complaints.get(position).getComplaintAgainst());
                                sharedPreferencesHelper.setString("subject", complaints.get(position).getSubject());
                                sharedPreferencesHelper.setString("description", complaints.get(position).getDescription());
                                sharedPreferencesHelper.setString("dateTime", complaints.get(position).getDate());
                                sharedPreferencesHelper.setString("status", complaints.get(position).getStatus());
                                context.startActivity(new Intent(context, ComplaintDetailsActivity.class));
                            }
                        }

                        @Override
                        public void onLongClick(View view, int position) {

                        }
                    }));
                }
            }
            @Override
            public void onFailure(Call<List<MyComplaintsResponse>>call, Throwable t) {
                Log.d("TAG", "Error : " + t.toString());
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void initRecyclerView(Context context, RecyclerView recyclerView, final ArrayList<MyRequests> complaints)
    {
        MyComplaintsAdapter myComplaintsAdapter=new MyComplaintsAdapter(context);
        myComplaintsAdapter.setMyRequests(complaints);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(myComplaintsAdapter);
    }
}