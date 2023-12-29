package com.example.vp_alpapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.vp_alpapp.BottomNavigationBar
import com.example.vp_alpapp.service.MyContainer

@Composable
fun ProfileView(
    navController: NavController
) {

    Column {


        Text(text = "THIS IS PROFILE")

        BottomNavigationBar(navController)

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfilePreview(){

}