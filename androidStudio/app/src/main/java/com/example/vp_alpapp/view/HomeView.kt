package com.example.vp_alpapp.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vp_alpapp.BottomNavigationBar
import com.example.vp_alpapp.R
import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.model.Pengguna
import com.example.vp_alpapp.viewmodel.HomeViewModel
import com.example.vp_alpapp.viewmodel.ProfileViewModel
import java.text.NumberFormat
import java.util.Locale
import kotlin.random.Random

@Composable
fun Tab(text: String, isSelected: Boolean) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) Color(0xFFF89715) else Color.Transparent)
            .padding(2.dp)
            .padding(if (isSelected) 1.dp else 0.dp)
            .width(120.dp)
            .height(36.dp)
    ) {
        BasicText(
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                color = if (isSelected) Color.White else Color(0xFFA7A7A7),
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun FilterMenu() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.End
    ) {
        item {
            Row(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color(0xFFE9E9E9))
                    .padding(8.dp)
            ) {
                Tab("News", true)
                Tab("Committees", false)
                Tab("Following", false)
            }
        }
    }
}

@Composable
fun Post(

    content: Content
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
                    Image(
                        painter = painterResource(id = R.drawable.profilepic),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = content.user.name,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Three Dot Menu
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu",
                    modifier = Modifier.clickable { /* Handle menu click */ }
                )
            }

            // Post Title
            Text(
                text = content.headline,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Post Content
            Text(
                text = content.contentText,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
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
                    Image(
                        painter = painterResource(id = R.drawable.favorite),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.comment),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.share),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.saved),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun TopBar(
    homeViewModel: HomeViewModel,
    navController: NavController,
    user: Pengguna
) {
    var isLogoutVisible by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .padding(16.dp),
        verticalAlignment = Alignment.Top // Set verticalAlignment to Alignment.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.profilepic),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .width(40.dp)
                .clickable {
                    // Implement profile picture click action
                }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = user.name ,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight(800),
                color = Color(0xFF000000),
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(id = R.drawable.arrowdown),
            contentDescription = "Arrow Down",
            modifier = Modifier
                .width(24.dp)
                .clickable {
                    // Toggle the visibility of the logout button
                    isLogoutVisible = !isLogoutVisible
                }
        )
    }

    // Show the logout button when isLogoutVisible is true
    if (isLogoutVisible) {
        Button(
            onClick = {
                // Implement logout button click action
                      homeViewModel.logout(navController = navController)

            },
            modifier = Modifier.padding(8.dp),
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text("Logout")
        }
    }
}

@Composable
fun Home(
    navController: NavController,
    homeViewModel: HomeViewModel,
    user: Pengguna
) {
    Column(
        modifier = Modifier
            .background(Color(0xFFF3F3F3))
    ) {
        TopBar(homeViewModel, navController = navController, user)
        FilterMenu()
        Spacer(modifier = Modifier.height(8.dp))
//        Post()
//
//        BoxWithConstraints {
//            val bottomNavHeight = 45.dp
//            val gridHeight = maxHeight - bottomNavHeight
//            LazyVerticalGrid(
//                columns = GridCells.Fixed(1),
//                modifier = Modifier
//                    .padding(bottom = 0.dp)
//                    .background(Color.Black)
//                    .height(gridHeight)
//            ) {
//                item(
//                    span = { GridItemSpan(1) }
//                ) {
//                    TopBar()
//                }
//                item(
//                    span = { GridItemSpan(1) }
//                ) {
//                    FilterMenu()
//                    Spacer(modifier = Modifier.height(8.dp))
//                }
//                item(
//                    span = { GridItemSpan(1) }
//                ) {
//                    Post()
//                }
//            }
//        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun HomeView() {
//    Home()
}