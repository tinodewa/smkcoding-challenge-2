package com.smkcoding.hamurchef

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.smkcoding.hamurchef.data.*
import com.smkcoding.hamurchef.model.MyFavoriteModel
import com.smkcoding.hamurchef.utils.dismissLoading
import com.smkcoding.hamurchef.utils.showLoading
import com.smkcoding.hamurchef.utils.tampilToast
import kotlinx.android.synthetic.main.activity_recipe_details.*
import kotlinx.android.synthetic.main.fragment_ingredient.*
import kotlinx.android.synthetic.main.home_recipe_book_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeDetails : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        dbRef = FirebaseDatabase.getInstance().getReference("users")
        auth = FirebaseAuth.getInstance()

        getDetailRecipe()

        getData()

        recipe_fav.setOnClickListener {
            addRecipeToFavorite()
        }

        cek_status.setOnClickListener {
            updatedata()
        }

    }

    private fun updatedata() {

        val getRecipeName = detail_name.text.toString()
        val getUserID = auth?.currentUser?.uid.toString()
        val checked = "checked"
        val unchecked = "unchecked"
        val key = dbRef.child(getUserID).child("favoriteRecipe").child(getRecipeName).key

        val remDataFav = MyFavoriteModel(getRecipeName, unchecked)
        val postRemove = remDataFav.toMap()

        val dataUpdate = hashMapOf<String, Any>(
            "/$getUserID/favoriteRecipe/$getRecipeName/" to postRemove
        )


        dbRef.updateChildren(dataUpdate)
            .addOnCompleteListener {
                Toast.makeText(
                    this,
                    "Success add recipe to favorite <3",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
    }

    private fun getData() {
        showLoading(this, detail_srl)

        val getUserID = auth?.currentUser?.uid.toString()
        val getRecipeName = detail_name.text.toString()

        dbRef.child(getUserID).child("favoriteRecipe").child(getRecipeName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    dismissLoading(detail_srl)
                    Toast.makeText(this@RecipeDetails, "gagal Gan...", Toast.LENGTH_SHORT).show()
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    dismissLoading(detail_srl)

                        val data = snapshot.getValue(MyFavoriteModel::class.java)
                        val status = data?.status
                        Status(status)

//                    for (shot in snapshot.children) {
//                        val data = shot.getValue(MyFavoriteModel::class.java)
//                        val status = data?.status
//                        Status(status)
//                    }

                    Toast.makeText(this@RecipeDetails, "Berhasil Gan...", Toast.LENGTH_SHORT).show()
                }
            })

    }

    private fun addRecipeToFavorite() {
        val getRecipeName = detail_name.text.toString()
        val getUserID = auth?.currentUser?.uid.toString()
        val checked = "checked"
        val unchecked = "unchecked"

        val dataFav = MyFavoriteModel(getRecipeName, checked)
        val remDataFav = MyFavoriteModel(getRecipeName, unchecked)

        val key = dbRef.child(getUserID).child("favoriteRecipe").child(getRecipeName).push().key

        recipe_fav.setOnCheckedChangeListener { check, isChecked ->
            if (isChecked) {
                dbRef.child(getUserID).child("favoriteRecipe").child(getRecipeName)
                    .setValue(dataFav)
                    .addOnCompleteListener {
                        Toast.makeText(
                            this,
                            "Success add recipe to favorite <3",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
            } else {
                Toast.makeText(this, "Fail add recipe to favorite :(", Toast.LENGTH_SHORT)
                    .show()
            }
        }

//        if (recipe_fav.isChecked == false) {
//            dbRef.child(getUserID).child("favoriteRecipe").child(getRecipeName).push()
//                .setValue(dataFav)
//                .addOnCompleteListener {
//                    Status(getStatus)
//                    Toast.makeText(this, "Success add recipe to favorite <3", Toast.LENGTH_SHORT)
//                        .show()
//                }
//        } else if (recipe_fav.isChecked == true) {
//            Toast.makeText(this, "Recipe already added to favorite <3", Toast.LENGTH_SHORT).show()
//        }
    }

    private fun getDetailRecipe() {
        val bundle = intent.extras

        detail_name.text = bundle?.getString("mealName").toString()
        detail_tags.text = bundle?.getString("mealTags").toString()

        Glide.with(this).load(bundle?.getString("mealThumb")).into(detail_Image)
    }

    private fun Status(status: String?) {
        if (status == "checked") {
            recipe_fav.isChecked = true
        } else if (status == "unchecked") {
            recipe_fav.isChecked = false
        } else {
            recipe_fav.isChecked = false
            Toast.makeText(this@RecipeDetails, "Gagal memproses data!", Toast.LENGTH_SHORT).show()
        }
    }
}
