package com.marvel.talentomobile.app.ui.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.marvel.talentomobile.app.data.model.MarvelCharacter
import com.marvel.talentomobile.app.data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource)
{
    fun getMarvelCharacters(): Flow<PagingData<MarvelCharacter>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchCharactersPagingSource(remoteDataSource) }
        ).flow
    }
}
