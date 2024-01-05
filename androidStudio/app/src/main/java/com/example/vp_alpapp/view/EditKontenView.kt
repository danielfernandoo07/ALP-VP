package com.example.vp_alpapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.vp_alpapp.ListScreen
import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.service.MyContainer
import com.example.vp_alpapp.viewmodel.DetailKontenViewModel
import com.example.vp_alpapp.viewmodel.EditContentViewModel
import com.example.vp_alpapp.viewmodel.RegisterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditContentView(
    editContentViewModel: EditContentViewModel,
    contentId: Content, // Content yang akan diedit
    navController: NavController
) {

    // Variabel untuk menyimpan nilai yang diedit
    var editedHeadline by rememberSaveable { mutableStateOf(contentId.headline) }
    var editedContentText by rememberSaveable { mutableStateOf(contentId.contentText) }
    var editedCategoryId by rememberSaveable { mutableIntStateOf(contentId.categoryId) }

//    var editedHeadline by rememberSaveable { mutableStateOf("contentId.headline") }
//    var editedContentText by rememberSaveable { mutableStateOf("contentId.contentText") }
//    var editedCategoryId by rememberSaveable { mutableStateOf("contentId.categoryId") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F3F3))
    ) {
        item {
            TextField(
                value = editedHeadline,
                onValueChange = { editedHeadline = it },
                label = { Text("Headline") },
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
        item {
            // Content
            TextField(
                value = editedContentText,
                onValueChange = { editedContentText = it },
                label = { Text("Content Text") },
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
        }
        item {
            // Category ID

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        // Toggle between 1 and 2
                        editedCategoryId = if (editedCategoryId == 1) 2 else 1
                    }
            ) {
                Text(
                    "Category ID (Click To Switch): ${if (editedCategoryId == 1) "News" else "Committee"}",
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }
        }

        item {
            // Tombol submit untuk menyimpan perubahan
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                // Button to submit
                Button(
                    onClick = {
                        editContentViewModel.edit(
                            kontenId = contentId.id.toString(),
                            categoryId = editedCategoryId.toString(),
                            context_text = editedContentText,
                            headline = editedHeadline,
                            navController = navController
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFF89715))
                ) {
                    Text(text = "Save Changes")
                }
            }
        }
    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun EditContentPreview() {
//
//val editContentViewModel: EditContentViewModel = viewModel()
//
//    EditContentView(editContentViewModel = editContentViewModel , contentId = null)
//}