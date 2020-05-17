package com.smkcoding.hamurchef.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET

interface RecipeService {

    @GET("filter.php?i=egg")
    fun getRecipes():Call<RecipeResponse>

}