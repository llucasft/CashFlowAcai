package com.example.cashflowacai.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.cashflowacai.R
import com.example.cashflowacai.database.AppDataBase
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class SeeRegistersActivity : AppCompatActivity() {

    lateinit var tvSeeRegiseterDate : TextView
    lateinit var btnSeeRegisterDate : Button
    lateinit var tvSeeRegistrPix : TextView
    lateinit var tvSeeRegisterCash : TextView
    lateinit var tvSeeRegisterDebit : TextView
    lateinit var tvSeeRegisterCredit : TextView
    lateinit var tvSeeRegisterIfood : TextView
    lateinit var tvSeeRegisterTotalHeader : TextView
    lateinit var tvSeeRegisterTotalValue : TextView
    val calendar = Calendar.getInstance()

    // Creating database instance in class
    private val registerDao by lazy {
        val db = AppDataBase.instance(this)
        db.registerDao()
    }

    private var registerId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_registers)

        // Initiating fields
        tvSeeRegiseterDate = findViewById(R.id.tvSeeRegisterDate)
        btnSeeRegisterDate = findViewById(R.id.btnSeeRegisterDate)
        tvSeeRegistrPix = findViewById(R.id.tvSeeRegisterPix)
        tvSeeRegisterCash = findViewById(R.id.tvSeeRegisterCash)
        tvSeeRegisterDebit = findViewById(R.id.tvSeeRegisterDebit)
        tvSeeRegisterCredit = findViewById(R.id.tvSeeRegisterCredit)
        tvSeeRegisterIfood = findViewById(R.id.tvSeeRegisterIfood)
        tvSeeRegisterTotalHeader = findViewById(R.id.tvSeeRegisterTotalHeader)
        tvSeeRegisterTotalValue = findViewById(R.id.tvSeeRegisterTotalValue)
        dateUpdateInView()

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