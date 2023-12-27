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

sealed interface KontenDetailUiState{
    data class Success(val data: Content): KontenDetailUiState
    object Error: KontenDetailUiState
    object Loading: KontenDetailUiState
}

class DetailKontenViewModel: ViewModel() {

    private lateinit var data: Content

    var kontenDetailUiState: KontenDetailUiState by mutableStateOf(KontenDetailUiState.Loading)

    fun getById(id: String) {
        viewModelScope.launch {

            try {
                data = MyContainer().myRepos.getKontenById(MyContainer.ACCESS_TOKEN,id)
                 kontenDetailUiState = KontenDetailUiState.Success(data)
                Log.d("KONTEN", data.id.toString())
                Log.d("KONTEN", data.image)
            }catch(e: Exception){
                Log.d("FAILED", e.message.toString())
                kontenDetailUiState = KontenDetailUiState.Error
            }
        }
    }
}