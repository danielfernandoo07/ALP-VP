package com.example.vp_alpapp.service

import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.model.CreateContent
import com.example.vp_alpapp.model.Login
import com.example.vp_alpapp.model.Pengguna
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

    suspend fun getAllContent(token: String): List<Content> {


        return userClient.getAllContent("Bearer $token")


    }

    suspend fun getKontenById(token: String, id: String): Content {

        return userClient.getKontenById("Bearer $token", id)
    }

    suspend fun createContent(token:String,content: CreateContent) {
        return userClient.createContent("Bearer $token",content);
    }

    suspend fun logout(token: String) {
        return userClient.logout(token)
    }

    suspend fun delete(token: String, id:String){
        return userClient.delete(token,id)
    }

    suspend fun getUser(token: String): Pengguna{
        return userClient.getUser(token)
    }


}