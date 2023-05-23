package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharactersResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<CharacterFromRickAndMorty>
) : Serializable
