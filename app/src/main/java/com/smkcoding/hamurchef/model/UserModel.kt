package com.smkcoding.hamurchef.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserModel(
    var name: String,
    var email: String,
    var password: String
){
    constructor() : this("","","")
}