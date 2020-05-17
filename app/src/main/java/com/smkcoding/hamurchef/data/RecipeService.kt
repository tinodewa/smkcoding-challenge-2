package com.smkcoding.hamurchef.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeService {

    @GET("filter.php?i=egg")
    fun getRecipes():Call<RecipeResponse>

    @GET("filter.php?i={ingredient}")
    fun getIngredient(@Path("ingredient") ingredient: String):Call<RecipeResponse>

}