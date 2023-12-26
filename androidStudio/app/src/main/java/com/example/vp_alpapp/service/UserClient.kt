package com.example.vp_alpapp.service

import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.model.ContentList
import com.example.vp_alpapp.model.Login
import com.example.vp_alpapp.model.LoginToken
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Header

public interface UserClient {

    @POST("login")
    fun login(@Body login: Login): Call<LoginToken>

    @GET("content")
    fun getAllContent(@Header("Authorization") token: String) :Call<List<Content>>

}