package com.marvel.talentomobile.app.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.marvel.talentomobile.app.data.model.MarvelCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private var currentOffsetValue: Int? = 0
    private var currentSearchResult: Flow<PagingData<MarvelCharacter>>? = null

    fun getMarvelCharacters(offset: Int): Flow<PagingData<MarvelCharacter>> {
        if (currentOffsetValue == offset && currentSearchResult != null) {
            return currentSearchResult!!
        }

        currentOffsetValue = offset
        val newResult: Flow<PagingData<MarvelCharacter>> = repository.getMarvelCharacters()
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}