package com.example.vp_alpapp.service

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.model.ContentUpdateRequest
import com.example.vp_alpapp.model.CreateContent
import com.example.vp_alpapp.model.Login
import com.example.vp_alpapp.model.Pengguna
import com.example.vp_alpapp.model.RegisterInfo
import com.example.vp_alpapp.model.UserUpdateReq2
import com.example.vp_alpapp.model.UserUpdateRequest
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection

class MyRepos(private val userClient: UserClient) {

    suspend fun login(email: String, password: String): String {

        val loginInfo = Login(email, password)

        val result = userClient.login(
            loginInfo
        )

        if (result.status.toInt() == HttpURLConnection.HTTP_OK) {
            return result.data as String
        }

        return result.message;

    }

    suspend fun getAllContent(token: String): List<Content> {


        return userClient.getAllContent("Bearer $token")


    }

    suspend fun getKontenById(token: String, id: String): Content {

        return userClient.getKontenById("Bearer $token", id)
    }

    suspend fun createContent(
        token: String,
        headline: String,
        contentText: String,
        categoryId: Int,
        image: Uri,
        context: Context
    ) {
        // Mendapatkan objek user dan mengonversinya menjadi string JSON
        val userJson = Gson().toJson(MyContainer().myRepos.getUser(MyContainer.ACCESS_TOKEN))

        // Membuat RequestBody untuk user
        val userRequestBody = userJson.toRequestBody("application/json".toMediaTypeOrNull())

        // Mengonversi headline, contentText, dan categoryId menjadi RequestBody
        val headlinePart = headline.toRequestBody("text/plain".toMediaTypeOrNull())
        val contentTextPart = contentText.toRequestBody("text/plain".toMediaTypeOrNull())
        val categoryIdPart = categoryId.toString().toRequestBody("text/plain".toMediaTypeOrNull())

        // Mengonversi file Uri menjadi File

        val fileDir = context.filesDir
        val file = File(fileDir, "image.png")

        val inputStream = context.contentResolver.openInputStream(image)
        inputStream?.use { input ->
            val outputStream = FileOutputStream(file)
            outputStream.use { output ->
                input.copyTo(output)
            }
        }

        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())

        val part = MultipartBody.Part.createFormData("file", file.name, requestBody)


        // Memanggil fungsi createContent di userClient
        userClient.createContent(
            token,
            headlinePart,
            contentTextPart,
            categoryIdPart,
            userRequestBody,
            part
        )
    }

    suspend fun logout(token: String) {
        return userClient.logout(token)
    }

    suspend fun delete(token: String, id: String) {
        return userClient.delete(token, id)
    }

    suspend fun getUser(token: String): Pengguna {
        return userClient.getUser(token)
    }

    suspend fun getUserbyId(token: String, id: String): Pengguna {

        return  userClient.getUserbyId(token,id)

    }

    suspend fun getSomeUserContent(token: String, id: String): List<Content> {

        return userClient.getSomeUserContent(token, id)

    }

    suspend fun register(
        name: String,
        email: String,
        nim: String,
        password: String,
        prodi_id: Int
    ): Pengguna {

        val pengguna = RegisterInfo(name, email, nim, password, prodi_id)

        return userClient.register(pengguna).pengguna

    }

    suspend fun getUserKonten(token: String, userId: String): List<Content> {

        return userClient.getUserKonten(token, userId)

    }

    suspend fun updateContent(
        token: String,
        id: String, headline: String, contentText: String, categoryId: String
    ) {

        // Prepare the request body
        val request = ContentUpdateRequest(headline, contentText, categoryId)

        // Make the API call using the userClient
        userClient.updateContent(token, id, request)


    }

    suspend fun updateUserV2(
        token: String,
        name: String,
        image: Uri,
        bio: String,
        context: Context,
        password: String
    ) = runBlocking {

        val namePart = name.toRequestBody()
        val bioPart = bio.toRequestBody()
        val passwordPart = password.toRequestBody()

        // Create an extension function for converting String to RequestBody
        fun String.toRequestBody(): RequestBody {
            return this.toRequestBody("text/plain".toMediaTypeOrNull())
        }

        // Handle potential IOException
        try {
            val fileDir = context.filesDir
            val file = File(fileDir, "photo.png")

            val inputStream = context.contentResolver.openInputStream(image)
            inputStream?.use { input ->
                val outputStream = FileOutputStream(file)
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }

            val requestBody = file.asRequestBody("photo/*".toMediaTypeOrNull())
            val imagePart = MultipartBody.Part.createFormData("file", file.name, requestBody)

            // Logging
            Log.d("YourTag", "Token: $token")
            Log.d("YourTag", "Name: $name")
            Log.d("YourTag", "Bio: $bio")
            Log.d("YourTag", "Password: $password")
            Log.d("YourTag", "Image RequestBody Size: ${imagePart.body.contentLength()}")

            // Use your Retrofit client to make the update request
            userClient.updateUser(token, namePart, bioPart, imagePart, password = passwordPart)

        } catch (e: IOException) {
            // Handle the IOException (e.g., log or show an error message)
            e.printStackTrace()
        }
    }

    suspend fun updateUser(
        token: String,
        name: String,
        password: String,
        image: String,
        bio: String
    ) {

//        val request = UserUpdateRequest(name, password, image, bio)

//        userClient.updateUser(token, request)

    }

    suspend fun createContentWithoutPhoto(
        token: String,
        headline: String,
        content_text: String,
        category_id: String

    ) {

        val CreateContent = CreateContent(headline,content_text,category_id)

        val requestBody = FormBody.Builder()
            .add("headline", headline)
            .add("content_text", content_text)
            .add("category_id", category_id)
            .build()

        userClient.createContentWithoutPhoto(token,requestBody)

    }


    suspend fun updateUserV3(
        token: String,
        name: String,
        bio: String,
        password: String,
        context:Context
    ) {

        Log.d("bio", bio)
        var bioku = bio
        if (bio.isNullOrEmpty()) {
            bioku = "{null}"
        }

        if (password == "") {

            val UserUpdateRequest = UserUpdateReq2(name,bioku)
            userClient.updateUserRaw1(token,UserUpdateRequest)
        }
        else {
            val UserUpdateRequest = UserUpdateRequest(name,password,bio)


            userClient.updateUserRaw(token,UserUpdateRequest)
        }




    }

}