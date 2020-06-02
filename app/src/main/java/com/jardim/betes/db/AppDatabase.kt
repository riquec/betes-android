package com.jardim.betes.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jardim.betes.db.dao.UserDao
import com.jardim.betes.db.entities.User

@Database(entities = [User::class], version = 1,  exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao() : UserDao
}