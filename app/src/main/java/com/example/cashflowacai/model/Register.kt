package com.example.cashflowacai.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.util.*

@Entity
@Parcelize
data class Register (

    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val date: Date,
    val pix: BigDecimal,
    val cash: BigDecimal,
    val debit: BigDecimal,
    val credit: BigDecimal,
    val ifood: BigDecimal

) : Parcelable