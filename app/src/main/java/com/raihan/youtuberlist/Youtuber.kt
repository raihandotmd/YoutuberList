package com.raihan.youtuberlist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Youtuber(
    val name: String,
    val summary: String,
    val photo: String,
    val born: Int,
    val subscriber: String,
    val genre: String,
    val description: String
): Parcelable
