package com.example.myapplication.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.DetailForCharacters
import com.example.myapplication.data.model.Episode
import com.example.myapplication.data.network.ApiClient
import kotlinx.coroutines.launch

class CharacterDetailViewModel: ViewModel(){

    var charLoading: Boolean by mutableStateOf(true)

    lateinit var character: DetailForCharacters

    fun getCharacter(id: Int){
        viewModelScope.launch {
            charLoading = true
            val response = ApiClient.getApiClient().getCharactersDetail(id)
            character = response
            charLoading = false
        }
    }
}
