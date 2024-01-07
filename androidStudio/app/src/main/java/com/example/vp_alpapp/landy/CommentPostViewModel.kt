package com.example.vp_alpapp.landy

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.vp_alpapp.ListScreen
import com.example.vp_alpapp.R
import com.example.vp_alpapp.model.Commentku
import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.model.Pengguna
import com.example.vp_alpapp.service.MyContainer
import com.example.vp_alpapp.viewmodel.ProfileOtherViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CommentPostViewModel : ViewModel() {

//    private val _uistate = MutableStateFlow<List<User>>(emptyList())
//    val uistate: StateFlow<List<User>> =_uistate.asStateFlow()
sealed interface CommentPostViewUIState {
    data class Success(val data: Content, val data1: List<Commentku>) : CommentPostViewUIState
    object Error : CommentPostViewUIState
    object Loading : CommentPostViewUIState

}

    var commentPostViewUIState:  CommentPostViewUIState  by mutableStateOf(CommentPostViewUIState.Loading)
        private set

    lateinit var data: Content
    lateinit var data1: List<Commentku>

    fun getData(
        id: String,
    ) {


        viewModelScope.launch {

            data= MyContainer().myRepos.getKontenById(MyContainer.ACCESS_TOKEN, id)
            data1 = MyContainer().myRepos.getComment(MyContainer.ACCESS_TOKEN, content_id = id)

           commentPostViewUIState = CommentPostViewUIState.Success(data,data1)

        }


    }

    fun create(
        content_id: String,
        comment_text: String,
        navController: NavController
    ) {
        viewModelScope.launch {
            MyContainer().myRepos.createComment(MyContainer.ACCESS_TOKEN, content_id, comment_text)
            navController.navigate(ListScreen.CommentView.name+"/"+content_id)


        }
    }
}

