package com.smkcoding.hamurchef.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.smkcoding.hamurchef.dao.MyRecipeDao
import com.smkcoding.hamurchef.data.recipe.RecipeData

@Database(entities = [RecipeData::class], version = 1)
abstract class MyRecipeDatabase : RoomDatabase() {
    abstract fun myRecipeDao(): MyRecipeDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MyRecipeDatabase? = null

        fun getDatabase(context: Context): MyRecipeDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyRecipeDatabase::class.java,
                    "my_recipe_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}