package com.wafaqimohtasib.mohstasibapp.ui.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.wafaqimohtasib.mohstasibapp.App;
import com.wafaqimohtasib.mohstasibapp.R;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.Complaints.CreateComplaint.CreateComplaintRequest;
import com.wafaqimohtasib.mohstasibapp.ui.viewmodels.ComplaintsVIewModel;
import com.wafaqimohtasib.mohstasibapp.utils.DialogManager;
import com.wafaqimohtasib.mohstasibapp.utils.FileChooser;
import com.wafaqimohtasib.mohstasibapp.utils.Helpers;
import com.wafaqimohtasib.mohstasibapp.utils.NetworkManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComplaintRegistrationActivity extends AppCompatActivity {

    @BindView(R.id.tv_header) TextView tvHeaderTitle;
    @BindView(R.id.toolbar_iv_back) ImageView ivBack;
    @BindView(R.id.et_complaintant_name) EditText etComplaintantName;
    @BindView(R.id.et_complaintant_email) EditText etComplaintantEmail;
    @BindView(R.id.et_address) EditText etAddress;
    @BindView(R.id.et_cnic) EditText etCnic;
    @BindView(R.id.et_cell_no) EditText etCellNo;
    @BindView(R.id.et_subject) EditText etSubject;
    @BindView(R.id.et_description) EditText etDescription;
    @BindView(R.id.et_attachments) EditText etAttachment;
    @BindView(R.id.sp_city) Spinner spCity;
    @BindView(R.id.sp_mohtasib_office) Spinner spMohtasibOffice;
    @BindView(R.id.sp_complaint_against) Spinner spAgencyName;
    @BindView(R.id.btn_submit) Button btnSubmit;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    private ComplaintsVIewModel complaintsVIewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_registration);
        ButterKnife.bind(this);
        initViews();
        callApis();
    }

    private void initViews()
    {
        tvHeaderTitle.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText(getResources().getString(R.string.complaints_registration));
        complaintsVIewModel= ViewModelProviders.of(this).get(ComplaintsVIewModel.class);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ComplaintRegistrationActivity.this,DashboardActivity.class));
            }
        });

        etAttachment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    requestStoragePermission();
                    return true;
                }
                return false;
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInputs()) {
                    if (NetworkManager.isNetworkAvailable(ComplaintRegistrationActivity.this)) {
                        complaintsVIewModel.createComplaint(ComplaintRegistrationActivity.this, progressBar, createComplaintRequest());
                    }
                    else {
                        DialogManager.noConnectivityPopUp(ComplaintRegistrationActivity.this);
                    }
                }
            }
        });
    }

    public void onBackPressed() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 1)
            {
                if(data == null){
                    return;
                }
                Uri selectedFileUri = data.getData();
                String fileName = FileChooser.getPath(this,selectedFileUri).substring(FileChooser.getPath(this,selectedFileUri).lastIndexOf("/")+1);
                if(fileName != null && !fileName.equals(""))
                {
                    etAttachment.setText(fileName);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private boolean validateInputs()
    {
        boolean isValid=false;
        if(etComplaintantName.getText().toString().equals(""))
        {
            etComplaintantName.setError(getResources().getString(R.string.required));
        }
        else if(etComplaintantEmail.getText().toString().equals(""))
        {
            etComplaintantEmail.setError(getResources().getString(R.string.required));
        }
        else if(etAddress.getText().toString().equals(""))
        {
            etAddress.setError(getResources().getString(R.string.required));
        }
        else if(etCnic.getText().toString().equals(""))
        {
            etCnic.setError(getResources().getString(R.string.required));
        }
        else if(etCellNo.getText().toString().equals(""))
        {
            etCellNo.setError(getResources().getString(R.string.required));
        }
        else if(etSubject.getText().toString().equals(""))
        {
            etSubject.setError(getResources().getString(R.string.required));
        }
        else if(etDescription.getText().toString().equals(""))
        {
            etDescription.setError(getResources().getString(R.string.required));
        }
        else
        {
            isValid=true;
        }
        return isValid;
    }

    private void requestStoragePermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        FileChooser.showFileChooser(ComplaintRegistrationActivity.this);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (response.isPermanentlyDenied()) {
                            DialogManager.showSettingsDialog(ComplaintRegistrationActivity.this);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread().check();
    }

    private void callApis()
    {
        if(NetworkManager.isNetworkAvailable(ComplaintRegistrationActivity.this)) {
            complaintsVIewModel.getAllCities(ComplaintRegistrationActivity.this, progressBar, App.LIST_ALL_CITIES, spCity);
            complaintsVIewModel.getAllMohtasibOffices(ComplaintRegistrationActivity.this, progressBar, App.LIST_ALL_MOHTASIB_OFFICES, spMohtasibOffice);
            complaintsVIewModel.getAllAgencies(ComplaintRegistrationActivity.this, progressBar, App.LIST_ALL_AGENCIES, spAgencyName);
            etComplaintantName.setText(Helpers.getPreferenceValues(ComplaintRegistrationActivity.this, getResources().getString(R.string.name)));
            etComplaintantEmail.setText(Helpers.getPreferenceValues(ComplaintRegistrationActivity.this, getResources().getString(R.string.email)));
            etAddress.setText(Helpers.getPreferenceValues(ComplaintRegistrationActivity.this, getResources().getString(R.string.address)));
            etCnic.setText(Helpers.getPreferenceValues(ComplaintRegistrationActivity.this, getResources().getString(R.string.cnic)));
            etCellNo.setText(Helpers.getPreferenceValues(ComplaintRegistrationActivity.this, getResources().getString(R.string.cell_no)));
        }
        else {
            DialogManager.noConnectivityPopUp(ComplaintRegistrationActivity.this);
        }
    }

    private CreateComplaintRequest createComplaintRequest()
    {
        CreateComplaintRequest createComplaintRequest=new CreateComplaintRequest();
        createComplaintRequest.setComplaintantName(etComplaintantName.getText().toString().trim());
        createComplaintRequest.setComplaintantEmail(etComplaintantEmail.getText().toString().trim());
        createComplaintRequest.setAddress(etAddress.getText().toString().trim());
        createComplaintRequest.setCnic(etCnic.getText().toString().trim());
        createComplaintRequest.setMobileNo(etCellNo.getText().toString().trim());
        createComplaintRequest.setSubject(etSubject.getText().toString().trim());
        createComplaintRequest.setDescription(etDescription.getText().toString().trim());
        createComplaintRequest.setAttachment(etAttachment.getText().toString().trim());
        createComplaintRequest.setCity(spCity.getSelectedItem().toString());
        createComplaintRequest.setMohtasibOffice(spMohtasibOffice.getSelectedItem().toString());
        createComplaintRequest.setComplaintAgainst(spAgencyName.getSelectedItem().toString());
        createComplaintRequest.setDateTime(Helpers.getCurrentDateTime(ComplaintRegistrationActivity.this,"yyyy-MM-dd hh:mm"));
        createComplaintRequest.setStatus("1");
        return createComplaintRequest;
    }
}
