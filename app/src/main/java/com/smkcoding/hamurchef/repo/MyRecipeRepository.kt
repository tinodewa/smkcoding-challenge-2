package com.smkcoding.hamurchef.repo

import androidx.lifecycle.LiveData
import com.smkcoding.hamurchef.dao.MyRecipeDao
import com.smkcoding.hamurchef.data.recipe.Recipe
import com.smkcoding.hamurchef.data.recipe.RecipeData

class MyRecipeRepository(private val myRecipeDao: MyRecipeDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allMyRecipe: LiveData<RecipeData> = myRecipeDao.getAllMyRecipe()

    suspend fun insert(myRecipe: RecipeData) {
        myRecipeDao.insert(myRecipe)
    }

    suspend fun insertAll(myRecipes: RecipeData) {
        myRecipeDao.insertAll(myRecipes)
    }

    suspend fun deleteAll() {
        myRecipeDao.deleteAll()
    }

    suspend fun update(myRecipe: RecipeData) {
        myRecipeDao.update(myRecipe)
    }

    suspend fun delete(myRecipe: RecipeData) {
        myRecipeDao.delete(myRecipe)
    }

}