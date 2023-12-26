package com.example.vp_alpapp.service

import com.example.vp_alpapp.model.Login
import java.net.HttpURLConnection

class MyRepos(private val userClient: UserClient) {

    suspend fun login(email: String, password: String):String {

        val loginInfo = Login(email,password)

        val result = userClient.login(
            loginInfo
        )

        if(result.status.toInt() == HttpURLConnection.HTTP_OK){
            return result.data as String
        }

        return result.message;

    }

    suspend fun getAllContent() {

    }
}