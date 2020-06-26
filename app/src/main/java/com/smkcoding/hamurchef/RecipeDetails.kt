package com.smkcoding.hamurchef

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.smkcoding.hamurchef.data.*
import com.smkcoding.hamurchef.model.MyFavoriteModel
import com.smkcoding.hamurchef.utils.tampilToast
import kotlinx.android.synthetic.main.activity_recipe_details.*
import kotlinx.android.synthetic.main.home_recipe_book_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeDetails : AppCompatActivity() {

    lateinit var db: DatabaseReference
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        getDetailRecipe()

        db = FirebaseDatabase.getInstance().getReference()
        auth = FirebaseAuth.getInstance()

        recipe_fav.setOnClickListener {
            addRecipeToFavorite()
        }
    }

    private fun addRecipeToFavorite() {
        val getRecipeName = detail_name.text.toString()
        val getUserID = auth?.currentUser?.uid.toString()
        val getStatus = "checked"

        val data_fav = MyFavoriteModel(getRecipeName, getStatus, "")

        db.child("users").child(getUserID).push().setValue(data_fav).addOnCompleteListener {
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDetailRecipe() {
        val bundle = intent.extras

        detail_name.text = bundle?.getString("mealName").toString()
        detail_tags.text = bundle?.getString("mealTags").toString()

        Glide.with(this).load(bundle?.getString("mealThumb")).into(detail_Image)
    }

}
