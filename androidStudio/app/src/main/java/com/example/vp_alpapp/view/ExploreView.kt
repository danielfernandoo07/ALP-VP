package com.example.vp_alpapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vp_alpapp.R
import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.model.Pengguna
import com.example.vp_alpapp.viewmodel.ExploreViewModel

@Composable
fun ExploreView(

    listData: List<Content>?,
    user: Pengguna?,
    exploreViewModel: ExploreViewModel,
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFF3F3F3))
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Explore",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(id = R.drawable.explore),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

//        Spacer(modifier = Modifier.height(10.dp))
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 12.dp),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.logosc),
//                contentDescription = "",
//                modifier = Modifier
//                    .size(100.dp)
//            )
//            Spacer(modifier = Modifier.width(20.dp))
//            Image(
//                painter = painterResource(id = R.drawable.uc),
//                contentDescription = "",
//                modifier = Modifier
//                    .size(100.dp)
//            )
//            Spacer(modifier = Modifier.width(20.dp))
//            Image(
//                painter = painterResource(id = R.drawable.srb),
//                contentDescription = "",
//                modifier = Modifier
//                    .size(100.dp)
//            )
//        }
//        Spacer(modifier = Modifier.height(10.dp))
//        Divider(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(20.dp)
//        )

//        Spacer(modifier = Modifier.height(20.dp))
//        repeat(5) { index ->
//            Post()
//            Spacer(modifier = Modifier.height(8.dp))
//        }

        if (listData != null) {
            repeat(listData.size) {

                if (user != null) {
                    if (user.id != listData[it].userId) {
                        Post(
                            content = listData[it],
                            user = user,
                            exploreViewModel = exploreViewModel,
                            navController = navController
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

            }
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ExplorePreview() {
//    ExploreView()
}
