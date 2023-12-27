package com.example.vp_alpapp.service

import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.model.Login
import com.example.vp_alpapp.model.LoginToken
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Header
import retrofit2.http.Path

public interface UserClient {

    @POST("login")
    suspend fun login(@Body login: Login): LoginToken

    @GET("content")
    suspend fun getAllContent(@Header("Authorization") token: String) : List<Content>

    @GET("content/{id}")
    suspend fun getKontenById(@Header("Authorization") token: String, @Path("id") contentId: String): Content

}