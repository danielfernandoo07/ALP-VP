package com.example.vp_alpapp.landy

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddFriendViewModel : ViewModel() {

    private val _uistate = MutableStateFlow(Friendship())
    val uistate: StateFlow<Friendship> =_uistate.asStateFlow()

    fun isClicked(result: Boolean) {
        _uistate.update {
            it.copy(
                isFollow = result
            )
        }
    }

}