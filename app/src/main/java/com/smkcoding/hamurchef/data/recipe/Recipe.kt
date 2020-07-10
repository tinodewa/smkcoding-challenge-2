package com.smkcoding.hamurchef.data.recipe

import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("meals")
    val meals: List<RecipeData>
)