package com.smkcoding.hamurchef.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeService {

    @GET("filter.php?i=egg")
    fun getRecipes():Call<RecipeResponse>

    @GET("filter.php")
    fun getIngredient(@Query("i") ingredient: String):Call<RecipeResponse>

    @GET("list.php?i=list")
    fun getMainIngredients():Call<IngredientResponse>
}