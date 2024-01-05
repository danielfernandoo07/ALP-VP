package com.example.vp_alpapp.view

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.dataStore
import androidx.lifecycle.viewModelScope
import com.example.vp_alpapp.DataStore
import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.viewmodel.HomeViewModel
import com.example.vp_alpapp.viewmodel.LoginViewModel
import com.example.vp_alpapp.viewmodel.HomeUIState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.vp_alpapp.BottomNavigationBar
import com.example.vp_alpapp.Routes
import com.example.vp_alpapp.model.CreateContent
import com.example.vp_alpapp.model.User
import com.example.vp_alpapp.service.MyContainer
import com.example.vp_alpapp.viewmodel.CreateContentViewModel
import com.example.vp_alpapp.viewmodel.DetailKontenViewModel


@Composable
fun TestView (
    context: Context,
    dataStore: DataStore,
    loginViewModel: LoginViewModel,
    homeViewModel: HomeViewModel,
    navController: NavController

) {

    val detailKontenViewModel: DetailKontenViewModel = viewModel()

    Row {
        Button(onClick = {        loginViewModel.login(context = context, dataStore = dataStore, email = "admin@example.com", password = "123", navController = navController)
        }) {

        }
        BottomNavigationBar(navController = navController)


    }

//
//    LazyColumn(Modifier.fillMaxSize()) {
//
//        items(homeViewModel.data) {
//
//            Text(text = it.headline)
//        }
//    }
   
    

}

@Composable
fun GetAllContent(
    listKonten: List<Content>
) {

    LazyColumn(Modifier.fillMaxSize()) {

        items(listKonten) {

            Text(text = it.headline)
        }
    }
}

@Composable
fun testLogout() {

    val homeViewModel: HomeViewModel = viewModel()
    val detailKontenViewModel: DetailKontenViewModel = viewModel()

    LaunchedEffect(key1 = true) {

        detailKontenViewModel.delete("2")

    }

}

@Composable
fun buatKonten(

    createContentViewModel: CreateContentViewModel

) {

//    createContentViewModel.createContent("HEADLINE","IMAGE","CONTENT TEXT",1)

}


@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun TestPreview() {
    val context = LocalContext.current
    val dataStore = DataStore(context)
    val loginViewModel: LoginViewModel = viewModel()
    val homeViewModel: HomeViewModel = viewModel()

//    TestView(context = context, dataStore = dataStore, loginViewModel = loginViewModel, homeViewModel = homeViewModel)

//     val homeViewModel :HomeViewModel = viewModel()
//
//    val status = homeViewModel.homeUIState
//
//    when(status){
//        is HomeUIState.Loading -> {}
//        is HomeUIState.Success -> {
//                GetAllContent(listKonten = status.data)
//            }
//
//        is HomeUIState.Error ->{
//
//            Log.d("ERRROR","ERROORR")
//        }
//    }

//val createContentViewModel: CreateContentViewModel= viewModel()
//
//    buatKonten(createContentViewModel = createContentViewModel)
//
//    testLogout()
//


}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun TestPreview2() {

    Routes()
}
