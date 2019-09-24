package com.wafaqimohtasib.mohstasibapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.wafaqimohtasib.mohstasibapp.R;
import com.wafaqimohtasib.mohstasibapp.utils.AssetManager;
import com.wafaqimohtasib.mohstasibapp.utils.Helpers;
import com.wafaqimohtasib.mohstasibapp.utils.HtmlLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InformationActivity extends AppCompatActivity {

    @BindView(R.id.tv_header) TextView tvHeaderTitle;
    @BindView(R.id.toolbar_iv_back) ImageView ivBack;
    @BindView(R.id.webview) WebView webView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        ButterKnife.bind(this);
        initViews();
        loadData();
    }

    private void initViews()
    {
        tvHeaderTitle.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText(getResources().getString(R.string.info));
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InformationActivity.this,DashboardActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    private void loadData()
    {
        String from=Helpers.getPreferenceValues(InformationActivity.this,getResources().getString(R.string.from));
        if(from.equals(getResources().getString(R.string.introduction)))
        {
            HtmlLoader.loadData(webView,progressBar, AssetManager.loadAsset(AssetManager.introduction).toString());
        }
        else if(from.equals(getResources().getString(R.string.profile)))
        {
            HtmlLoader.loadData(webView,progressBar, AssetManager.loadAsset(AssetManager.profile).toString());
        }
        else if(from.equals(getResources().getString(R.string.mandate)))
        {
            HtmlLoader.loadData(webView,progressBar, AssetManager.loadAsset(AssetManager.mandate).toString());
        }
        else if(from.equals(getResources().getString(R.string.find_us)))
        {
            HtmlLoader.loadData(webView,progressBar, AssetManager.loadAsset(AssetManager.findUs).toString());
        }
        else if(from.equals(getResources().getString(R.string.what_we_do)))
        {
            HtmlLoader.loadData(webView,progressBar, AssetManager.loadAsset(AssetManager.does).toString());
        }
        else if(from.equals(getResources().getString(R.string.what_we_dont)))
        {
            HtmlLoader.loadData(webView,progressBar, AssetManager.loadAsset(AssetManager.donts).toString());
        }
        else if(from.equals(getResources().getString(R.string.employee_area)))
        {
            HtmlLoader.loadData(webView, progressBar, AssetManager.loadAsset(AssetManager.employeeArea).toString());
        }
        else if(from.equals(getResources().getString(R.string.processs)))
        {
            HtmlLoader.loadData(webView, progressBar, AssetManager.loadAsset(AssetManager.complaintProcess).toString());
        }
    }
}
