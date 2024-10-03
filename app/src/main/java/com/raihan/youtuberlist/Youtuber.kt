package com.raihan.youtuberlist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Youtuber(
    val name: String,
    val description: String,
    val photo: String,
    val born: Int,
    val subscriber: String,
    val genre: String
): Parcelable
