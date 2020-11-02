package com.godbeom.baseapp.persistence.entity

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity
data class UserDTO(
    @PrimaryKey
    @Nullable
    @field:Json(name="userId") val userId: String,

    @Nullable
    @field:Json(name="userName") val userName:String
)