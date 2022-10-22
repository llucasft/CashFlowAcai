@file:Suppress("DEPRECATION")

package com.example.cashflowacai.database.dao

import androidx.room.*
import com.example.cashflowacai.model.Register
import java.math.BigDecimal
import java.util.*

@Dao
interface RegisterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg register: Register)

    @Query("SELECT SUM(pix) FROM Register WHERE date BETWEEN :dateStart AND :dateEnd")
    fun selectPixFromDbByDate(dateStart: Date?, dateEnd: Date?) : BigDecimal

    @Query("SELECT SUM(cash) FROM Register WHERE date BETWEEN :dateStart AND :dateEnd")
    fun selectCashFromDbByDate(dateStart: Date?, dateEnd: Date?) : BigDecimal

    @Query("SELECT SUM(debit) FROM Register WHERE date BETWEEN :dateStart AND :dateEnd")
    fun selectDebitFromDbByDate(dateStart: Date?, dateEnd: Date?) : BigDecimal

    @Query("SELECT SUM(credit) FROM Register WHERE date BETWEEN :dateStart AND :dateEnd")
    fun selectCreditFromDbByDate(dateStart: Date?, dateEnd: Date?) : BigDecimal

    @Query("SELECT SUM(ifood) FROM Register WHERE date BETWEEN :dateStart AND :dateEnd")
    fun selectIfoodFromDbByDate(dateStart: Date?, dateEnd: Date?) : BigDecimal

}