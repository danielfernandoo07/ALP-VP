package com.example.vp_alpapp.landy

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vp_alpapp.R
import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.model.Pengguna
import com.example.vp_alpapp.service.MyContainer
import com.example.vp_alpapp.viewmodel.ProfileOtherViewModel
import com.example.vp_alpapp.viewmodel.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MessageViewModel : ViewModel() {
    sealed interface MessageUIState {
        data class Success(val data: Pengguna) : MessageUIState
        object Error : MessageUIState
        object Loading : MessageUIState

    }

    var messageUIState: MessageViewModel.MessageUIState by mutableStateOf(
        MessageViewModel.MessageUIState.Loading)
        private set

    lateinit var data: Pengguna

    private val _uistate = MutableStateFlow<List<Message>>(emptyList())
    val uistate: StateFlow<List<Message>> =_uistate.asStateFlow()

    fun createChat(chat : String) {

        val message = Message(chat)
        val copy = _uistate.value.toMutableList()
        copy.add(message)
        _uistate.value = copy

    }

    fun getYangDichat(

        id: String

    ) {

        viewModelScope.launch {


        try {
            data = MyContainer().myRepos.getUserbyId(MyContainer.ACCESS_TOKEN, id)
            messageUIState = MessageUIState.Success(data)
        } catch (e: Exception) {
            Log.d("FAILED", e.message.toString())
        }

    }

    }


}