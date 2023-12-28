package com.example.vp_alpapp.view

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vp_alpapp.model.Content
import com.example.vp_alpapp.viewmodel.DetailKontenViewModel
import com.example.vp_alpapp.viewmodel.KontenDetailUiState


@Composable
fun DetailKontenView(

    content: Content
) {

    //desain di sini content.headline content.image dll

    Text(text = content.contentText )


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailKontenPreview() {

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