package com.example.myapplication.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Episode
import com.example.myapplication.data.network.ApiClient
import kotlinx.coroutines.launch

class EpisodeViewModel : ViewModel() {

    var episodeLoading: Boolean by mutableStateOf(true)

    lateinit var episodes: Episode

    fun getEpisode(url: String){
        viewModelScope.launch {
            episodeLoading = true
            val responseEpisode = ApiClient.getApiClient().getEpisodes(url)
            episodes = responseEpisode
            episodeLoading = false

        }
    }
}