package com.smkcoding.hamurchef.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smkcoding.hamurchef.data.recipe.Recipe
import com.smkcoding.hamurchef.data.recipe.RecipeData
import com.smkcoding.hamurchef.db.MyRecipeDatabase
import com.smkcoding.hamurchef.repo.MyRecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyRecipeFragmentViewModel(): ViewModel() {

    lateinit var repository: MyRecipeRepository
    lateinit var allMyRecipes: LiveData<RecipeData>

    fun init(context: Context) {
        val myRecipeDao = MyRecipeDatabase.getDatabase(context).myRecipeDao()
        repository = MyRecipeRepository(myRecipeDao)
        allMyRecipes = repository.allMyRecipe
    }

    fun delete(myRecipe: RecipeData) = viewModelScope.launch (Dispatchers.IO) {
        repository.delete(myRecipe)
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertAll(myRecipes: RecipeData) =
        viewModelScope.launch (Dispatchers.IO) {
            repository.deleteAll()
            repository.insertAll(myRecipes)
        }
}