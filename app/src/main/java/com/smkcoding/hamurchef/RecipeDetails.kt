package com.smkcoding.hamurchef

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.smkcoding.hamurchef.model.MyFavoriteModel
import com.smkcoding.hamurchef.utils.dismissLoading
import com.smkcoding.hamurchef.utils.showLoading
import kotlinx.android.synthetic.main.activity_recipe_details.*

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

    }

    private fun getData() {
        showLoading(this, detail_srl)

        val getUserID = auth?.currentUser?.uid.toString()
        val getRecipeName = detail_name.text.toString()

        dbRef.child(getUserID).child("favoriteRecipe").child(getRecipeName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    dismissLoading(detail_srl)
                    Toast.makeText(
                        this@RecipeDetails,
                        "gagal mendapatkan data :(",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    dismissLoading(detail_srl)

                    val data = snapshot.getValue(MyFavoriteModel::class.java)
                    val status = data?.status
                    status(status)

//                    for (shot in snapshot.children) {
//                        val data = shot.getValue(MyFavoriteModel::class.java)
//                        val status = data?.status
//                        Status(status)
//                    }

                    Toast.makeText(
                        this@RecipeDetails,
                        "Berhasil mendapatkan data :)",
                        Toast.LENGTH_SHORT
                    ).show()
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

//        val key = dbRef.child(getUserID).child("favoriteRecipe").child(getRecipeName).push().key

        recipe_fav.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked == true) {
                dbRef.child(getUserID).child("favoriteRecipe").child(getRecipeName)
                    .setValue(dataFav)
                    .addOnCompleteListener {
                        Toast.makeText(
                            this,
                            "Berhasil menambahkan ke favorit :)",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
            } else if (isChecked == false) {
                dbRef.child(getUserID).child("favoriteRecipe").child(getRecipeName)
                    .setValue(remDataFav)
                    .addOnCompleteListener {
                        Toast.makeText(
                            this,
                            "Berhasil menghapus dari favorit :)",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
            } else {
                Toast.makeText(this, "Gagal menambahkan ke favorit :(", Toast.LENGTH_SHORT)
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

    private fun status(status: String?) {
        when (status) {
            "checked" -> {
                recipe_fav.isChecked = true
            }
            "unchecked" -> {
                recipe_fav.isChecked = false
            }
            else -> {
                recipe_fav.isChecked = false
            }
        }
    }
}
