package com.example.vp_alpapp.service;


import com.example.vp_alpapp.model.Login;
import com.example.vp_alpapp.model.LoginToken;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


    public static Retrofit retrofit;


    public static Retrofit getRetrofit() {


        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8000/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }





}
