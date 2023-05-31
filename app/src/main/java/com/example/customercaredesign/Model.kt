package com.example.customercaredesign

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

data class Model(
    val type: String,
    val title: String,
    val subTitle: String,
    @DrawableRes
    val icon: Int,
)

data class BottomCardModel(
    val title: String,
)

