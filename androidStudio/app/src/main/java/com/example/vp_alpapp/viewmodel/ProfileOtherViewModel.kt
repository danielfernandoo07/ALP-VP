package com.example.vp_alpapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.vp_alpapp.ListScreen
import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.model.Pengguna
import com.example.vp_alpapp.service.MyContainer
import kotlinx.coroutines.launch

class ProfileOtherViewModel: ViewModel() {


    sealed interface Profile2UIState {
        data class Success(val data: Pengguna, val data1: List<Content>, val data2: Pengguna) : Profile2UIState
        object Error : Profile2UIState
        object Loading : Profile2UIState

    }

        var profile2UiState:  Profile2UIState  by mutableStateOf(Profile2UIState.Loading)
            private set

        lateinit var data: Pengguna
        lateinit var data1: List<Content>
    lateinit var data2: Pengguna



    fun load(
            userID : String
        ) {

            viewModelScope.launch {


                try {

                    data = MyContainer().myRepos.getUserbyId(MyContainer.ACCESS_TOKEN,userID)
                    data1 = MyContainer().myRepos.getSomeUserContent(MyContainer.ACCESS_TOKEN, userID)
                    data2 = MyContainer().myRepos.getUser(MyContainer.ACCESS_TOKEN)

                    profile2UiState = Profile2UIState.Success(data,data1, data2)

                }catch(e: Exception){
                    Log.d("FAILED", e.message.toString())
                }

            }

        }





}