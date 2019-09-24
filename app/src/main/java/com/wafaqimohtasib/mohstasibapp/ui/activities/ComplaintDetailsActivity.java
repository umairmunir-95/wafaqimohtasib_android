package com.wafaqimohtasib.mohstasibapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.wafaqimohtasib.mohstasibapp.App;
import com.wafaqimohtasib.mohstasibapp.R;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Complaints.ComplaintApproveReject.ApproveRejectRequest;
import com.wafaqimohtasib.mohstasibapp.ui.viewmodels.ComplaintsVIewModel;
import com.wafaqimohtasib.mohstasibapp.utils.DialogManager;
import com.wafaqimohtasib.mohstasibapp.utils.Helpers;
import com.wafaqimohtasib.mohstasibapp.utils.NetworkManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComplaintDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tv_header) TextView tvHeaderTitle;
    @BindView(R.id.toolbar_iv_back) ImageView ivBack;
    @BindView(R.id.et_complaintant_name) EditText etComplaintantName;
    @BindView(R.id.et_complaintant_email) EditText etComplaintantEmail;
    @BindView(R.id.et_address) EditText etAddress;
    @BindView(R.id.et_cnic) EditText etCnic;
    @BindView(R.id.et_cell_no) EditText etCellNo;
    @BindView(R.id.et_subject) EditText etSubject;
    @BindView(R.id.et_description) EditText etDescription;
    @BindView(R.id.et_against) EditText etComplaintAgainst;
    @BindView(R.id.et_city) EditText etCity;
    @BindView(R.id.et_mohtasib_office) EditText etMohtasibOffice;
    @BindView(R.id.et_datetime) EditText etDateTime;
    @BindView(R.id.btn_approve) Button btnApprove;
    @BindView(R.id.btn_reject) Button btnReject;

    @BindView(R.id.progress_bar) ProgressBar progressBar;
    private ComplaintsVIewModel complaintsVIewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_details);
        ButterKnife.bind(this);
        initViews();
        populateFields();
    }

    private void initViews()
    {
        tvHeaderTitle.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText(getResources().getString(R.string.complaint_details));
        complaintsVIewModel= ViewModelProviders.of(this).get(ComplaintsVIewModel.class);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ComplaintDetailsActivity.this,MyComplaintsActivity.class));
            }
        });

        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Helpers.getPreferenceValues(ComplaintDetailsActivity.this,"status").equals("2"))
                {
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.already_approved),Toast.LENGTH_LONG).show();
                }
                else if(Helpers.getPreferenceValues(ComplaintDetailsActivity.this,"status").equals("3"))
                {
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.already_rejected),Toast.LENGTH_LONG).show();
                }
                else
                {
                    complaintsVIewModel.approveRejectComplaint(ComplaintDetailsActivity.this,progressBar,approveRejectRequest("2"),App.GET_USER_COMPLAINTS+Helpers.getPreferenceValues(ComplaintDetailsActivity.this,"id"));
                }
            }
        });

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Helpers.getPreferenceValues(ComplaintDetailsActivity.this,"status").equals("2"))
                {
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.already_approved),Toast.LENGTH_LONG).show();
                }
                else if(Helpers.getPreferenceValues(ComplaintDetailsActivity.this,"status").equals("3"))
                {

                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.already_rejected),Toast.LENGTH_LONG).show();
                }
                else {
                    complaintsVIewModel.approveRejectComplaint(ComplaintDetailsActivity.this, progressBar, approveRejectRequest("3"), App.GET_USER_COMPLAINTS+ Helpers.getPreferenceValues(ComplaintDetailsActivity.this, "id"));
                }
            }
        });
    }

    public void onBackPressed() {

    }

    private void populateFields()
    {
        etComplaintantName.setText(Helpers.getPreferenceValues(ComplaintDetailsActivity.this,"name"));
        etComplaintantEmail.setText(Helpers.getPreferenceValues(ComplaintDetailsActivity.this,"email"));
        etAddress.setText(Helpers.getPreferenceValues(ComplaintDetailsActivity.this,"address"));
        etCnic.setText(Helpers.getPreferenceValues(ComplaintDetailsActivity.this,"cnic"));
        etCellNo.setText(Helpers.getPreferenceValues(ComplaintDetailsActivity.this,"mobileNo"));
        etCity.setText(Helpers.getPreferenceValues(ComplaintDetailsActivity.this,"city"));
        etMohtasibOffice.setText(Helpers.getPreferenceValues(ComplaintDetailsActivity.this,"mohtasibOffice"));
        etComplaintAgainst.setText(Helpers.getPreferenceValues(ComplaintDetailsActivity.this,"complaintAgainst"));
        etDateTime.setText(Helpers.getPreferenceValues(ComplaintDetailsActivity.this,"dateTime"));
        etDescription.setText(Helpers.getPreferenceValues(ComplaintDetailsActivity.this,"description"));
        etSubject.setText(Helpers.getPreferenceValues(ComplaintDetailsActivity.this,"subject"));
    }
    private ApproveRejectRequest approveRejectRequest(String status)
    {
        ApproveRejectRequest approveRejectRequest=new ApproveRejectRequest();
        approveRejectRequest.setStatus(status);
        return approveRejectRequest;
    }

}
