package com.marvel.talentomobile.app.api

import com.marvel.talentomobile.app.api.responseDTO.ResponseDTO
import retrofit2.Response
import retrofit2.http.*

interface IMarvelService {
    @GET(ApiEndPoints.CHARACTERS_URL)
    suspend fun getCharacters(
        @Query("orderBy") orderBy: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<ResponseDTO>

    @GET(ApiEndPoints.CHARACTER_DETAIL_URL)
    suspend fun getCharacterDetail(
        @Path("characterID") characterID: Int
    ): Response<ResponseDTO>
}
