package com.example.vp_alpapp.view

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.dataStore
import com.example.vp_alpapp.DataStore
import com.example.vp_alpapp.viewmodel.LoginViewModel

@Composable
fun TestView (
    context: Context,
    dataStore: DataStore,
    loginViewModel: LoginViewModel

) {

    LaunchedEffect(key1 = true ) {

        loginViewModel.testLoad(context = context, dataStore = dataStore)

    }

}
@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun TestPreview() {
    val context = LocalContext.current
    val dataStore = DataStore(context)
    val loginViewModel = LoginViewModel()

    TestView(context = context, dataStore = dataStore, loginViewModel = loginViewModel)
}