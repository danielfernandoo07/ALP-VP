package com.example.vp_alpapp.model

import com.google.gson.annotations.SerializedName

data class ContentUpdateRequest (
    @SerializedName("headline") val headline: String,
    @SerializedName("image") val image: String,
    @SerializedName("content_text") val content_text: String,
    @SerializedName("category_id") val category_id: Int
)
