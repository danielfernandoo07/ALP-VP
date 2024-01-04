package com.example.vp_alpapp.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vp_alpapp.R
import com.example.vp_alpapp.model.Pengguna
import com.example.vp_alpapp.viewmodel.EditProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileView(
    navController: NavController,
    editProfileViewModel: EditProfileViewModel,
    user: Pengguna
) {

    var bioku = ""
    if (user.bio.isNullOrEmpty()) {

        bioku = ""

    }
    else {
        bioku = user.bio
    }
    var password by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf(user.name) }
    var bio by rememberSaveable { mutableStateOf(bioku) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F3F3))
            .padding(30.dp)
    ) {
        // Similar UI structure as the registration screen
        // You can reuse the existing components and modify as needed

        Column (
            Modifier.verticalScroll(rememberScrollState())
        ){
            Text(
                text = "Edit Profile",
                style = TextStyle(
                    fontSize = 36.sp,
                    fontWeight = FontWeight(800),
                    color = Color(0xFF000000),
                )
            )
            Spacer(modifier = Modifier.height(50.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                var gambaruser = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png"

                if ( user.photo == null) {
                    LoadImageCustom(url = user.photo, contentScale = ContentScale.Crop, modifier = Modifier.size(120.dp).clip(
                        CircleShape))
                }

                else {

                    gambaruser = user.photo.toString()

                    LoadImageCustom(url = user.photo.toString(), contentScale = ContentScale.Crop, modifier = Modifier.size(120.dp).clip(
                        CircleShape))
                }





            }
            Spacer(modifier = Modifier.height(10.dp))


            // Email
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profilepic),
                    contentDescription = "Email Picture",
                    modifier = Modifier.size(30.dp)
                )
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text("Username") },
                    modifier = Modifier.padding(10.dp)
                    // ... (rest of the TextField configuration)
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Password (optional)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.key),
                    contentDescription = "Email Picture",
                    modifier = Modifier.size(30.dp)
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("New Password") },
                    modifier = Modifier.padding(10.dp)
                    // ... (rest of the TextField configuration)
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            // NIM

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.comment),
                    contentDescription = "Email Picture",
                    modifier = Modifier.size(30.dp)
                )
                TextField(
                    value = bio,
                    onValueChange = { bio = it },
                    placeholder = { Text("Bio") },
                    modifier = Modifier.padding(10.dp)
                    // ... (rest of the TextField configuration)
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            // PRODI ID
            // ...

            // Name
            // ...



            // Save Changes Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 15.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 50.dp)
                            .background(Color(0xFFF89715), RoundedCornerShape(8.dp))
                            .border(1.dp, Color(0xFFF89715), RoundedCornerShape(8.dp))
                            .padding(12.dp)
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        Text(
                            text = "Save Changes",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center,
                            ),
                            modifier = Modifier
                                .clickable {

                                    editProfileViewModel.editProfile(name,user.photo.toString(),password,bio)
                                }
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileView1(
    navController: NavController,
    editProfileViewModel: EditProfileViewModel,
    user: Pengguna?
) {
    var password by rememberSaveable { mutableStateOf("Old Password") }
    var name by rememberSaveable { mutableStateOf("user.name") }
    var bio by rememberSaveable { mutableStateOf("user.nim") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F3F3))
            .padding(30.dp)
    ) {
        // Similar UI structure as the registration screen
        // You can reuse the existing components and modify as needed

        Column {
            Text(
                text = "Edit Profile",
                style = TextStyle(
                    fontSize = 36.sp,
                    fontWeight = FontWeight(800),
                    color = Color(0xFF000000),
                )
            )
            Spacer(modifier = Modifier.height(50.dp))


            // Email
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profilepic),
                    contentDescription = "Email Picture",
                    modifier = Modifier.size(30.dp)
                )
                TextField(
                    value = "",
                    onValueChange = { name = it },
                    placeholder = { Text("Username") },
                    modifier = Modifier.padding(10.dp)
                    // ... (rest of the TextField configuration)
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Password (optional)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.key),
                    contentDescription = "Email Picture",
                    modifier = Modifier.size(30.dp)
                )
                TextField(
                    value = "",
                    onValueChange = { password = it },
                    placeholder = { Text("Password") },
                    modifier = Modifier.padding(10.dp)
                    // ... (rest of the TextField configuration)
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            // NIM
            // ...

            // PRODI ID
            // ...

            // Name
            // ...



            // Save Changes Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 15.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 50.dp)
                            .background(Color(0xFFF89715), RoundedCornerShape(8.dp))
                            .border(1.dp, Color(0xFFF89715), RoundedCornerShape(8.dp))
                            .padding(12.dp)
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        Text(
                            text = "Save Changes",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center,
                            ),
                            modifier = Modifier
                                .clickable {
                                    // Call the ViewModel function to update the profile
                                    // You may navigate back or perform other actions after updating
                                }
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EditPreview() {
    
//    EditProfileView()
}