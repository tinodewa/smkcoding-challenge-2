package com.smkcoding.hamurchef.data

import com.smkcoding.hamurchef.data.recipe.Recipe
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {

    @GET("search.php?f=b")
    fun getRecipes():Call<Recipe>

    @GET("filter.php")
    fun getIngredient(@Query("i") ingredient: String):Call<Recipe>

    @GET("list.php?i=list")
    fun getMainIngredients():Call<IngredientResponse>

    @GET("lookup.php")
    fun getDetails(@Query("i") foodName: String?):Call<Recipe>
}