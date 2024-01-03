package com.example.vp_alpapp.model

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import java.io.File

data class CreateContent(

    @SerializedName("headline") val headline: String,
    @SerializedName("file") val file: File,
    @SerializedName("content_text") val contentText: String,
    @SerializedName("category_id") val categoryId: Int,
    @SerializedName("user") val user: Pengguna

)