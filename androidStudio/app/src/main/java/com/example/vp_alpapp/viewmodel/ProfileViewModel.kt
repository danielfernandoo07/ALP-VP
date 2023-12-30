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

sealed interface ProfileUiState {
    data class Success(val data: Pengguna) : ProfileUiState
    object Error : ProfileUiState
    object Loading : ProfileUiState

}

class ProfileViewModel : ViewModel()
{

    var profileUiState:  ProfileUiState by mutableStateOf(ProfileUiState.Loading)
        private set

    lateinit var data: Pengguna

    init {
        load()
    }


    private fun load() {

        viewModelScope.launch {


            try {
                data = MyContainer().myRepos.getUser(MyContainer.ACCESS_TOKEN)
                profileUiState = ProfileUiState.Success(data)
            }catch(e: Exception){
                Log.d("FAILED", e.message.toString())
                profileUiState = ProfileUiState.Error
            }

        }

    }
    public fun logout(
        navController: NavController
    ) {

        viewModelScope.launch {

            MyContainer().myRepos.logout(MyContainer.ACCESS_TOKEN)

            MyContainer.ACCESS_TOKEN = ""

            navController.navigate(ListScreen.Login.name)
        }

    }

}