package com.example.vp_alpapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vp_alpapp.model.CreateContent
import com.example.vp_alpapp.service.MyContainer
import kotlinx.coroutines.launch

class CreateContentViewModel: ViewModel() {

    fun createContent(
        headline:String,
        image: String,
        content_text: String,
        category_id: Int,
    ) {


        viewModelScope.launch {

            val user = MyContainer().myRepos.getUser(MyContainer.ACCESS_TOKEN)
            val konten = CreateContent(headline,image,content_text,category_id, user)

            MyContainer().myRepos.createContent(MyContainer.ACCESS_TOKEN,konten)

        }

    }

}