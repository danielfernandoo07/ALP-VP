package com.example.vp_alpapp.service

import com.example.vp_alpapp.model.Login
import com.example.vp_alpapp.model.LoginToken
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.Call

public interface UserClient {

    @POST("login")
    fun login(@Body login: Login): Call<LoginToken>


}