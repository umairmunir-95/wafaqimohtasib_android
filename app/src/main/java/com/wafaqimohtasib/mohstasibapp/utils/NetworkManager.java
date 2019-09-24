package com.wafaqimohtasib.mohstasibapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.ProgressBar;

public class NetworkManager {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showProgressBar(ProgressBar progressBar,boolean show) {
        if(show)
        {
            progressBar.setVisibility(View.VISIBLE);
        }
        else
            {
            progressBar.setVisibility(View.GONE);
        }
    }
}
