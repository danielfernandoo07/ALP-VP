package com.example.vp_alpapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vp_alpapp.service.MyContainer
import kotlinx.coroutines.launch


class EditProfileViewModel : ViewModel() {


    fun editProfile(

        name: String,
        photo: String,
        password: String,
        bio: String

    ) {


        viewModelScope.launch {
            MyContainer().myRepos.updateUser(MyContainer.ACCESS_TOKEN, name,password, photo, bio)

        }

    }


}