@file:Suppress("DEPRECATION")

package com.example.cashflowacai.database.dao

import androidx.room.*
import com.example.cashflowacai.model.Register
import java.util.*

@Dao
interface RegisterDao {

    fun convertToLong(startDate: String, endDate: String) {

        try {
            val valueStart = startDate.toLong()
            val valueEnd = endDate.toLong()
        }
        catch (ex: NumberFormatException) {
            println("Please enter a number: ")
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg register: Register)

    @Query("SELECT * FROM Register")
    fun selectFromDb() : List<Register>

    @Query("SELECT * FROM Register WHERE date = :date")
    fun selectFromDbByDate(date: String) : Register

}