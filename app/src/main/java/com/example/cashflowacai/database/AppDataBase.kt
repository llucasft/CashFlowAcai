package com.example.cashflowacai.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cashflowacai.database.dao.RegisterDao
import com.example.cashflowacai.model.Register

@Database(entities = [Register::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun registerDao(): RegisterDao

    companion object {
    fun instance(context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "cashflow.db"
        ).allowMainThreadQueries()
            .build()
    }
    }
}