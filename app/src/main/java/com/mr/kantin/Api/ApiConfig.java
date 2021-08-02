package com.mr.kantin.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {

    private static final String BASE_URL = "https://lanzhaa.000webhostapp.com/";
    private static Retrofit retrofit;

    public static Retrofit connectAPI(){

        if (retrofit == null) {
           retrofit = new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
        }
        return retrofit;
    }


}
