package com.wafaqimohtasib.mohstasibapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wafaqimohtasib.mohstasibapp.App;
import com.wafaqimohtasib.mohstasibapp.R;
import com.wafaqimohtasib.mohstasibapp.ui.viewmodels.UsersViewModel;
import com.wafaqimohtasib.mohstasibapp.utils.DialogManager;
import com.wafaqimohtasib.mohstasibapp.utils.Helpers;
import com.wafaqimohtasib.mohstasibapp.utils.NetworkManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_email) EditText etEmail;
    @BindView(R.id.et_password) EditText etPassword;
    @BindView(R.id.btn_login) Button btnLogin;
    @BindView(R.id.tv_register) TextView tvRegister;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    private UsersViewModel usersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews()
    {
        usersViewModel= ViewModelProviders.of(this).get(UsersViewModel.class);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            }
        });
        if(Helpers.getPreferenceValues(LoginActivity.this,getResources().getString(R.string.islogged_in)).equals("true")) {
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
        }
        else {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (etEmail.getText().toString().equals("")) {
                        etEmail.setError(getResources().getString(R.string.required));
                    } else if (etPassword.getText().toString().equals("")) {
                        etPassword.setError(getResources().getString(R.string.required));
                    } else {
                        if (NetworkManager.isNetworkAvailable(LoginActivity.this)) {
                            usersViewModel.login(LoginActivity.this, progressBar, App.LOGIN + etEmail.getText().toString().trim() + "_" + etPassword.getText().toString().trim());
                        } else {
                            DialogManager.noConnectivityPopUp(LoginActivity.this);
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        finish();
        System.exit(0);
    }
}
