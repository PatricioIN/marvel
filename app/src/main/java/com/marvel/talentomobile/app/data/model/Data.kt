package com.marvel.talentomobile.app.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<MarvelCharacter>,
    val series: List<Series>,
    val total: Int
): Parcelable