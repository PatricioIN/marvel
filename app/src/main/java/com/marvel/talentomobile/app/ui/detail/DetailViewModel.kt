package com.marvel.talentomobile.app.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.marvel.talentomobile.app.data.model.MarvelCharacter
import com.marvel.talentomobile.app.data.remote.RemoteDataSource
import com.marvel.talentomobile.app.data.remote.StatusRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ViewModel() {

    var marvelCharacterDetail = MutableLiveData<List<MarvelCharacter>>()

    fun getMarvelCharacter(marvelCharacterID: Int) = viewModelScope.launch {
        val remoteData = remoteDataSource.character(marvelCharacterID)
        when (remoteData.status) {
            StatusRequest.Status.SUCCESS -> {
                println(remoteData.data)
                marvelCharacterDetail.value = remoteData.data?.data?.results
            }
            StatusRequest.Status.ERROR -> println("Call error")
            StatusRequest.Status.LOADING -> println("Loading data")
        }
    }
}