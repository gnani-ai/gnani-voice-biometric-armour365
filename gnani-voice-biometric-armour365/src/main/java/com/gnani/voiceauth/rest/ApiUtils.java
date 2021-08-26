package com.gnani.voiceauth.rest;

public class ApiUtils {

    private ApiUtils() {}

    public static APIService getAPIService() {

        return RetrofitClient.getClient().create(APIService.class);
    }
}