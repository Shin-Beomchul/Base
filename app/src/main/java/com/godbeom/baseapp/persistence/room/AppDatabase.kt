package com.godbeom.baseapp.persistence.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.godbeom.baseapp.persistence.entity.Matchs
import com.godbeom.baseapp.persistence.entity.UserDTO

@Database(entities = [UserDTO::class, Matchs.Match::class,Matchs.MatchRemotehKeys::class], version = 1)
@TypeConverters(value = [DateConverters::class])
abstract class AppDatabase  :RoomDatabase(){

    abstract fun getUserDAO(): UserDAO
    abstract fun getMatchDAO(): MatchDAO
    abstract fun getMatchRemoteKey(): MatchRemoteKeyDAO
}