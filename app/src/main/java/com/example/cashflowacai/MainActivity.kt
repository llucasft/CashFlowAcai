package com.example.cashflowacai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.cashflowacai.database.AppDataBase
import com.example.cashflowacai.model.Register
import com.google.android.material.datepicker.MaterialDatePicker
import org.w3c.dom.Text
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var tvDate : TextView
    lateinit var btnDate : Button
    lateinit var etPix : EditText
    lateinit var etCash : EditText
    lateinit var etDebit : EditText
    lateinit var etCredit : EditText
    lateinit var etIfood : EditText
    lateinit var btnSave : Button
    lateinit var tvTotal : TextView
    val calendar = Calendar.getInstance()

    // Creating database instance in class
    private val registerDao by lazy {
        val db = AppDataBase.instance(this)
        db.registerDao()
    }

    private var registerId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initiating fields
        tvDate = findViewById(R.id.tvDate)
        btnDate = findViewById(R.id.btnDate)
        etPix = findViewById(R.id.etPix)
        etCash = findViewById(R.id.etCash)
        etDebit = findViewById(R.id.etDebit)
        etCredit = findViewById(R.id.etCredit)
        etIfood = findViewById(R.id.etIfood)
        btnSave = findViewById(R.id.btnSave)
        tvTotal = findViewById(R.id.tvTotal)
        dateUpdateInView()

        // Seting date period selector
        btnDate.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
            datePicker.show(supportFragmentManager, "Selecione a data")
        }

        setBtnSave()
    }

    // Seting listener to get and save values of the EditText's
    private fun setBtnSave(){
        btnSave.setOnClickListener {
            var total = addValues()
            //tvTotal.text = "Total de entradas: $total"
            registerDao.save(total)
        }
    }

    // Getting values from the EditText fields
    private fun addValues(): Register{
        val pixValueText = etPix.text.toString()
        val pixValue = if(pixValueText.isBlank()){
            BigDecimal.ZERO
        } else {
            BigDecimal(pixValueText)
        }

        val cashValueText = etCash.text.toString()
        val cashValue = if(cashValueText.isBlank()){
            BigDecimal.ZERO
        } else {
            BigDecimal(cashValueText)
        }

        val debitValueText = etDebit.text.toString()
        val debitValue = if(debitValueText.isBlank()){
            BigDecimal.ZERO
        } else {
            BigDecimal(debitValueText)
        }

        val creditValueText = etCredit.text.toString()
        val creditValue = if(creditValueText.isBlank()){
            BigDecimal.ZERO
        } else {
            BigDecimal(creditValueText)
        }

        val ifoodValueText = etIfood.text.toString()
        val ifoodValue = if(ifoodValueText.isBlank()){
            BigDecimal.ZERO
        } else {
            BigDecimal(ifoodValueText)
        }
        
        // Inserting register to database
        return Register(
            id = registerId,
            pix = pixValue,
            cash = cashValue,
            debit = debitValue,
            credit = creditValue,
            ifood = ifoodValue,
            date = dateUpdateInView()
        )
    }

    // Getting the current day date
    private fun dateUpdateInView(): String{
        // Setting date format to brazilian format
        val dateFormatToPtBr = "dd/MM/yyyy"
        val simpleDateFormat = SimpleDateFormat(dateFormatToPtBr, Locale("pt", "br"))
        //tvDate.text = simpleDateFormat.format(calendar.time)
        val todayDate = simpleDateFormat.format(calendar.time)
        tvDate.text = todayDate
        return todayDate
    }
}