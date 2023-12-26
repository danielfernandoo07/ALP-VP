package com.example.vp_alpapp.model

import com.google.gson.annotations.SerializedName

data class Content(

    @SerializedName("body")
    val content_id: Int,
    val headline: String,
    val image: String,
    val content_text: String,
    val category_id: Int,
    val user_id: Int,
    val created_at: String,
    val user: User


)