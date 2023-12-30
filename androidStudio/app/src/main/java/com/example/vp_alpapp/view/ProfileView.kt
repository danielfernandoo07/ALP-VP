package com.example.vp_alpapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.vp_alpapp.BottomNavigationBar
import com.example.vp_alpapp.model.Pengguna
import com.example.vp_alpapp.service.MyContainer
import com.example.vp_alpapp.viewmodel.ProfileUiState
import com.example.vp_alpapp.viewmodel.ProfileViewModel

@Composable
fun ProfileView(
    navController: NavController,
    user: Pengguna,
     profileViewModel: ProfileViewModel,

) {

    Column {


        Text(text = "THIS IS PROFILE")

        Text(text = user.email)

        Button(onClick = { profileViewModel.logout(navController)}) {

            Text(text = "Logout")
        }

        BottomNavigationBar(navController)

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfilePreview(){

}