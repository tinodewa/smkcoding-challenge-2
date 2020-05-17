package com.smkcoding.hamurchef.data


import com.google.gson.annotations.SerializedName

data class IngredientResponse(
    @SerializedName("meals")
    val meals: List<Ingredient>
)

data class Ingredient(
    @SerializedName("idIngredient")
    val idIngredient: String,
    @SerializedName("strDescription")
    val strDescription: String,
    @SerializedName("strIngredient")
    val strIngredient: String,
    @SerializedName("strType")
    val strType: Any
)