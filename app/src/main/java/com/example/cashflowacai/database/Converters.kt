package com.example.cashflowacai.database

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.util.*

@Suppress("DEPRECATION")
class Converters {

    // Converter from double to big decimal
    @TypeConverter
    fun fromDouble(value: Double?) : BigDecimal{
        return value?.let { BigDecimal(value.toString()) } ?: BigDecimal.ZERO
    }

    // Converter from big decimal to double
    @TypeConverter
    fun bigDecimalToDouble(value: BigDecimal?) : Double?{
        return value?.let { value.toDouble() }
    }

    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

}