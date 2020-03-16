package com.godbeom.baseapp.persistence.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.godbeom.baseapp.dto.UserDTO

@Database(entities = [UserDTO::class], version = 1)
@TypeConverters(value = [DateConverters::class])
abstract class AppDatabase  :RoomDatabase(){

    abstract fun getUserDAO(): UserDAO
}