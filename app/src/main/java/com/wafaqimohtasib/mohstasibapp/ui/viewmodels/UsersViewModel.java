package com.wafaqimohtasib.mohstasibapp.ui.viewmodels;

import android.app.Application;
import android.content.Context;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.wafaqimohtasib.mohstasibapp.data.remote.models.SignUp.SignUpRequest;
import com.wafaqimohtasib.mohstasibapp.data.repositories.UsersRepository;

public class UsersViewModel extends AndroidViewModel {

    private UsersRepository usersRepository;

    public UsersViewModel(@NonNull Application application)
    {
        super(application);
        usersRepository= new UsersRepository(application);

    }

    public void registerUser(Context context, ProgressBar progressBar, SignUpRequest signUpRequest)
    {
        usersRepository.registerUser(context,progressBar,signUpRequest);
    }

    public void login(Context context,ProgressBar progressBar,String url)
    {
        usersRepository.login(context,progressBar,url);
    }
}

