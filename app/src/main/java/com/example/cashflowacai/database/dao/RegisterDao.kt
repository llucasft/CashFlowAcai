package com.example.cashflowacai.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cashflowacai.model.Register

@Dao
interface RegisterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg register: Register)

    @Query("SELECT * FROM Register")
    fun selectFromDb() : List<Register>
}