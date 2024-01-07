package com.example.vp_alpapp.landy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.vp_alpapp.R
import com.example.vp_alpapp.model.Commentku
import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.service.MyContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentPostView(
    commentPostViewModel: CommentPostViewModel = viewModel(),
    content: Content,
    semuaKomen: List<Commentku>,
    navController: NavController
) {

    var commentText by rememberSaveable {
        mutableStateOf("")
    }
    var isClicked by rememberSaveable {
        mutableStateOf(false)
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F3F3))
            .padding(horizontal = 12.dp, vertical = 12.dp),
    ) {

        item {

            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.padding(bottom = 14.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(32.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                ) {

                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(R.drawable.profilepic),
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(24.dp)

                            )

                            Spacer(modifier = Modifier.padding(end = 10.dp))

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = content.user.name,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.SemiBold
                                )

                            }

                        }
                        Spacer(modifier = Modifier.padding(bottom = 10.dp))

                        Text(
                            text = content.headline,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.padding(bottom = 10.dp))

                        Text(
                            text = content.contentText,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.padding(bottom = 12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = if (isClicked) {Icons.Outlined.Favorite} else { Icons.Outlined.FavoriteBorder},
                                contentDescription = null,
                                tint = if (isClicked) {Color.Red} else { Color.Black },
                                modifier = Modifier.size(20.dp).clickable{ isClicked = !isClicked }
                            )
                            Spacer(modifier = Modifier.padding(end = 14.dp))
                            Image(
                                painter = painterResource(id = R.drawable.share),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable { }

                            )

                            Spacer(modifier = Modifier.padding(end = 220.dp))

                            Image(
                                painter = painterResource(id = R.drawable.saved),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable { }
                            )
                        }
                    }

                }
            }

        }

        items(semuaKomen) {
            CommentCard(
                it
            )
        }

        item {
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            Divider(
                color = Color.DarkGray,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp)
            )

            Spacer(modifier = Modifier.padding(bottom = 6.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profilepic),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )

                TextField(value = commentText,
                    onValueChange = {
                        commentText = it
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        Color.Black,
                    ),
                    placeholder = {
                        Text(
                            text = "Write your comment...", fontSize = 12.sp
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.padding(bottom = 18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .clickable {
                            commentPostViewModel.create(content.id.toString(), commentText, navController)
                        }
                        .width(170.dp)
                        .height(40.dp)
                        .background(color = Color(0XFFF89715), shape = RoundedCornerShape(12.dp))) {
                    Text(
                        text = "Post",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun CommentCard(
    komen: Commentku,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(32.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profilepic),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(24.dp)

                )

                Spacer(modifier = Modifier.padding(end = 10.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = komen.user.name,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                }

            }
            Spacer(modifier = Modifier.padding(bottom = 10.dp))

            Text(
                text = komen.comment_text,
                fontSize = 12.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun CommentPostPreview() {
//    return CommentPostView()
}