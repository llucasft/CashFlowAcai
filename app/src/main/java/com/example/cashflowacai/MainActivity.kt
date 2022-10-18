package com.example.cashflowacai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var tvDate : TextView
    lateinit var btnDate : Button
    val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDate = findViewById(R.id.tvDate)
        btnDate = findViewById(R.id.btnDate)
        dateUpdateInView()

        btnDate.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
            datePicker.show(supportFragmentManager, "Selecione a data")
        }
    }

    private fun dateUpdateInView(){
        val dateFormatToPtBr = "dd/MM/yyyy"
        val simpleDateFormat = SimpleDateFormat(dateFormatToPtBr, Locale("pt", "br"))
        tvDate.text = simpleDateFormat.format(calendar.time)
    }
}