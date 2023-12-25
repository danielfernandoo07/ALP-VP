package com.example.vp_alpapp


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.vp_alpapp.model.Login
import com.example.vp_alpapp.model.LoginToken
import com.example.vp_alpapp.service.UserClient
import com.example.vp_alpapp.ui.theme.VPALPAPPTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginTest: ComponentActivity() {


    private val retrofit: Retrofit = Retrofit.Builder()
        //link api e kita nanti
        .baseUrl("https://api.escuelajs.co/api/v1/auth/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

//    private val login: Login = Login("admin@example.com", "123")


    val userClient = retrofit.create(UserClient::class.java)
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

        var token by remember { mutableStateOf("LOADING...") }


        LaunchedEffect(key1 = true) {

            //email password diganti di bagian sini, jadikin variabel mutable state trs teksfield dll
            val call = userClient.login(Login("john@mail.com", "changeme"))

            call.enqueue(object : Callback<LoginToken?> {
                override fun onResponse(call: Call<LoginToken?>, response: Response<LoginToken?>) {

                    token = response.body()?.token.toString()
                }

                override fun onFailure(call: Call<LoginToken?>, t: Throwable) {

                    token = t.message.toString()
                }
            })

        }

        //buat ngecek token
        Text(text =  token)




    }

    @Preview(showBackground = true)
    @Composable
    fun LoginTestView() {
        VPALPAPPTheme {
            LoginTestScreen()
        }
    }
}

