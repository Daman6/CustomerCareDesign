package com.example.customercaredesign

import androidx.compose.ui.graphics.vector.ImageVector

data class Model(
    val type: String,
    val title: String,
    val subTitle: String,
    val icon: ImageVector,
)

data class BottomCardModel(
    val title: String,
)

