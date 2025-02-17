package com.example.newsapps_samplesyncrony.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapps_samplesyncrony.R

@Composable
fun ImageViewWithCoil(imageUrl:String){
Column {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true).
            placeholder(R.drawable.place_holder_image).
            error(R.drawable.error_image)
            .build(),
        contentDescription = null,
        modifier = Modifier.size(100.dp),
        contentScale = ContentScale.Crop
    )
}
}