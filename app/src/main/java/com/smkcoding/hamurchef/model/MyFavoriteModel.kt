package com.smkcoding.hamurchef.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_favorite")
data class MyFavoriteModel(
    var recipe_name: String,
    var status: String,
    @PrimaryKey var key: String
) {
    constructor() : this("", "", "")
}