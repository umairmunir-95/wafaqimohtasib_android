package com.wafaqimohtasib.mohstasibapp.data.repositories;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.wafaqimohtasib.mohstasibapp.R;
import com.wafaqimohtasib.mohstasibapp.data.local.preferences.SharedPreferencesHelper;
import com.wafaqimohtasib.mohstasibapp.data.remote.ApiClient;
import com.wafaqimohtasib.mohstasibapp.data.remote.interfaces.UsersInterface;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Login.LoginResponse;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.SignUp.SignUpRequest;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.SignUp.SignUpResponse;
import com.wafaqimohtasib.mohstasibapp.ui.activities.DashboardActivity;
import com.wafaqimohtasib.mohstasibapp.utils.DialogManager;
import com.wafaqimohtasib.mohstasibapp.utils.Helpers;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository {

    SharedPreferencesHelper sharedPreferencesHelper;

    public UsersRepository(Application application) {
    }

    public void registerUser(final Context context, final ProgressBar progressBar, SignUpRequest signUpRequest) {
        UsersInterface usersInterface = ApiClient.getClient().create(UsersInterface.class);
        Call<SignUpResponse> call = usersInterface.register(signUpRequest);
        progressBar.setVisibility(View.VISIBLE);
        sharedPreferencesHelper = new SharedPreferencesHelper(context);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    DialogManager.showNetworkInfo(context, "", response.body().getMessage(), "userRegistration");
                } else {
                    DialogManager.showNetworkInfo(context, "", response.message(), "userRegistration");
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

                progressBar.setVisibility(View.GONE);
                DialogManager.showNetworkInfo(context, "", t.toString(), "userRegistration");
            }
        });
    }

    public void login(final Context context, final ProgressBar progressBar,String url) {

        UsersInterface usersInterface= ApiClient.getClient().create(UsersInterface.class);
        Call<List<LoginResponse>> call = usersInterface.login(url);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<List<LoginResponse>>() {
            @Override
            public void onResponse(Call<List<LoginResponse>> call, Response<List<LoginResponse>> response) {
                progressBar.setVisibility(View.GONE);
                sharedPreferencesHelper=new SharedPreferencesHelper(context);
                if (response.isSuccessful()) {
                    if(response.body().size()==0)
                    {
                        DialogManager.showNetworkInfo(context, "", context.getResources().getString(R.string.incorrect_credentials), "");
                    }
                    else {
                        for (int i = 0; i < response.body().size(); i++) {
                            sharedPreferencesHelper.setString(context.getResources().getString(R.string.name), "" + response.body().get(i).getName());
                            sharedPreferencesHelper.setString(context.getResources().getString(R.string.email), "" + response.body().get(i).getEmail());
                            sharedPreferencesHelper.setString(context.getResources().getString(R.string.cell_no), "" + response.body().get(i).getCellNo());
                            sharedPreferencesHelper.setString(context.getResources().getString(R.string.cnic), response.body().get(i).getCnic());
                            sharedPreferencesHelper.setString(context.getResources().getString(R.string.address), response.body().get(i).getAddress());
                            sharedPreferencesHelper.setString(context.getResources().getString(R.string.user_type), "" + response.body().get(i).getUserType());
                            sharedPreferencesHelper.setString(context.getResources().getString(R.string.islogged_in), "true");
                        }
                        context.startActivity(new Intent(context, DashboardActivity.class));
                    }
                }
                else {
                    DialogManager.showNetworkInfo(context, "", response.message(), "");
                }
            }

            @Override
            public void onFailure(Call<List<LoginResponse>> call, Throwable t) {

                Log.d("TAG", "Error : " + t.toString());
                progressBar.setVisibility(View.GONE);
                DialogManager.showNetworkInfo(context,"",t.toString(),"");
            }
        });
    }
}