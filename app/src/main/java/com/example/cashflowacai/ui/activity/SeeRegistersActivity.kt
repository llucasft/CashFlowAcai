package com.example.cashflowacai.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.cashflowacai.R
import com.example.cashflowacai.database.AppDataBase
import com.example.cashflowacai.extensions.toBrazilianReal
import com.example.cashflowacai.model.Register
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class SeeRegistersActivity : AppCompatActivity() {

    lateinit var tvSeeRegiseterDate : TextView
    lateinit var btnSeeRegisterDate : Button
    lateinit var tvSeeRegisterPix : TextView
    lateinit var tvSeeRegisterCash : TextView
    lateinit var tvSeeRegisterDebit : TextView
    lateinit var tvSeeRegisterCredit : TextView
    lateinit var tvSeeRegisterIfood : TextView
    lateinit var tvSeeRegisterTotalHeader : TextView
    lateinit var tvSeeRegisterTotalValue : TextView
    val calendar = Calendar.getInstance()
    lateinit var register : Register

    // Creating database instance in class
//    private val registerDao by lazy {
//        val db = AppDataBase.instance(this)
//        db.registerDao()
//    }

    private var registerId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_registers)

        val db = AppDataBase.instance(this)
        val registerDao = db.registerDao()

        // Initiating fields
        tvSeeRegiseterDate = findViewById(R.id.tvSeeRegisterDate)
        btnSeeRegisterDate = findViewById(R.id.btnSeeRegisterDate)
        tvSeeRegisterPix = findViewById(R.id.tvSeeRegisterPix)
        tvSeeRegisterCash = findViewById(R.id.tvSeeRegisterCash)
        tvSeeRegisterDebit = findViewById(R.id.tvSeeRegisterDebit)
        tvSeeRegisterCredit = findViewById(R.id.tvSeeRegisterCredit)
        tvSeeRegisterIfood = findViewById(R.id.tvSeeRegisterIfood)
        tvSeeRegisterTotalHeader = findViewById(R.id.tvSeeRegisterTotalHeader)
        tvSeeRegisterTotalValue = findViewById(R.id.tvSeeRegisterTotalValue)
        val day = dateUpdateInView()

        register = registerDao.selectFromDbByDate(day)

        tvSeeRegisterTotalValue.text = "R$" + (register.pix + register.cash + register.debit + register.credit + register.ifood).toString()

        val pixText : String = register.pix.toBrazilianReal()
        tvSeeRegisterPix.text = "Pix: " + pixText

        val cashText : String = register.cash.toBrazilianReal()
        tvSeeRegisterCash.text = "Dinheiro: " + cashText

        val debitText : String = register.debit.toBrazilianReal()
        tvSeeRegisterDebit.text = "Débito: " + debitText

        val creditText : String = register.credit.toBrazilianReal()
        tvSeeRegisterCredit.text = "Crédito: " + creditText

        val ifoodText : String = register.ifood.toBrazilianReal()
        tvSeeRegisterIfood.text = "Ifood: " + ifoodText

        // Seting date period selector
        btnSeeRegisterDate.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
            datePicker.show(supportFragmentManager, "Selecione a data")
        }
    }

    // Getting the current day date
    private fun dateUpdateInView(): String{
        // Setting date format to brazilian format
        val dateFormatToPtBr = "dd/MM/yyyy"
        val simpleDateFormat = SimpleDateFormat(dateFormatToPtBr, Locale("pt", "br"))
        //tvDate.text = simpleDateFormat.format(calendar.time)
        val todayDate = simpleDateFormat.format(calendar.time)
        tvSeeRegiseterDate.text = todayDate
        return todayDate
    }
}