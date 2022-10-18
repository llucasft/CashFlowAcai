package com.example.cashflowacai.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Entity
@Parcelize
data class Register (

    val date: String,
    val pix: BigDecimal,
    val cash: BigDecimal,
    val debit: BigDecimal,
    val credit: BigDecimal,
    val ifood: BigDecimal

) : Parcelable