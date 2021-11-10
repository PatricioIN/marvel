package com.marvel.talentomobile.app.data.remote

import com.marvel.talentomobile.app.api.IMarvelService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val marvelService: IMarvelService
): BaseDataSource() {
    suspend fun characters(limit: Int, offset: Int) = getResult {
        marvelService.getCharacters("name", limit, offset) }
    suspend fun character(characterID: Int) = getResult { marvelService.getCharacterDetail(characterID) }
}