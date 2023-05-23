package com.example.myapplication.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.data.model.CharacterFromRickAndMorty
import com.example.myapplication.data.network.ApiClient

class CharacterPagingSource {

    fun getCharacters(): PagingSource<Int, CharacterFromRickAndMorty> {
        return object : PagingSource<Int, CharacterFromRickAndMorty>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterFromRickAndMorty> {
                val pageNumber = params.key ?: FIRST_PAGE

                val charactersResponse = ApiClient.getApiClient().loadList(pageNumber)
                val characters = charactersResponse.results

                val prevKey = if (pageNumber > FIRST_PAGE) pageNumber - FIRST_PAGE else null
                val nextKey = pageNumber + FIRST_PAGE

                return LoadResult.Page(
                    data = characters,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }

            override fun getRefreshKey(state: PagingState<Int, CharacterFromRickAndMorty>): Int = FIRST_PAGE
        }

    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}
