package com.example.vp_alpapp.service

import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.model.ContentUpdateRequest
import com.example.vp_alpapp.model.CreateContent
import com.example.vp_alpapp.model.Login
import com.example.vp_alpapp.model.Pengguna
import com.example.vp_alpapp.model.RegisterInfo
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

    suspend fun register(name:String, email: String,nim:String, password: String, prodi_id: Int): Pengguna {

        val pengguna = RegisterInfo(name,email,nim,password, prodi_id)

        return userClient.register(pengguna).pengguna

    }

    suspend fun getUserKonten(token: String, userId: String): List<Content> {

        return userClient.getUserKonten(token,userId)

    }

    suspend fun updateContent(id: Int, headline: String, image: String, contentText: String, categoryId: Int) {

            // Prepare the request body
            val request = ContentUpdateRequest(headline, image, contentText, categoryId)

            // Make the API call using the userClient
            val response = userClient.updateContent(id, request)


       
    }

}