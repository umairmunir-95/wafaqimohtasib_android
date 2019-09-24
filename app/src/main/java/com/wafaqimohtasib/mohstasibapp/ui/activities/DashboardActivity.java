package com.wafaqimohtasib.mohstasibapp.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wafaqimohtasib.mohstasibapp.R;
import com.wafaqimohtasib.mohstasibapp.data.local.preferences.SharedPreferencesHelper;
import com.wafaqimohtasib.mohstasibapp.ui.adapters.DashboardAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity {

    @BindView(R.id.tv_header) TextView tvHeaderTitle;
    @BindView(R.id.toolbar_exit) ImageView ivExit;
    @BindView(R.id.toolbar_iv_back) ImageView ivBack;
    @BindView(R.id.grid) GridView gridView;
    private DashboardAdapter dashboardAdapter;
    private String titlesText[] ;
    private SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    public void onBackPressed() {

    }

    private void initViews()
    {
        ivBack.setVisibility(View.VISIBLE);
        ivExit.setVisibility(View.VISIBLE);
        tvHeaderTitle.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText(getResources().getString(R.string.dashboard));
        titlesText=getResources().getStringArray(R.array.menu_titles);
        sharedPreferencesHelper=new SharedPreferencesHelper(DashboardActivity.this);
        dashboardAdapter=new DashboardAdapter(DashboardActivity.this,titlesText);
        gridView.setAdapter(dashboardAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 8:
                    case 9:
                    case 11:
                        sharedPreferencesHelper.setString(getResources().getString(R.string.from),titlesText[position]);
                        startActivity(new Intent(DashboardActivity.this, InformationActivity.class));
                        break;
                    case 6:
                        sharedPreferencesHelper.setString(getResources().getString(R.string.from),titlesText[position]);
                        startActivity(new Intent(DashboardActivity.this, MyComplaintsActivity.class));
                        break;
                    case 5:
                        sharedPreferencesHelper.setString(getResources().getString(R.string.from),titlesText[position]);
                        startActivity(new Intent(DashboardActivity.this, ComplaintRegistrationActivity.class));
                        break;
                    case 3:
                    case 10:
                        sharedPreferencesHelper.setString(getResources().getString(R.string.from),titlesText[position]);
                        startActivity(new Intent(DashboardActivity.this, PdfLoaderActivity.class));
                        break;
                    case 12:
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                Uri.parse("https://www.google.com/maps/search/3rd+floor,+State+Life+Building%D8%8C+Davis+Road,+Garhi+Shahu,+Lahore,+Punjab+54000%E2%80%AD/@31.5575053,74.3370039,17z/data=!3m1!4b1"));
                        startActivity(intent);
                        break;

                    default:
                        break;

                }
            }
        });

        ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferencesHelper.setString(getResources().getString(R.string.name),"");
                sharedPreferencesHelper.setString(getResources().getString(R.string.email),"");
                sharedPreferencesHelper.setString(getResources().getString(R.string.cell_no),"");
                sharedPreferencesHelper.setString(getResources().getString(R.string.cnic),"");
                sharedPreferencesHelper.setString(getResources().getString(R.string.address),"");
                sharedPreferencesHelper.setString(getResources().getString(R.string.user_type),"");
                sharedPreferencesHelper.setString(getResources().getString(R.string.islogged_in),"false");
                startActivity(new Intent(DashboardActivity.this,LoginActivity.class));
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                finish();
                System.exit(0);
            }
        });
    }
}
