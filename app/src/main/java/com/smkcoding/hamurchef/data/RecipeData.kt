package com.smkcoding.hamurchef.data


import com.google.gson.annotations.SerializedName

class RecipeData(
    @SerializedName("href")
    val href: String,
    @SerializedName("results")
    val results: ArrayList<Result>,
    @SerializedName("title")
    val title: String,
    @SerializedName("version")
    val version: Double
)