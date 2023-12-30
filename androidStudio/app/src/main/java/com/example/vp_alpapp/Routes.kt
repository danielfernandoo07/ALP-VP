package com.example.vp_alpapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vp_alpapp.service.MyContainer
import com.example.vp_alpapp.view.AddPostView
import com.example.vp_alpapp.view.DetailKontenView
import com.example.vp_alpapp.view.ExploreView
import com.example.vp_alpapp.view.LoginView
import com.example.vp_alpapp.view.ProfileView
import com.example.vp_alpapp.view.RegisterView
import com.example.vp_alpapp.viewmodel.CreateContentViewModel
import com.example.vp_alpapp.viewmodel.DetailKontenViewModel
import com.example.vp_alpapp.viewmodel.KontenDetailUiState
import com.example.vp_alpapp.viewmodel.LoginViewModel
import com.example.vp_alpapp.viewmodel.ProfileUiState
import com.example.vp_alpapp.viewmodel.ProfileViewModel
import com.example.vp_alpapp.viewmodel.RegisterViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


enum class ListScreen() {

    Home,
    Login,
    DetailKonten,
    CreatePost,
    Profile,
    Explore,
    Register
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Routes() {

    val navController = rememberNavController()
    val dataStore = DataStore(LocalContext.current)




    GlobalScope.launch {
        dataStore.getToken.collect{
            if (it != null) {
                MyContainer.ACCESS_TOKEN = it
                MyContainer.user = MyContainer().myRepos.getUser(MyContainer.ACCESS_TOKEN)
            }
        }
    }

    Scaffold { it ->

        NavHost(
            navController = navController,
            startDestination = ListScreen.Login.name,
            modifier = Modifier.padding(it)

        ) {

            composable(ListScreen.Login.name) {


                if (MyContainer.ACCESS_TOKEN.isEmpty()) {
                    val loginViewModel: LoginViewModel = viewModel()

                    LoginView(loginViewModel = loginViewModel, dataStore = dataStore, context = LocalContext.current, navController = navController, "" )

                }
                else {

                    navController.navigate(ListScreen.Profile.name)

                }
            }

            composable(ListScreen.Login.name+"/{email}") {


                if (MyContainer.ACCESS_TOKEN.isEmpty()) {
                    val loginViewModel: LoginViewModel = viewModel()

                    it.arguments?.let { it1 -> LoginView(loginViewModel = loginViewModel, dataStore = dataStore, context = LocalContext.current, navController = navController, it1.getString("email", "") ) }

                }
                else {

                    navController.navigate(ListScreen.Profile.name)

                }
            }

            composable(ListScreen.DetailKonten.name) {


                val detailKontenViewModel: DetailKontenViewModel = viewModel()

                //test untuk konten id 9
                LaunchedEffect(key1 = true) {
                    detailKontenViewModel.getById("9")
                }

                val status = detailKontenViewModel.kontenDetailUiState

                when(status){
                    is KontenDetailUiState.Loading -> {
                        Log.d("LOADING", "LOADING KONTEN")
                    }
                    is KontenDetailUiState.Success -> {

                        DetailKontenView(content = status.data)
                    }


                    is KontenDetailUiState.Error ->{

                    }
                }

            }

            composable(ListScreen.Profile.name) {

                val profileViewModel: ProfileViewModel = viewModel()

                val status = profileViewModel.profileUiState

                when (status) {
                    is ProfileUiState.Loading -> {
                        Log.d("LOADING", "LOADING KONTEN")
                    }

                    is ProfileUiState.Success -> {

                        ProfileView(navController, status.data, profileViewModel)

                    }


                    is ProfileUiState.Error -> {

                    }

                }
            }

            composable(ListScreen.Explore.name) {

                ExploreView(navController = navController)



            }

            composable(ListScreen.CreatePost.name) {

                val createContentViewModel:CreateContentViewModel = viewModel()
                AddPostView(createContent = createContentViewModel, navController = navController)

            }

            composable(ListScreen.Register.name) {

                val registerViewModel: RegisterViewModel = viewModel()

                RegisterView(navController = navController, registerViewModel = registerViewModel)

            }
        }

    }
}