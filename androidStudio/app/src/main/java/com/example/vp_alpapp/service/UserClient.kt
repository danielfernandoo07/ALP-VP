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
import com.example.vp_alpapp.model.UserUpdateRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.PUT
import retrofit2.http.Part
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

    @Multipart
    @POST("content")
    suspend fun createContent(
        @Header("Authorization") token: String,
        @Part("headline") headline: RequestBody,
        @Part("content_text") contentText: RequestBody,
        @Part("category_id") categoryId: RequestBody,
        @Part("user") user: RequestBody,
        @Part file: MultipartBody.Part
    )

    @GET("logout")
    suspend fun logout(@Header("Authorization") token: String)

    @DELETE("content/{id}")
    suspend fun delete(@Header("Authorization") token: String, @Path("id") contentId: String)

    @GET("user")
    suspend fun getUser(@Header("Authorization") token: String): Pengguna

    @GET("user/contents/{userId}")
    suspend fun getUserKonten(@Header("Aauthorization") token: String, @Path("userId") userId: String): List<Content>

    @PATCH("content/{id}")
    suspend fun updateContent(@Header("Aauthorization") token: String,@Path("id") id: String, @Body request: ContentUpdateRequest)

    @Multipart
    @PATCH("user")
    suspend fun updateUser(
        @Header("Authorization") token: String,
        @Part("name") name: RequestBody,
        @Part("bio") bio: RequestBody,
        @Part("password") password: RequestBody,
        @Part file: MultipartBody.Part
    )



}