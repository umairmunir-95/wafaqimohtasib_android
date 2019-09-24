package com.wafaqimohtasib.mohstasibapp.utils;

public class AssetManager {

    private static final String assetsBase="file:///android_asset/";
    public static final String introduction="introduction.html";
    public static final String profile="profile.html";
    public static final String mandate="mandate.html";
    public static final String does="does.html";
    public static final String donts="donts.html";
    public static final String employeeArea="employeeArea.html";
    public static final String findUs="findUs.html";
    public static final String complaintProcess="complaintProcess.html";

    public static StringBuilder loadAsset(String fileName)
    {
        return new StringBuilder(assetsBase).append(fileName);
    }
}
