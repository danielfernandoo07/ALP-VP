package com.example.vp_alpapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.service.MyContainer
import kotlinx.coroutines.launch


sealed interface HomeUIState {
    data class Success(val data: List<Content>) : HomeUIState
    object Error : HomeUIState
    object Loading : HomeUIState

}
class HomeViewModel() : ViewModel() {

    var homeUIState: HomeUIState by mutableStateOf(HomeUIState.Loading)
        private set

    private lateinit var data: List<Content>

    init {
        loadKonten()
    }

    private fun loadKonten() {


        viewModelScope.launch{
            try {
                data = MyContainer().myRepos.getAllContent(MyContainer.ACCESS_TOKEN)
                homeUIState = HomeUIState.Success(data)
            }catch(e: Exception){
                Log.d("FAILED", e.message.toString())
                homeUIState = HomeUIState.Error
            }
        }
    }




}