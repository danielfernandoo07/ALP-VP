package com.example.vp_alpapp.view

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
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

    Column(
        Modifier.fillMaxWidth()
    ) {
        // TextField untuk mengedit headline
        TextField(
            value = editedHeadline,
            onValueChange = { editedHeadline = it },
            label = { Text("Headline") }
        )

        // TextField untuk mengedit content_text
        TextField(
            value = editedContentText,
            onValueChange = { editedContentText = it },
            label = { Text("Content Text") }
        )

        // TextField untuk mengedit category_id
        TextField(
            value = editedCategoryId.toString(),
            onValueChange = { editedCategoryId = it.toIntOrNull() ?: 0 },
            label = { Text("Category ID") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )

        // Tombol submit untuk menyimpan perubahan
        Button(
            onClick = {

                editContentViewModel.edit(kontenId = contentId.id.toString(), categoryId = editedCategoryId.toString(), context_text = editedContentText, headline = editedHeadline, navController = navController)

            }
        ) {
            Text(text = "Save Changes")
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