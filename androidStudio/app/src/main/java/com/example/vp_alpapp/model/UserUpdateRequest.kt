package com.example.vp_alpapp.model

import kotlinx.serialization.SerialName

class UserUpdateRequest (
    @SerialName("name") val name: String? = null,
    @SerialName("password") val password: String? = null,
    @SerialName("photo") val photo: String? = null,
    @SerialName("bio") val bio: String? = null
)