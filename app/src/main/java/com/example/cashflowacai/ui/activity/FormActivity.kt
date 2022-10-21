package com.example.cashflowacai.ui.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cashflowacai.R
import com.example.cashflowacai.database.AppDataBase
import com.example.cashflowacai.model.Register
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.time.format.DateTimeParseException
import java.util.*


class FormActivity : AppCompatActivity() {

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
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    // Creating database instance in class
    private val registerDao by lazy {
        val db = AppDataBase.instance(this)
        db.registerDao()
    }

    private var registerId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

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
        btnDate.setOnClickListener {
            showDateStartRangePicker()
        }

        // Seting date period selector
        setBtnSave()
    }

    private fun showDateStartRangePicker() {
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                tvDate.setText("" + mDay + "/" + (mMonth + 1) + "/" + mYear)
            }, year, month, day
        )
        dpd.show()
    }

    private fun selectTodayDate() {
        val datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Selecione a data").build()
        datePicker.show(supportFragmentManager, "date_picker")
        datePicker.addOnPositiveButtonClickListener { datePicked->
            if(datePicked != null) {
                val todayDate = convertLongToDate(datePicked)
                tvDate.text = todayDate
            }
        }
    }

    private fun convertLongToDate(time: Long): String{
        val date = Date(time)
        val simpleDateFormat = SimpleDateFormat(
            "dd/MM/yyyy",
            Locale("pt", "br")
        )
        return simpleDateFormat.format(date)
    }

    // Seting listener to get and save values of the EditText's
    private fun setBtnSave(){
        btnSave.setOnClickListener {
            val returnedRegister = addValues()
            registerDao.save(returnedRegister)
            Toast.makeText(
                this,
                "Registro salvo com sucesso! ",
                Toast.LENGTH_LONG
            ).show()
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

        tvTotal.text = "R$" + (pixValue + cashValue + debitValue + creditValue + ifoodValue).toString()

        // Inserting register to database
        return Register(
            id = registerId,
            pix = pixValue,
            cash = cashValue,
            debit = debitValue,
            credit = creditValue,
            ifood = ifoodValue,
            date = tvDate.text.toString()
        )
    }

     //Getting the current day date
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