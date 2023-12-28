package com.example.vp_alpapp.view

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vp_alpapp.model.CreateContent
import com.example.vp_alpapp.model.User
import com.example.vp_alpapp.service.MyContainer
import com.example.vp_alpapp.viewmodel.CreateContentViewModel


@Composable
fun AddPostView(

    createContent: CreateContentViewModel

) {


    var headline by rememberSaveable { mutableStateOf("") }
    var image by rememberSaveable { mutableStateOf("") }
    var content_text by rememberSaveable { mutableStateOf("") }
    var category_id by rememberSaveable { mutableStateOf(1) }


    Button(onClick = {

        createContent.createContent(headline,image,content_text,category_id)
    }) {

        Text(text = "POST")
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AddPostPreview() {

    val createContent: CreateContentViewModel = viewModel()


}