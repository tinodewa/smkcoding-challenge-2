package com.smkcoding.hamurchef.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.smkcoding.hamurchef.data.recipe.Recipe
import com.smkcoding.hamurchef.data.recipe.RecipeData

@Dao
interface MyRecipeDao {
    @Query("SELECT * FROM my_recipe")

    fun getAllMyRecipe(): LiveData<RecipeData>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myFriend: RecipeData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(myFriends: RecipeData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(myFriend: RecipeData)

    @Delete()
    suspend fun delete(myFriend: RecipeData)

    @Query("DELETE FROM my_recipe")
    suspend fun deleteAll()
}