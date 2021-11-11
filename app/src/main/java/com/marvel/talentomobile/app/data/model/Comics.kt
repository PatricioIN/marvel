package com.marvel.talentomobile.app.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: ArrayList<Item> = arrayListOf(),
    val returned: Int
): Parcelable