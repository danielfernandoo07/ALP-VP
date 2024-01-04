package com.example.vp_alpapp.viewmodel

import android.service.autofill.UserData
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.model.Pengguna
import com.example.vp_alpapp.service.MyContainer
import kotlinx.coroutines.launch

class ExploreViewModel: ViewModel() {

    sealed interface ExploreUiState {
        data class Success(val data: List<Content>, val datauser: Pengguna) : ExploreUiState
        object Error : ExploreUiState
        object Loading : ExploreUiState

    }
    var exploreUiState:  ExploreUiState by mutableStateOf( ExploreUiState.Loading)
        private set

    lateinit var data: List<Content>
    lateinit var datauser: Pengguna

    init {
        loadKonten()
    }

    private fun loadKonten() {


        viewModelScope.launch{
            try {
                datauser = MyContainer().myRepos.getUser(MyContainer.ACCESS_TOKEN)
                data = MyContainer().myRepos.getAllContent(MyContainer.ACCESS_TOKEN)
                exploreUiState =  ExploreUiState.Success(data,datauser)
            }catch(e: Exception){
                Log.d("FAILED", e.message.toString())
                exploreUiState =  ExploreUiState.Error
            }
        }
    }

    fun delete(

        kontenId: String

    ) {


        viewModelScope.launch{
            MyContainer().myRepos.delete(MyContainer.ACCESS_TOKEN,kontenId)

        }



    }


}
