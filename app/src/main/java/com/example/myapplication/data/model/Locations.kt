package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Locations(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) : Serializable
