package com.example.vp_alpapp.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.datastore.dataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vp_alpapp.DataStore
import com.example.vp_alpapp.service.MyContainer
import kotlinx.coroutines.launch


class LoginViewModel: ViewModel() {

    fun testLoad(
        dataStore: DataStore,
        context: Context
    ) {



        viewModelScope.launch {
            val token = MyContainer().myRepos.login("admin@example.com", "123")
            if(token.equals("Incorrect Password", true)){
                Toast.makeText(context, token, Toast.LENGTH_LONG).show()
            }else if(token.equals("User not found", true)){
                Toast.makeText(context, token, Toast.LENGTH_LONG).show()
            }else{

                Log.d("Token : ", token)
                dataStore.saveToken(token)

                dataStore.getToken.collect{token->
                    if(token != null){
                        MyContainer.ACCESS_TOKEN = token
                    }
                }
            }
        }

    }
}