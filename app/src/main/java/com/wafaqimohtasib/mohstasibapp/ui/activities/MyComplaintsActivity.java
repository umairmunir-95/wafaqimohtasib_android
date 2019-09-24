package com.wafaqimohtasib.mohstasibapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.wafaqimohtasib.mohstasibapp.App;
import com.wafaqimohtasib.mohstasibapp.R;
import com.wafaqimohtasib.mohstasibapp.ui.viewmodels.ComplaintsVIewModel;
import com.wafaqimohtasib.mohstasibapp.utils.DialogManager;
import com.wafaqimohtasib.mohstasibapp.utils.NetworkManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyComplaintsActivity extends AppCompatActivity {

    @BindView(R.id.tv_header) TextView tvHeaderTitle;
    @BindView(R.id.toolbar_iv_back) ImageView ivBack;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    private ComplaintsVIewModel complaintsVIewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_complaints);
        ButterKnife.bind(this);
        initViews();
        callApi();
    }

    private void initViews()
    {
        tvHeaderTitle.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText(getResources().getString(R.string.my_complaints));
        complaintsVIewModel= ViewModelProviders.of(this).get(ComplaintsVIewModel.class);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyComplaintsActivity.this,DashboardActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    private void callApi()
    {
        if(NetworkManager.isNetworkAvailable(MyComplaintsActivity.this))
        {
            complaintsVIewModel.getMyComplaints(MyComplaintsActivity.this,progressBar, App.GET_USER_COMPLAINTS,recyclerView);
        }
        else
        {
            DialogManager.noConnectivityPopUp(MyComplaintsActivity.this);
        }
    }
}
