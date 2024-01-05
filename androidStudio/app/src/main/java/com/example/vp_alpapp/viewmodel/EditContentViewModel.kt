package com.example.vp_alpapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.vp_alpapp.ListScreen
import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.service.MyContainer
import kotlinx.coroutines.launch

class EditContentViewModel: ViewModel() {


    suspend fun getKontenId(
        id: String
    ): Content {


        return MyContainer().myRepos.getKontenById(MyContainer.ACCESS_TOKEN, id )


    }

    fun edit(
        kontenId: String,
        headline: String,
        categoryId: String,
        context_text: String,
        navController: NavController

        ) {

        viewModelScope.launch {

            MyContainer().myRepos.updateContent(MyContainer.ACCESS_TOKEN, kontenId, headline = headline, context_text , categoryId = categoryId)
            navController.navigate(ListScreen.Profile.name)


        }
    }


}