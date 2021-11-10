package com.marvel.talentomobile.app.ui.detail

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marvel.talentomobile.app.data.model.MarvelCharacter
import com.marvel.talentomobile.app.data.remote.RemoteDataSource
import com.marvel.talentomobile.app.data.remote.StatusRequest
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchCharactersPagingSource @Inject constructor(
    private val remoteDataSource: RemoteDataSource) : PagingSource<Int, MarvelCharacter>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarvelCharacter> {
        val position = params.key ?: 1
        val offset = if (params.key != null) ((position - 1) * 1) + 1 else 0
        return try {
            val remoteData = remoteDataSource.characters(params.loadSize, offset)
            if(remoteData.status == StatusRequest.Status.SUCCESS){
                val nextKey = if (remoteData.data?.data?.results?.isEmpty()!!) {
                    null
                } else {
                    offset + params.loadSize
                }
                LoadResult.Page(
                    data = remoteData.data.data.results,
                    prevKey = null,
                    nextKey = nextKey
                )
            }else{
                LoadResult.Error(IOException(remoteData.message))
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MarvelCharacter>): Int? {
        return null
    }
}