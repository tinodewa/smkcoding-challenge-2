package com.smkcoding.hamurchef

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.smkcoding.hamurchef.data.*
import com.smkcoding.hamurchef.utils.tampilToast
import kotlinx.android.synthetic.main.activity_recipe_details.*
import kotlinx.android.synthetic.main.home_recipe_book_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        getDetailRecipe()
    }

    private fun getDetailRecipe() {
        val bundle = intent.extras

        detail_name.text = bundle?.getString("mealName").toString()
        detail_tags.text = bundle?.getString("mealTags").toString()

        Glide.with(this).load(bundle?.getString("mealThumb")).into(detail_Image)
    }

}
