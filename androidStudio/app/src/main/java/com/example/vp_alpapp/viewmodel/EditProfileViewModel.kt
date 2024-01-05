package com.example.vp_alpapp.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.vp_alpapp.ListScreen
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


    @SuppressLint("Recycle")
    fun editProfileV2(
        name: String,
        image: Uri,
        bio: String,
        context: Context,
        navController: NavController,
        password: String

    ) {

        viewModelScope.launch {


            MyContainer().myRepos.updateUserV2(MyContainer.ACCESS_TOKEN,name,image,bio,context, password)

            navController.navigate(ListScreen.Profile.name)


        }

    }


}