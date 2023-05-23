package com.example.myapplication.data.network

import com.example.myapplication.data.model.DetailForCharacters
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.myapplication.data.model.CharactersResponse
import com.example.myapplication.data.model.Episode
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {
    @GET("/api/character")
    suspend fun loadList(@Query(value = "page") page: Int): CharactersResponse

    @GET("/api/character/{id}")
    suspend fun getCharactersDetail(@Path("id") id:Int): DetailForCharacters

    @GET()
    suspend fun getEpisodes(@Url url: String) : Episode
}

