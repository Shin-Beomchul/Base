package com.godbeom.baseapp.dto

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class UserDTO(
    @PrimaryKey
    @Nullable
    @SerializedName("userId") val userId: String,

    @Nullable
    @SerializedName("userName") val userName:String
)