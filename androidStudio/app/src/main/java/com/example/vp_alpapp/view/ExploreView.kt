package com.example.vp_alpapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.vp_alpapp.BottomNavigationBar

@Composable
fun ExploreView(

    navController: NavController
) {

    Column {
        Text(text = "EXPLORE")



        BottomNavigationBar(navController = navController)


    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ExplorePreview() {

}
