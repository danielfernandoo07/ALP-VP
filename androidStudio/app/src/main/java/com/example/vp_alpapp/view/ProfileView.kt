package com.example.vp_alpapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vp_alpapp.R

@Composable
fun Profile() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFF3F3F3))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1000.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart)
                    .height(350.dp)
                    .background(Color.Blue) // GANTI IMAGE BACKGROUND
            ) {
                Column() {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.more),
                            contentDescription = "",
                            modifier = Modifier
                                .size(50.dp)
                                .padding(top = 16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(120.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column() {
                            Text(
                                text = "Username",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            Text(
                                text = "CAPTION E MEREKA Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
                                color = Color.White
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .height(680.dp)
                    .align(Alignment.BottomStart)
            ) {
                Column {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 100.dp)
                            .background(Color(0xFFF89715), RoundedCornerShape(16.dp))
                            .border(1.dp, Color(0xFFF89715), RoundedCornerShape(16.dp))
                            .padding(12.dp)
                            .clip(RoundedCornerShape(20.dp))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Edit",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Image(
                                painter = painterResource(id = R.drawable.arrdownwhite),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(20.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 30.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "1000", // POST AMMOUNT
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight(400),
                                )
                                Text(
                                    text = "Posts",
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color(0xFF7D7D7D),
                                    fontWeight = FontWeight(400),
                                )
                            }
                            Spacer(modifier = Modifier.width(60.dp))
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "1000", // FOllowers AMMOUNT
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight(400),
                                )
                                Text(
                                    text = "Followers",
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color(0xFF7D7D7D),
                                    fontWeight = FontWeight(400),
                                )
                            }
                            Spacer(modifier = Modifier.width(60.dp))
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "1000", // FOLLOWING AMMOUNT
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight(400),

                                    )
                                Text(
                                    text = "Following",
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color(0xFF7D7D7D),
                                    fontWeight = FontWeight(400),

                                    )
                            }
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 30.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.posts),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Image(
                                painter = painterResource(id = R.drawable.videos),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Image(
                                painter = painterResource(id = R.drawable.tagged),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(28.dp)
                            )
                        }
                    }

                    //FORLOOP ND SINI YA NGAB
                    Spacer(modifier = Modifier.height(20.dp))
                    Post()
                }
            }
        }
    }
}
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(820.dp)
//                    .align(Alignment.BottomStart)
//            ) {
//                Column(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 30.dp)
//                            .background(Color.White, RoundedCornerShape(16.dp))
//                            .border(1.dp, Color.DarkGray, RoundedCornerShape(16.dp))
//                            .padding(12.dp)
//                            .clip(RoundedCornerShape(20.dp))
//                    ) {
//                        Column(
//                            modifier = Modifier
//                                .width(100.dp)
//                                .background(Color(0xF89715))
//                                .padding(16.dp)
//                        ){
//                            Text(
//                                text = "Edit",
//                                fontSize = 20.sp,
//                                fontWeight = FontWeight.Bold,
//                                modifier = Modifier.padding(bottom = 10.dp)
//                            )
//                        }
//                        Column(
//                            modifier = Modifier
//                                .width(300.dp)
//                                .background(Color.White)
//                                .padding(16.dp)
//                        ) {
//                            Text(
//                                text = "Personal Data",
//                                fontSize = 20.sp,
//                                fontWeight = FontWeight.Bold,
//                                modifier = Modifier.padding(bottom = 10.dp)
//                            )
//                            Divider(modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(2.dp))
//
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(top = 10.dp),
//                                horizontalArrangement = Arrangement.SpaceBetween
//                            ) {
//                                Column(
//                                    modifier = Modifier.weight(1f)
//                                ) {
//                                    Text(
//                                        text = "NIDN",
//                                        fontWeight = FontWeight.SemiBold,
//                                        color = Color.Gray
//                                    )
//                                    Text(
//                                        text = "0123456789",
//                                        fontSize = 14.sp,
//                                        fontWeight = FontWeight.Bold,
//                                        color = Color.DarkGray,
//                                        modifier = Modifier.padding(bottom = 5.dp)
//                                    )
//                                }
//                                Column(
//                                    modifier = Modifier.weight(1f)
//                                ) {
//                                    Text(
//                                        text = "Date of Birth",
//                                        fontWeight = FontWeight.SemiBold,
//                                        color = Color.Gray
//                                    )
//                                    Text(
//                                        text = "17 Agustus 1945",
//                                        fontSize = 14.sp,
//                                        fontWeight = FontWeight.Bold,
//                                        color = Color.DarkGray,
//                                        modifier = Modifier.padding(bottom = 5.dp)
//                                    )
//                                }
//                            }
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(top = 10.dp),
//                                horizontalArrangement = Arrangement.SpaceBetween
//                            ) {
//                                Column(
//                                    modifier = Modifier.weight(1f)
//                                ) {
//                                    Text(
//                                        text = "Occupation",
//                                        fontWeight = FontWeight.SemiBold,
//                                        color = Color.Gray
//                                    )
//                                    Text(
//                                        text = "Lecturer",
//                                        fontSize = 14.sp,
//                                        fontWeight = FontWeight.Bold,
//                                        color = Color.DarkGray,
//                                        modifier = Modifier.padding(bottom = 5.dp)
//                                    )
//                                }
//                                Column(
//                                    modifier = Modifier.weight(1f)
//                                ) {
//                                    Text(
//                                        text = "Martial Status",
//                                        fontWeight = FontWeight.SemiBold,
//                                        color = Color.Gray
//                                    )
//                                    Text(
//                                        text = "Married",
//                                        fontSize = 14.sp,
//                                        fontWeight = FontWeight.Bold,
//                                        color = Color.DarkGray,
//                                        modifier = Modifier.padding(bottom = 5.dp)
//                                    )
//                                }
//                            }
//
//                            Text(
//                                text = "Email",
//                                fontWeight = FontWeight.SemiBold,
//                                color = Color.Gray
//                            )
//                            Text(
//                                text = "Turtule@Uc.co.id",
//                                fontSize = 13.sp,
//                                fontWeight = FontWeight.Bold,
//                                color = Color.Black
//                            )
//                        }
//                    Spacer(modifier = Modifier.height(20.dp))
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 30.dp)
//                            .background(Color.White, RoundedCornerShape(16.dp))
//                            .border(1.dp, Color.DarkGray, RoundedCornerShape(16.dp))
//                            .padding(12.dp)
//                            .clip(RoundedCornerShape(20.dp))
//                    ) {
//                        Column(
//                            modifier = Modifier
//                                .width(300.dp)
//                                .background(Color.White)
//                                .padding(16.dp)
//                        ) {
//                            Text(
//                                text = "Expertise",
//                                fontSize = 20.sp,
//                                fontWeight = FontWeight.Bold,
//                                modifier = Modifier.padding(bottom = 10.dp)
//                            )
//                            Divider(modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(2.dp))
//                            Spacer(modifier = Modifier.height(20.dp))
//                            Row(
//                                verticalAlignment = Alignment.CenterVertically
//                            ){
////                                Image(
////                                    painter = painterResource(id = R.drawable.hmmturtle),
////                                    contentDescription = "",
////                                    modifier = Modifier.size(35.dp,35.dp),
////                                    contentScale = ContentScale.Crop
//////                                        .padding(12.dp)
////                                )
//                                Text(
//                                    text = "Apple CEO",
//                                    modifier = Modifier.padding(12.dp),
//                                    fontWeight = FontWeight.Bold
//                                )
//                            }
//                            Spacer(modifier = Modifier.height(12.dp))
//                            Row(
//                                verticalAlignment = Alignment.CenterVertically
//                            ){
////                                Image(
////                                    painter = painterResource(id = R.drawable.yesturtle),
////                                    contentDescription = "",
////                                    modifier = Modifier.size(35.dp,35.dp),
////                                    contentScale = ContentScale.Crop
//////                                        .padding(12.dp)
////                                )
//                                Text(
//                                    text = "Gojek Developer",
//                                    modifier = Modifier.padding(12.dp),
//                                    fontWeight = FontWeight.Bold
//                                )
//                            }
//                        }
//                    }
//                    Spacer(modifier = Modifier.height(20.dp))
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 30.dp)
//                            .background(Color.White, RoundedCornerShape(16.dp))
//                            .border(1.dp, Color.DarkGray, RoundedCornerShape(16.dp))
//                            .padding(12.dp)
//                            .clip(RoundedCornerShape(20.dp))
//                    ) {
//                        Column(
//                            modifier = Modifier
//                                .width(300.dp)
//                                .background(Color.White)
//                                .padding(16.dp)
//                        ) {
//                            Text(
//                                text = "Send a Message",
//                                fontSize = 20.sp,
//                                fontWeight = FontWeight.Bold,
//                                modifier = Modifier.padding(bottom = 10.dp)
//                            )
//                            Divider(modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(2.dp))
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(top = 17.dp)
//                                    .border(
//                                        2.dp,
//                                        Color.LightGray,
//                                        shape = RoundedCornerShape(15.dp)
//                                    )
//                            ) {
//                                Text(
//                                    text = "Write a Message",
//                                    color = Color.Gray,
//                                    modifier = Modifier.padding(10.dp)
//                                )
//                            }
//                            Column(
//                                horizontalAlignment = Alignment.CenterHorizontally,
//                                modifier = Modifier
//                                    .padding(top = 10.dp)
//                                    .fillMaxWidth()
//                            ) {
//                                Text(
//                                    text = "Send",
//                                    fontSize = 18.sp,
//                                    modifier = Modifier
//                                        .background(
//                                            Color.Blue,
//                                            shape = RoundedCornerShape(30.dp)
//                                        )
//                                        .padding(vertical = 10.dp, horizontal = 40.dp),
//                                    color = Color.White,
//                                    textAlign = TextAlign.Center
//                                )
//                            }
//                        }
//                    }

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    Profile()
}