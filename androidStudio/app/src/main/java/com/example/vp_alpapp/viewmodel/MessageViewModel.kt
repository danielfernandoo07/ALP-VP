package com.example.vp_alpapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.vp_alpapp.R
import com.example.vp_alpapp.model.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MessageViewModel : ViewModel() {
    private val _uistate = MutableStateFlow<List<Message>>(emptyList())
    val uistate: StateFlow<List<Message>> =_uistate.asStateFlow()

    fun createChat(chat : String) {

        val message = Message(chat)
        val copy = _uistate.value.toMutableList()
        copy.add(message)
        _uistate.value = copy

    }
}