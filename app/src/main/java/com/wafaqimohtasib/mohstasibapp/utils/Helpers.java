package com.wafaqimohtasib.mohstasibapp.utils;

import android.content.Context;
import android.widget.Toast;

import com.wafaqimohtasib.mohstasibapp.data.local.preferences.SharedPreferencesHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Helpers {

    public static String generateVerificationCode()
    {
        return String.format("%04d", new Random().nextInt(10000));
    }

    public static String getPreferenceValues(Context context, String value)
    {
        String prefferenceValue="";
        SharedPreferencesHelper sharedPreferencesHelper=new SharedPreferencesHelper(context);
        if(sharedPreferencesHelper!=null)
        {
            if(sharedPreferencesHelper.getString(value)!=null)
            {
                prefferenceValue=sharedPreferencesHelper.getString(value);
            }
        }
        return prefferenceValue;
    }

    public static String getCurrentDateTime(Context context,String dateTimeFormat) {
        String currentDateTime = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
            currentDateTime = sdf.format(new Date());
        } catch (Exception e) {
            Toast.makeText(context,e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return currentDateTime;
    }

}
