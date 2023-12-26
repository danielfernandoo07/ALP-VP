package com.example.vp_alpapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.model.ContentList
import com.example.vp_alpapp.model.Login
import com.example.vp_alpapp.model.LoginToken
import com.example.vp_alpapp.service.RetrofitClient
import com.example.vp_alpapp.service.RetrofitFunc
import com.example.vp_alpapp.service.UserClient
import com.example.vp_alpapp.ui.theme.VPALPAPPTheme
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder


class LoginTest: ComponentActivity() {




     private val retrofit: Retrofit = RetrofitClient.getRetrofit()

//    private val login: Login = Login("admin@example.com", "123")


    private val userClient: UserClient = retrofit.create(UserClient::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VPALPAPPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    LoginTestScreen()

                }
            }
        }
    }

    @Composable
    fun LoginTestScreen() {

        var token by remember { mutableStateOf("") }

        var hasilkonten by remember { mutableStateOf("LOADING KONTEN...\n") }


        var newsList by remember { mutableStateOf<List<Content>?>(null) }


        LaunchedEffect(key1 = true) {

            //email password diganti di bagian sini, jadikin variabel mutable state trs teksfield dll
//            val call = userClient.login(Login("admin@example.com", "123"))
//
//            call.enqueue(object : Callback<LoginToken?> {
//                override fun onResponse(call: Call<LoginToken?>, response: Response<LoginToken?>) {
//
//                    token = response.body()?.token.toString()
//
//                    Log.d("TOKEN", token)
//                }
//
//                override fun onFailure(call: Call<LoginToken?>, t: Throwable) {
//
//                    token = t.message.toString()
//                }
//            })

            val tokenku = "1|aICsxo8WDdiSZvH9V3SSvjg23sb5sRRtSuPtGzFN4859bf86"

            val callContent = userClient.getAllContent("Bearer $tokenku")

            val str = StringBuilder()

            callContent.enqueue(object : Callback<List<Content>?> {

                override fun onResponse(
                    call: Call<List<Content>?>,
                    response: Response<List<Content>?>
                ) {

                    if (response.isSuccessful && response.body() != null){


                        for (konten in response.body()!!) {

                            Log.d("HASIL : ",konten.headline)

                            Log.d("Content ID", konten.content_id.toString())
                        }

                        hasilkonten = str.toString()
                    }

                    newsList = response.body()

                }

                override fun onFailure(call: Call<List<Content>?>, t: Throwable) {

                    hasilkonten = t.message.toString()
                }
            })


        }

        //buat desain e di sini?









    }

    @Preview(showBackground = true)
    @Composable
    fun LoginTestView() {
        VPALPAPPTheme {
            LoginTestScreen()
        }
    }
}

