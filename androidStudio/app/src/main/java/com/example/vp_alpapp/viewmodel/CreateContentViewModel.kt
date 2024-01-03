package com.example.vp_alpapp.viewmodel

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vp_alpapp.model.CreateContent
import com.example.vp_alpapp.service.MyContainer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
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

    fun uploadAndCreateContent(
        headline: String,
        image: Uri,
        content_text: String,
        category_id: Int,
        context: Context
    ) {

        viewModelScope.launch {
            val user = MyContainer().myRepos.getUser(MyContainer.ACCESS_TOKEN)


            val imageFile: File? = uriToFile(context, image)

            // Call repository to upload file and create content
            imageFile?.let { CreateContent(headline, it, content_text, category_id, user) }?.let {
                MyContainer().myRepos.createContent(
                    MyContainer.ACCESS_TOKEN,
                    it
                )
            }
        }
        // Convert Uri to File

    }


    fun uriToFile(context: Context, uri: Uri): File? {
        var inputStream: InputStream? = null
        var file: File? = null

        try {
            inputStream = context.contentResolver.openInputStream(uri)
            if (inputStream != null) {
                file = createTemporalFileFrom(inputStream, context)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                inputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return file
    }

    @Throws(IOException::class)
    private fun createTemporalFileFrom(inputStream: InputStream, context: Context): File {
        var targetFile: File? = null

        if (inputStream != null) {
            val extension = getFileExtension(context.contentResolver, Uri.fromFile(File("temp")))
            val fileName = "${System.currentTimeMillis()}.$extension"

            val outputDir = context.cacheDir
            targetFile = File(outputDir, fileName)

            val outputStream = FileOutputStream(targetFile)

            try {
                val buffer = ByteArray(1024)
                var read: Int
                while (inputStream.read(buffer).also { read = it } != -1) {
                    outputStream.write(buffer, 0, read)
                }
                outputStream.flush()
            } finally {
                outputStream.close()
            }
        }

        return targetFile ?: throw IOException("Failed to create temporal file")
    }

    private fun getFileExtension(contentResolver: ContentResolver, uri: Uri): String? {
        val mimeType = contentResolver.getType(uri)
        return if (mimeType == null) {
            // Fallback to default extension if MIME type is not available
            MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType)
        } else {
            // Extract extension from URI if MIME type is not available
            uri.lastPathSegment?.substringAfterLast(".", "") ?: ""
        }
    }
}