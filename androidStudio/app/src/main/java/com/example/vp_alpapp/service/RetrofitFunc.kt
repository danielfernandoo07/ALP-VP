package com.example.vp_alpapp.service

import com.example.vp_alpapp.model.Login
import com.example.vp_alpapp.model.LoginToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RetrofitFunc {
//
//    val userClient: UserClient = RetrofitClient.getRetrofit().create(UserClient::class.java)
//
//
//
//    suspend fun getToken(): String {
//        // Ganti parameter sesuai kebutuhan
//        val call = userClient.login(Login("admin@example.com", "123"))
//
//        return suspendCoroutine { continuation ->
//            call.enqueue(object : Callback<LoginToken?> {
//                override fun onResponse(call: Call<LoginToken?>, response: Response<LoginToken?>) {
//                    val token = response.body()?.token.toString()
//                    continuation.resume(token)
//                }
//
//                override fun onFailure(call: Call<LoginToken?>, t: Throwable) {
//                    continuation.resume(t.message.toString())
//                }
//            })
//        }
//
//
//    }
}