package com.smkcoding.hamurchef.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "my_ingredient")
data class IngredientResponse(
    @SerializedName("meals")
    val meals: List<Ingredient>
)

data class Ingredient(
    @SerializedName("idIngredient")
    @PrimaryKey val idIngredient: String,
    @SerializedName("strDescription")
    val strDescription: String,
    @SerializedName("strIngredient")
    val strIngredient: String,
    @SerializedName("strType")
    val strType: Any
)