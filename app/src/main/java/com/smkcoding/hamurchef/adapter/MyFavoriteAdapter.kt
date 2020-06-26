package com.smkcoding.hamurchef.adapter

import android.content.Context
import com.google.firebase.database.IgnoreExtraProperties
import com.smkcoding.hamurchef.model.MyFavoriteModel

@IgnoreExtraProperties
data class MyFavoriteAdapter (
    var recipe_name: String? = "",
    var status: String? = ""
)