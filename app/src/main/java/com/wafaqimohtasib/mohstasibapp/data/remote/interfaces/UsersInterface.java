package com.wafaqimohtasib.mohstasibapp.data.remote.interfaces;

import com.wafaqimohtasib.mohstasibapp.App;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Login.LoginResponse;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.SignUp.SignUpRequest;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.SignUp.SignUpResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface UsersInterface {

    @POST(App.REGISTRATION)
    Call<SignUpResponse> register(@Body SignUpRequest signUpRequest);
    @GET
    Call<List<LoginResponse>> login(@Url String url);
}
