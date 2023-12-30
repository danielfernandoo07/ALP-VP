package com.example.vp_alpapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.vp_alpapp.BottomNavigationBar
import com.example.vp_alpapp.model.CreateContent
import com.example.vp_alpapp.model.User
import com.example.vp_alpapp.service.MyContainer
import com.example.vp_alpapp.viewmodel.CreateContentViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPostView(

    createContent: CreateContentViewModel,
    navController: NavController

) {

    var headline by rememberSaveable { mutableStateOf("") }
    var image by rememberSaveable { mutableStateOf("") }
    var contentText by rememberSaveable { mutableStateOf("") }
    var categoryId by rememberSaveable { mutableStateOf(1) }


    //desain chat gpt
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Headline TextField
        TextField(
            value = headline,
            onValueChange = { headline = it },
            label = { Text("Headline") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Image TextField
        TextField(
            value = image,
            onValueChange = { image = it },
            label = { Text("Image URL") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Content Text TextField
        TextField(
            value = contentText,
            onValueChange = { contentText = it },
            label = { Text("Content Text") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Category ID TextField
        TextField(
            value = categoryId.toString(),
            onValueChange = { categoryId = it.toIntOrNull() ?: 0 },
            label = { Text("Category ID") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Button to submit
        Button(
            onClick = {
                createContent.createContent(
                    headline = headline,
                    image = image,
                    content_text = contentText,
                    category_id = categoryId
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "POST")
        }

        BottomNavigationBar(navController = navController)
    }



}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AddPostPreview() {

    val createContent: CreateContentViewModel = viewModel()


}