package com.wafaqimohtasib.mohstasibapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.wafaqimohtasib.mohstasibapp.R;
import com.wafaqimohtasib.mohstasibapp.data.local.preferences.SharedPreferencesHelper;
import com.wafaqimohtasib.mohstasibapp.utils.DialogManager;
import com.wafaqimohtasib.mohstasibapp.utils.EmailConfig;
import com.wafaqimohtasib.mohstasibapp.utils.Helpers;
import com.wafaqimohtasib.mohstasibapp.utils.NetworkManager;
import com.wafaqimohtasib.mohstasibapp.utils.SendEmail;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.et_name) EditText etName;
    @BindView(R.id.et_email) EditText etEmail;
    @BindView(R.id.et_password) EditText etPassword;
    @BindView(R.id.et_cnic) EditText etCnic;
    @BindView(R.id.et_cell_no) EditText etCellNo;
    @BindView(R.id.et_address) EditText etAddress;
    @BindView(R.id.btn_register) Button btnRegister;
    @BindView(R.id.tv_login) TextView tvLogin;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    private SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    public void onBackPressed() {

    }

    private void initViews()
    {
        sharedPreferencesHelper=new SharedPreferencesHelper(RegistrationActivity.this);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(NetworkManager.isNetworkAvailable(RegistrationActivity.this))
                {
                    if(validateInputs())
                    {
                        sharedPreferencesHelper.setString(getResources().getString(R.string.name), etName.getText().toString());
                        sharedPreferencesHelper.setString(getResources().getString(R.string.email), etEmail.getText().toString());
                        sharedPreferencesHelper.setString(getResources().getString(R.string.password), etPassword.getText().toString());
                        sharedPreferencesHelper.setString(getResources().getString(R.string.cnic), etCnic.getText().toString());
                        sharedPreferencesHelper.setString(getResources().getString(R.string.cell_no), etCellNo.getText().toString());
                        sharedPreferencesHelper.setString(getResources().getString(R.string.address), etAddress.getText().toString());
                        sharedPreferencesHelper.setString(getResources().getString(R.string.user_type), "user");
                        sharedPreferencesHelper.setString(getResources().getString(R.string.verification_code), Helpers.generateVerificationCode());
                        new SendEmail(RegistrationActivity.this,progressBar,etEmail.getText().toString(), EmailConfig.SUBJECT,EmailConfig.MESSAGE+Helpers.getPreferenceValues(RegistrationActivity.this,getResources().getString(R.string.verification_code))).execute();
                    }
                }
                else
                {
                    DialogManager.noConnectivityPopUp(RegistrationActivity.this);
                }
            }
        });
    }

    private boolean validateInputs()
    {
        boolean isValid=false;
        if(etName.getText().toString().equals(""))
        {
            etName.setError(getResources().getString(R.string.required));
        }
        else if(etEmail.getText().toString().equals(""))
        {
            etEmail.setError(getResources().getString(R.string.required));
        }
        else if(etPassword.getText().toString().equals(""))
        {
            etPassword.setError(getResources().getString(R.string.required));
        }
        else if(etCnic.getText().toString().equals(""))
        {
            etCnic.setError(getResources().getString(R.string.required));
        }
        else if(etCellNo.getText().toString().equals(""))
        {
            etCellNo.setError(getResources().getString(R.string.required));
        }
        else if(etAddress.getText().toString().equals(""))
        {
            etAddress.setError(getResources().getString(R.string.required));
        }
        else
        {
            isValid=true;
        }
        return isValid;
    }
}
