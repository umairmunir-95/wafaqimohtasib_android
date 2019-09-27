package com.wafaqimohtasib.mohstasibapp;

import android.app.Application;

public class App  extends Application {
    public static final String BASE_URL=" https://62285301.ngrok.io";
    public static final String REGISTRATION="users/add";
    public static final String LOGIN="users/login/";
    public static final String LIST_ALL_CITIES="cities/listAllCities";
    public static final String LIST_ALL_MOHTASIB_OFFICES="mohtasibOffices/listAllOffices";
    public static final String LIST_ALL_AGENCIES="agencies/listAllAgencies";
    public static final String CREATE_COMPLAINT="complaints/add";
    public static final String GET_USER_COMPLAINTS="complaints/";
}