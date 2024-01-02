package com.example.vp_alpapp.viewmodel

import android.content.Context
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.model.CreateContent
import com.example.vp_alpapp.service.MyContainer
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.Paths


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

    fun uploadAndCreateContent(
        headline: String,
        image: Uri,
        content_text: String,
        category_id: Int,
        context: Context
    ) {

        viewModelScope.launch {
            val user = MyContainer().myRepos.getUser(MyContainer.ACCESS_TOKEN)
            val file = File(context.cacheDir, "temp_file").also { tempFile ->
                context.contentResolver.openInputStream(image).use { inputStream ->
                    FileOutputStream(tempFile).use { outputStream ->
                        inputStream?.copyTo(outputStream)
                    }
                }
            }

            // Upload file to server
            val imagePart = MultipartBody.Part.createFormData(
                "image",
                file.name,
                file.asRequestBody("image/*".toMediaTypeOrNull())
            )

            // Call repository to upload file and create content
            MyContainer().myRepos.createContent(
                MyContainer.ACCESS_TOKEN,
                CreateContent(headline, imagePart, content_text, category_id, user)
            )
        }
        // Convert Uri to File

    }

}