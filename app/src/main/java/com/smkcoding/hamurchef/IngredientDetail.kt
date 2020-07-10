package com.smkcoding.hamurchef

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ingredient_detail.*

class IngredientDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient_detail)

        getDetailIngredient()
    }

    private fun getDetailIngredient() {
        val bundle = intent.extras

        ingredient_name.text = bundle?.getString("ingName").toString()
        ingredient_desc.text = bundle?.getString("ingDesc").toString()

    }

}
