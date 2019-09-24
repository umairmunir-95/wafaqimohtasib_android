package com.wafaqimohtasib.mohstasibapp.data.remote;

import com.wafaqimohtasib.mohstasibapp.App;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient()
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder oktHttpClient = new OkHttpClient.Builder();
        oktHttpClient.addInterceptor(interceptor);
        oktHttpClient.connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);

        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(App.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(oktHttpClient.build())
                    .build();
        }
        return retrofit;
    }
}
