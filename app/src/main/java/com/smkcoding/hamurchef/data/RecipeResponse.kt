package com.smkcoding.hamurchef.data

import com.google.gson.annotations.SerializedName

data class RecipeResponse (
    @SerializedName("title")
    val title: String,
    @SerializedName("version")
    val version: Double,
    @SerializedName("href")
    val href: String,
    @SerializedName("result")
    val result: ArrayList<Recipe>
)
