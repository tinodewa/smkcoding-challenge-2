package com.smkcoding.hamurchef.data

import com.google.gson.annotations.SerializedName

data class RecipeResponse (
    @SerializedName("title")
    val title: String,
    @SerializedName("version")
    val version: Double,
    @SerializedName("href")
    val href: String,
    @SerializedName("results")
    val results: List<Recipe>?
)

data class Recipe(
    @SerializedName("title")
    val title: String,
    @SerializedName("href")
    val href: String,
    @SerializedName("ingredients")
    val ingredients: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)
