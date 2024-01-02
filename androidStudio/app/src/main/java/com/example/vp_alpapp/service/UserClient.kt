package com.example.vp_alpapp.service

import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.model.ContentUpdateRequest
import com.example.vp_alpapp.model.CreateContent
import com.example.vp_alpapp.model.Login
import com.example.vp_alpapp.model.LoginToken
import com.example.vp_alpapp.model.Pengguna
import com.example.vp_alpapp.model.Register
import com.example.vp_alpapp.model.RegisterInfo
import com.example.vp_alpapp.model.User
import com.example.vp_alpapp.model.UserId
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

public interface UserClient {

    @POST("login")
    suspend fun login(@Body login: Login) : LoginToken

    @POST("register")
    suspend fun register(@Body registerInfo: RegisterInfo): Register

    @GET("contents")
    suspend fun getAllContent(@Header("Authorization") token: String) : List<Content>

    @GET("content/{id}")
    suspend fun getKontenById(@Header("Authorization") token: String, @Path("id") contentId: String): Content

    @POST("content")
    suspend fun createContent(
        @Header("Authorization") token: String,
        @Body content: CreateContent
    )

    @GET("logout")
    suspend fun logout(@Header("Authorization") token: String)

    @DELETE("content/{id}")
    suspend fun delete(@Header("Authorization") token: String, @Path("id") contentId: String)

    @GET("user")
    suspend fun getUser(@Header("Authorization") token: String): Pengguna

    @GET("user/contents/{userId}")
    suspend fun getUserKonten(@Header("Aauthorization") token: String, @Path("userId") userId: String): List<Content>

    @PUT("content/{id}")
    suspend fun updateContent(@Path("id") id: Int, @Body request: ContentUpdateRequest)

}