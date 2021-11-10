package com.marvel.talentomobile.app.api.responseDTO

import android.os.Parcelable
import com.marvel.talentomobile.app.data.model.Data
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseDTO(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: Data,
    val etag: String,
    val status: String
) : Parcelable