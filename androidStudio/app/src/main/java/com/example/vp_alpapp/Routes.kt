package com.example.vp_alpapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vp_alpapp.model.Pengguna
import com.example.vp_alpapp.service.MyContainer
import com.example.vp_alpapp.view.AddPostView
import com.example.vp_alpapp.view.DetailKontenView
import com.example.vp_alpapp.view.EditContentView
import com.example.vp_alpapp.view.EditProfileView
import com.example.vp_alpapp.view.EditProfileView1
import com.example.vp_alpapp.view.ExploreView
import com.example.vp_alpapp.view.Home
import com.example.vp_alpapp.view.LoginUIView
import com.example.vp_alpapp.view.Profile
//import com.example.vp_alpapp.view.ProfileView
import com.example.vp_alpapp.view.RegisterView
import com.example.vp_alpapp.viewmodel.CreateContentViewModel
import com.example.vp_alpapp.viewmodel.DetailKontenViewModel
import com.example.vp_alpapp.viewmodel.EditContentViewModel
import com.example.vp_alpapp.viewmodel.EditProfileViewModel
import com.example.vp_alpapp.viewmodel.ExploreViewModel
import com.example.vp_alpapp.viewmodel.HomeViewModel
import com.example.vp_alpapp.viewmodel.KontenDetailUiState
import com.example.vp_alpapp.viewmodel.LoginViewModel
import com.example.vp_alpapp.viewmodel.ProfileUiState
import com.example.vp_alpapp.viewmodel.ProfileViewModel
import com.example.vp_alpapp.viewmodel.RegisterViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


enum class ListScreen() {

    Home,
    Login,
    DetailKonten,
    CreatePost,
    Profile,
    Explore,
    Register,
    EditProfile,
    EditKonten
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun Routes() {

    val navController = rememberNavController()
    val dataStore = DataStore(LocalContext.current)


    val tokenState by dataStore.getToken.collectAsState(initial = null)


    GlobalScope.launch {
        dataStore.getToken.collect{
            if (it != null) {
                MyContainer.ACCESS_TOKEN = it
//                MyContainer.user = MyContainer().myRepos.getUser(MyContainer.ACCESS_TOKEN)
            }
        }
    }

    Scaffold (

        bottomBar = {
                BottomNavigationBar(navController = navController)

        }
    ){ it ->

        NavHost(
            navController = navController,
            startDestination = ListScreen.Login.name,
            modifier = Modifier.padding(it)

        ) {

            composable(ListScreen.Login.name) {


                if (MyContainer.ACCESS_TOKEN.isNullOrEmpty()) {
                    val loginViewModel: LoginViewModel = viewModel()

                    LoginUIView(loginViewModel = loginViewModel, dataStore = dataStore, context = LocalContext.current, navController = navController, "" )

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
                val exploreViewModel: ExploreViewModel = viewModel()
                val status = profileViewModel.profileUiState

                when (status) {
                    is ProfileUiState.Loading -> {
                        Profile(navController, Pengguna("","","","","",0,"","","",0,""),null, exploreViewModel, profileViewModel)

                    }

                    is ProfileUiState.Success -> {


                        Profile(navController, status.data, status.data1, exploreViewModel, profileViewModel)




                    }


                    is ProfileUiState.Error -> {

                    }

                }
            }



            composable(ListScreen.Explore.name) {


                val exploreViewModel: ExploreViewModel = viewModel()



                when (val status = exploreViewModel.exploreUiState) {
                    is ExploreViewModel.ExploreUiState.Loading -> {

                        ExploreView(listData = null, null, exploreViewModel, navController)

                    }

                    is ExploreViewModel.ExploreUiState.Success -> {


                        
                        ExploreView(listData = status.data, status.datauser, exploreViewModel, navController)




                    }


                    is ExploreViewModel.ExploreUiState.Error -> {



                    }

                }




            }

            composable(ListScreen.CreatePost.name) {

                val createContentViewModel:CreateContentViewModel = viewModel()



                AddPostView(createContent = createContentViewModel, context = LocalContext.current, navController)


            }

            composable(ListScreen.Register.name) {

                val registerViewModel: RegisterViewModel = viewModel()

                RegisterView(navController = navController, registerViewModel = registerViewModel)
            }

            composable(ListScreen.Home.name) {


                val homeViewModel: HomeViewModel = viewModel()

                val profileViewModel: ProfileViewModel = viewModel()

                val status = profileViewModel.profileUiState

                when (status) {
                    is ProfileUiState.Loading -> {

                        Home(navController, homeViewModel = homeViewModel, Pengguna("","","","","",0,"","","",0,""), dataStore)

                    }

                    is ProfileUiState.Success -> {


                        Home(navController, homeViewModel = homeViewModel, user = status.data, dataStore = dataStore)





                    }


                    is ProfileUiState.Error -> {

                    }

                }


            }

            composable(ListScreen.EditProfile.name) {

                val profileViewModel: ProfileViewModel = viewModel()

                val editProfileViewModel: EditProfileViewModel = viewModel()

                val status = profileViewModel.profileUiState

                when (status) {
                    is ProfileUiState.Loading -> {


                        EditProfileView1(navController = navController, editProfileViewModel = editProfileViewModel, user = null)

                    }

                    is ProfileUiState.Success -> {



                        EditProfileView(navController = navController, editProfileViewModel = editProfileViewModel, user = status.data, context = LocalContext.current )

                    }


                    is ProfileUiState.Error -> {

                    }

                }
            }


            composable(ListScreen.EditKonten.name+"/{id}") {

                val editContentViewModel: EditContentViewModel = viewModel()
                val detailKontenViewModel: DetailKontenViewModel = viewModel()


                it.arguments?.let { it1 ->  val kontenku = it1.getString("id", "")


                    LaunchedEffect(key1 = true) {
                        detailKontenViewModel.getById(kontenku)
                    }

                    val status = detailKontenViewModel.kontenDetailUiState

                    when(status){
                        is KontenDetailUiState.Loading -> {
                            Log.d("LOADING", "LOADING KONTEN")
                        }
                        is KontenDetailUiState.Success -> {




                            EditContentView(editContentViewModel = editContentViewModel, contentId = status.data, navController)


                        }


                        is KontenDetailUiState.Error ->{

                        }
                    }


                }









            }
            composable(ListScreen.Login.name+"/{email}") {


                if (MyContainer.ACCESS_TOKEN.isEmpty()) {
                    val loginViewModel: LoginViewModel = viewModel()

                    it.arguments?.let { it1 -> LoginUIView(loginViewModel = loginViewModel, dataStore = dataStore, context = LocalContext.current, navController = navController, it1.getString("email", "") ) }

                }
                else {


                    navController.navigate(ListScreen.Profile.name)

                }
            }


            }
        }

    }
