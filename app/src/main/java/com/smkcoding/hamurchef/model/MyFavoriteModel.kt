package com.smkcoding.hamurchef.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class MyFavoriteModel(
    var recipe_name: String? = "",
    var status: String? = ""
) {
    //    constructor() : this("", "", "")
    // [START fav_to_map]
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "recipe_name" to recipe_name,
            "status" to status
        )
    }
    // [END fav_to_map]
}
// [END fav_class]