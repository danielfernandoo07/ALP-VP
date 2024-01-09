package com.example.vp_alpapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vp_alpapp.ListScreen
import com.example.vp_alpapp.R
import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.model.Pengguna
import com.example.vp_alpapp.service.MyContainer
import com.example.vp_alpapp.viewmodel.ExploreViewModel
import com.example.vp_alpapp.viewmodel.ProfileViewModel

@Composable
fun Profile2(
    navController: NavController,
    user: Pengguna,
    curUser: Pengguna,
    listku: List<Content>?,
    exploreViewModel: ExploreViewModel,
//    profileViewModel: ProfileViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F3F3))
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .background(Color.Gray)
            ) {
                var gambaruser =
                    "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png"

                if (user.photo == null) {
                    LoadImage(url = gambaruser)
                } else {
                    gambaruser = user.photo.toString()
                    LoadImage(url = gambaruser)
                }

                Column() {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        var showMenu by remember { mutableStateOf(false) }

                        // Image with onClick listener to show the popup menu
                        Image(
                            painter = painterResource(id = R.drawable.more),
                            contentDescription = "",
                            modifier = Modifier
                                .size(50.dp)
                                .padding(top = 16.dp)
                                .clickable { showMenu = true }
                        )


                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false },
                            modifier = Modifier.background(Color.Gray),
                            offset = DpOffset((-8).dp, 0.dp) // Adjust the offset as needed
                        ) {
                            // Menu item for logout
                            DropdownMenuItem(
                                text = { Text(text = "Chat") },
                                onClick = {
                                    showMenu = false

                                    navController.navigate(ListScreen.Chat.name+"/"+user.id.toString())
                                }
                            )
                        }


                    }
                    Spacer(modifier = Modifier.height(120.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column() {
                            Text(
                                text = user.name,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            var teks = "Your Bio"

                            if (user.bio == null) {
                                teks =
                                    "Share a bit about yourself. Let others know who you are, what you're passionate about. Highlight your interests, experiences, and anything else that you think defines you. This is your space to express yourself."
                            } else {
                                teks = user.bio
                            }
                            Text(
                                text = teks,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

//        item {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 100.dp)
//                    .background(Color(0xFFF89715), RoundedCornerShape(16.dp))
//                    .border(1.dp, Color(0xFFF89715), RoundedCornerShape(16.dp))
//                    .padding(12.dp)
//                    .clip(RoundedCornerShape(20.dp))
//            ) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 12.dp)
//                        .clickable {
//                            navController.navigate(ListScreen.EditProfile.name)
//                        },
//                    horizontalArrangement = Arrangement.Center,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = "Edit Profile",
//                        color = Color.White,
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold,
//                    )
//                    Image(
//                        painter = painterResource(id = R.drawable.arrdownwhite),
//                        contentDescription = "",
//                        modifier = Modifier
//                            .size(20.dp)
//                    )
//                }
//            }
//        }
        // FOR LOOP HERE
        if (listku != null) {
            val reversedList = listku.reversed()
            items(reversedList.size) { index ->
                Spacer(modifier = Modifier.height(20.dp))
                Post2(
                    content = reversedList[index],
                    user = user,
                    curUser = curUser,
                    exploreViewModel = exploreViewModel,
                    navController = navController
                )
            }
        }
        item { Spacer(modifier = Modifier.height(20.dp)) }

    }
}

@Composable
fun Post2(
    curUser: Pengguna,
    user: Pengguna,
    content: Content,
    exploreViewModel: ExploreViewModel,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp) // Use padding for left and right margins
            .clip(RoundedCornerShape(16.dp)) // Adjust the corner radius as needed
            .background(Color(0xFFFBFBFB))
            .padding(vertical = 4.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp) // Use padding for left and right margins
                .padding(vertical = 4.dp),
        ) {
            // Top Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Profile Picture and Name
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    var gambaruser =
                        "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png"

                    if (user.photo == null) {
                        LoadProfileImage(url = gambaruser, navController, content, user)
                    } else {
                        gambaruser = content.user.photo
                        LoadProfileImage(url = gambaruser, navController, content, user)
                    }
                    Text(
                        text = content.user.name,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,

                        )
                }

                // Three Dot Menu
                if (user != null && curUser.id == content.user.id) {

                    var showDialog by remember { mutableStateOf(false) }

                    IconButton(
                        onClick = {
                            showDialog = true
                        },
                        modifier = Modifier
                            .size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.Black
                        )
                    }

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = {
                                // Handle dialog dismissal if needed
                                showDialog = false
                            },
                            title = {
                                Text(text = "Delete Post")
                            },
                            text = {
                                Text(text = "Are you sure you want to delete this post?")
                            },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        exploreViewModel.delete(content.id.toString())
                                        showDialog = false
                                        navController.navigate(ListScreen.Profile.name)
                                    }
                                ) {
                                    Text(text = "Yes")
                                }
                            },
                            dismissButton = {
                                Button(
                                    onClick = {
                                        showDialog = false
                                    }
                                ) {
                                    Text(text = "No")
                                }
                            }
                        )
                    }

                }

            }

            // Post Title
            Text(
                text = content.headline,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .clickable {

                        navController.navigate(ListScreen.CommentView.name + "/" + content.id.toString())

                    }
            )

            if (content.image == null) {

            } else {
                LoadImageCustom(
                    url = content.image, modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(ListScreen.CommentView.name + "/" + content.id.toString())
                        }
                        .heightIn(max = 170.dp), contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(10.dp))


            // Post Content
            Text(
                text = content.contentText,
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .clickable {
                        navController.navigate(ListScreen.CommentView.name + "/" + content.id.toString())
                    }
            )

            // Bottom Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Like, Comment, Share
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.favorite),
//                        contentDescription = null,
//                        modifier = Modifier.size(24.dp)
//                    )
//                    Spacer(modifier = Modifier.width(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.comment),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {

                                navController.navigate(ListScreen.CommentView.name + "/" + content.id.toString())

                            }
                    )
//                    Spacer(modifier = Modifier.width(16.dp))
//                    Image(
//                        painter = painterResource(id = R.drawable.share),
//                        contentDescription = null,
//                        modifier = Modifier.size(24.dp)
//                    )
                }

                if (user != null && curUser.id == content.user.id) {


                    IconButton(
                        onClick = {

                            navController.navigate(ListScreen.EditKonten.name + "/" + content.id.toString())

                        },
                        modifier = Modifier.size(24.dp)

                    ) {

                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "edit",
                            tint = Color.Black
                        )
                    }
                }
            }


        }
    }
}

