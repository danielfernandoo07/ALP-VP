package com.example.vp_alpapp.viewmodel

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vp_alpapp.model.CreateContent
import com.example.vp_alpapp.service.MyContainer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

import java.util.UUID


class CreateContentViewModel: ViewModel() {

//    fun createContent(
//        headline: String,
//        image: Uri,
//        content_text: String,
//        category_id: Int,
//    ) {
//
//
//        viewModelScope.launch {
//
//            val user = MyContainer().myRepos.getUser(MyContainer.ACCESS_TOKEN)
//            val konten = CreateContent(headline, image, content_text, category_id, user)
//
//            MyContainer().myRepos.createContent(MyContainer.ACCESS_TOKEN, konten)
//
//        }
//
//    }

    @SuppressLint("Recycle")
    fun uploadAndCreateContent(
        headline: String,
        image: Uri,
        content_text: String,
        category_id: Int,
        context: Context
    ) {

        viewModelScope.launch {


            MyContainer().myRepos.createContent(MyContainer.ACCESS_TOKEN,headline,content_text,category_id,image,context)


        }

    }



}