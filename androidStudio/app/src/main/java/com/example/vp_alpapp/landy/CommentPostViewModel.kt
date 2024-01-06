package com.example.vp_alpapp.landy

import androidx.lifecycle.ViewModel
import com.example.vp_alpapp.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CommentPostViewModel : ViewModel() {

    private val _uistate = MutableStateFlow<List<User>>(emptyList())
    val uistate: StateFlow<List<User>> =_uistate.asStateFlow()

    fun createComment(comment : String) {

        val user = User("User01", R.drawable.ellipse_5, "098712654321", "1h", comment)
        val copy = _uistate.value.toMutableList()
        copy.add(user)
        _uistate.value = copy

    }
}