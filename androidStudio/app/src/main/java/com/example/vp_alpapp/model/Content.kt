package com.example.vp_alpapp.model

import com.google.gson.annotations.SerializedName

data class Content(

    @SerializedName("body")
    val category_id: Int,
    val content_text: String,
    val created_at: String,
    val headline: String,
    val id: Int,
    val image: String,
    val user_id: UserId
)