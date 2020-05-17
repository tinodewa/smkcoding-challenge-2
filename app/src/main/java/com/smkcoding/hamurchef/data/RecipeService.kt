package com.smkcoding.hamurchef.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeService {

    @GET("search.php?f=b")
    fun getRecipes():Call<RecipeResponse>

    @GET("filter.php")
    fun getIngredient(@Query("i") ingredient: String):Call<RecipeResponse>

    @GET("list.php?i=list")
    fun getMainIngredients():Call<IngredientResponse>

//    @GET("lookup.php")
//    fun getDetails(@Query("i") foodName: String?):Call<DetailResponse>
}