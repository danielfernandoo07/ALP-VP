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
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vp_alpapp.BottomNavigationBar
import com.example.vp_alpapp.DataStore
import com.example.vp_alpapp.ListScreen
import com.example.vp_alpapp.R
import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.model.Pengguna
import com.example.vp_alpapp.service.MyContainer
import com.example.vp_alpapp.ui.theme.orangelight
import com.example.vp_alpapp.viewmodel.ExploreViewModel
import com.example.vp_alpapp.viewmodel.HomeViewModel
import com.example.vp_alpapp.viewmodel.ProfileViewModel
import java.text.NumberFormat
import java.util.Locale
import kotlin.random.Random

@Composable
fun FilterMenu() {
    var isNewsSelected by remember { mutableStateOf(true) }
    var isCommitteesSelected by remember { mutableStateOf(false) }

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
//                    .background(Color(0xFFE9E9E9))
                    .padding(8.dp)
            ) {
                Tab("News", R.drawable.news, isNewsSelected) {
                    isNewsSelected = true
                    isCommitteesSelected = false
                    // Add any logic you want when the "News" tab is clicked
                }
                Tab("Committees", R.drawable.comit, isCommitteesSelected) {
                    isNewsSelected = false
                    isCommitteesSelected = true
                    // Add any logic you want when the "Committees" tab is clicked
                }
            }
        }
    }
}

@Composable
fun RowScope.Tab(
    text: String,
    iconRes: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .padding(8.dp)
            .clickable { onClick() }
            .background(
                color = if (isSelected) orangelight else Color.Transparent,
                shape = MaterialTheme.shapes.medium
            )
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) Color.Black else Color.Gray,
                modifier = Modifier.clickable { onClick() }
            )
        }
    }
}


@Composable
fun Post(

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
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                // Three Dot Menu
                if (user != null && user.id == content.user.id) {


                    // Show delete button and make it clickable
                    IconButton(
                        onClick = {

                            exploreViewModel.delete(content.id.toString())

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
                }

            }

            // Post Title
            Text(
                text = content.headline,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            if (content.image == null) {

            }
            else {
                LoadImageCustom(
                    url = content.image, modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 170.dp), contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(10.dp))


            // Post Content
            Text(
                text = content.contentText,
                fontSize = 14.sp,
                color = Color.Black,
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
//                    Image(
//                        painter = painterResource(id = R.drawable.favorite),
//                        contentDescription = null,
//                        modifier = Modifier.size(24.dp)
//                    )
//                    Spacer(modifier = Modifier.width(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.comment),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
//                    Spacer(modifier = Modifier.width(16.dp))
//                    Image(
//                        painter = painterResource(id = R.drawable.share),
//                        contentDescription = null,
//                        modifier = Modifier.size(24.dp)
//                    )
                }

                if (user != null && user.id == content.user.id) {


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


@Composable
fun TopBar(
    homeViewModel: HomeViewModel,
    navController: NavController,
    user: Pengguna,
    dataStore: DataStore
) {
    var isLogoutVisible by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .padding(16.dp)
            .clickable{
                isLogoutVisible = !isLogoutVisible
            },
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
            text = user.name,
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
        )
    }

    // PopupMenu triggered by the click on the Image
    DropdownMenu(
        expanded = isLogoutVisible,
        onDismissRequest = { isLogoutVisible = false },
        modifier = Modifier.background(Color.Gray),
        offset = DpOffset((30).dp, (-100).dp) // Adjust the offset as needed
    ) {
        // Menu item for logout
        DropdownMenuItem(
            text = { Text(text = "Logout") },
            onClick = {
                isLogoutVisible = false
                homeViewModel.logout(navController = navController, dataStore)
                // Handle logout action here
            }
        )
    }
}

@Composable
fun Home(
    navController: NavController,
    homeViewModel: HomeViewModel,
    user: Pengguna,
    dataStore: DataStore
) {
    Column(
        modifier = Modifier
            .background(Color(0xFFF3F3F3))
    ) {
        TopBar(homeViewModel, navController = navController, user, dataStore = dataStore)
        FilterMenu()
        Spacer(modifier = Modifier.height(8.dp))
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun HomeView() {
//    Home()
}