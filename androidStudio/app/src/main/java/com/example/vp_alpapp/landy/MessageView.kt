package com.example.vp_alpapp.landy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vp_alpapp.R
import com.example.vp_alpapp.model.Pengguna

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    messageViewModel: MessageViewModel = viewModel(),
    lawanBicara: Pengguna
) {
    val messageUIState by messageViewModel.uistate.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
                .background(Color(0XFFF89715)),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.padding(start = 16.dp))

            Image(
                painter = painterResource(id = R.drawable.profilepic),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.padding(end = 16.dp))

            Text(
                text = lawanBicara.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        ChatRow(messageUIState)
        BottomTextField(messageViewModel)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomTextField(
    messageViewModel: MessageViewModel
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        TextField(
            value = text,
            onValueChange = { text = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray,
                textColor = Color.Black
            ),
            placeholder = {
                Text(
                    text = "Send a message...", fontSize = 12.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .align(Alignment.BottomCenter),
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.dms),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { messageViewModel.createChat(text) }
                )
            }
        )
    }
}

@Composable
fun ChatRow(
    message: List<Message>
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .background(shape = RoundedCornerShape(18.dp), color = Color.LightGray)
            ) {

                Spacer(modifier = Modifier.padding(start = 18.dp))
                Text(
                    text = "Hello whatsup",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
                Spacer(modifier = Modifier.padding(end = 18.dp, bottom = 18.dp))
            }
            Spacer(modifier = Modifier.padding(bottom = 14.dp))
        }
        items(message) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .background(shape = RoundedCornerShape(18.dp), color = Color(0XFFF89715)),
                ) {

                    Spacer(modifier = Modifier.padding(start = 18.dp))
                    Text(
                        text = "${it.message}",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                    Spacer(modifier = Modifier.padding(end = 18.dp, bottom = 18.dp))
                }
            }

            Spacer(modifier = Modifier.padding(bottom = 14.dp))

        }

    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun ChatScreenPreview() {
//    return ChatScreen()
}