package com.example.vp_alpapp.landy

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vp_alpapp.R
import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.model.Pengguna
import com.example.vp_alpapp.view.Post
import com.example.vp_alpapp.viewmodel.ExploreViewModel
import com.example.vp_alpapp.viewmodel.ProfileViewModel

@Composable
fun AddFriendView(
    profileViewModel: ProfileViewModel = viewModel(),
    navController : NavController,
    addFriendViewModel: AddFriendViewModel = viewModel(),
    exploreViewModel: ExploreViewModel = viewModel(),
    user: Pengguna,
    listku: List<Content>?,
) {
    val addFriendUIState by addFriendViewModel.uistate.collectAsState()

    var isClicked by rememberSaveable {
        mutableStateOf(false)
    }
    var follOrUnfoll by rememberSaveable {
        mutableStateOf(false)
    }
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

                Image(
                    painter = painterResource(id = R.drawable.ellipse_5),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

                Column() {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                    ) {

                        // Image with onClick listener to show the popup menu
                        Image(
                            painter = painterResource(id = R.drawable.more),
                            contentDescription = "",
                            modifier = Modifier
                                .size(50.dp)
                                .padding(top = 16.dp)
                                .clickable { isClicked = !isClicked }
                        )

                        // PopupMenu triggered by the click on the Image
                        DropdownMenu(
                            expanded = isClicked,
                            onDismissRequest = { isClicked != isClicked },
                            modifier = Modifier.background(Color.Gray),
                            offset = DpOffset((-8).dp, 0.dp)
                        ) {
                            // Menu item for logout
                            DropdownMenuItem(
                                text = { Text(text = "Logout") },
                                onClick = {
                                    isClicked = false
                                    profileViewModel.logout(navController)
                                }
                            )
                        }
                    }
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(26.dp)
                                .align(Alignment.BottomCenter)
                        ) {
                            Text(
                                text = "Gerald Gavin Lienardi",
                                fontSize = 18.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                                fontSize = 14.sp,
                                color = Color.White,
                            )
                        }
                    }
                }

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .clickable {
                            follOrUnfoll = !follOrUnfoll
                            addFriendViewModel.isClicked(follOrUnfoll)
                        }
                        .width(170.dp)
                        .height(40.dp)
                        .offset((0).dp, (-22).dp)
                        .background(color = Color(0XFFF89715), shape = RoundedCornerShape(12.dp))) {
                    Text(
                        text = if (!addFriendUIState.isFollow) { "Follow" } else { "Unfollow" },
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    val context = LocalContext.current
                    if (follOrUnfoll) {
                        Toast.makeText(context, "Follow berhasil!", Toast.LENGTH_LONG)
                    }
                    else {
                        Toast.makeText(context, "Unfollow berhasil!", Toast.LENGTH_LONG)
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 46.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "1500",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Posts",
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "328",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Followers",
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "5993",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Following",
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                }
            }

            Divider(color = Color.Black, thickness = 1.dp, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.grid),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.padding(end = 18.dp))
                Image(
                    painter = painterResource(id = R.drawable.film),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.padding(end = 18.dp))
                Image(
                    painter = painterResource(id = R.drawable.tag),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )

            }
        }
        if (listku != null) {
            val reversedList = listku.reversed()
            items(reversedList.size) { index ->
                Spacer(modifier = Modifier.height(20.dp))
                Post(
                    content = reversedList[index],
                    user = user,
                    exploreViewModel = exploreViewModel,
                    navController = navController
                )
            }
        }
        if (listku != null) {
            if (listku.isEmpty()) {
                item {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    )
                    Text(
                        text = "No Posts Yet!",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp) // Adjust the padding as needed
                    )
                }
            }
        }
        item { Spacer(modifier = Modifier.height(20.dp)) }

    }
}
