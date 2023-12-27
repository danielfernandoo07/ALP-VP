package com.example.vp_alpapp.view

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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


@Composable
fun TestView (
    context: Context,
    dataStore: DataStore,
    loginViewModel: LoginViewModel

) {

    LaunchedEffect(key1 = true ) {

        loginViewModel.login(context = context, dataStore = dataStore)



    }

}

@Composable
fun GetAllContent(
    listKonten: List<Content>
) {

    LaunchedEffect(key1 = true) {
        for (kontent in listKonten) {
            Log.d("Output : ", kontent.headline)
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun TestPreview() {
//    val context = LocalContext.current
//    val dataStore = DataStore(context)
//    val loginViewModel = LoginViewModel()
//
//    TestView(context = context, dataStore = dataStore, loginViewModel = loginViewModel)

     val homeViewModel :HomeViewModel = viewModel()
    
    val status = homeViewModel.homeUIState

    when(status){
        is HomeUIState.Loading -> {}
        is HomeUIState.Success -> {
                GetAllContent(listKonten = status.data)
            }
        
        is HomeUIState.Error ->{

            Log.d("ERRROR","ERROORR")
        }
    }






}