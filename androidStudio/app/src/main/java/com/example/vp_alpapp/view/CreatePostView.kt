package com.example.vp_alpapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vp_alpapp.R
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.input.ImeAction


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


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F3F3))
    ) {
        Row {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
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
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp) // Use padding for left and right margins
                .clip(RoundedCornerShape(16.dp)) // Adjust the corner radius as needed
                .background(Color(0xFFFBFBFB))
                .padding(vertical = 4.dp),
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profilepic),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    TextField(
                        value = headline,
                        onValueChange = { headline = it },
                        placeholder = { Text("What's New...") },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.Black,
                            cursorColor = Color(0xFF8F8F8F),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            containerColor = Color.Transparent,
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        textStyle = TextStyle(fontSize = 18.sp, color = Color.Black)
                    )
                }

                // Content
                TextField(
                    value = contentText,
                    onValueChange = { contentText = it },
                    placeholder = { Text("Your Content Goes Here...") },
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color(0xFF8F8F8F),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = Color.Transparent,
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textStyle = TextStyle(fontSize = 12.sp, color = Color.Black)
                )

                // Category ID
                TextField(
                    value = categoryId.toString(),
                    onValueChange = { categoryId = it.toIntOrNull() ?: 0 },
                    label = { Text("Category ID") },
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color(0xFF8F8F8F),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = Color.Transparent,
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textStyle = TextStyle(fontSize = 12.sp, color = Color.Black)
                )
                // IMG URL
                TextField(
                    value = image,
                    onValueChange = { image = it },
                    label = { Text("Image URL") },
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color(0xFF8F8F8F),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textStyle = TextStyle(fontSize = 12.sp, color = Color.Black)
                )
            }
        }
//        BottomNavigationBar(navController = navController)
    }


}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AddPostPreview() {
//    AddPostView()
//    val createContent: CreateContentViewModel = viewModel()
}