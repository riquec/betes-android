package com.jardim.betes.db.dao

import androidx.room.Dao
import androidx.room.Insert
import com.jardim.betes.db.entities.User

@Dao
interface UserDao {
    @Insert
    fun saveUser(vararg user: User)
}