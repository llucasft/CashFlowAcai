package com.example.cashflowacai.ui.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cashflowacai.R
import com.example.cashflowacai.database.AppDataBase
import com.example.cashflowacai.databinding.ActivityFormBinding
import com.example.cashflowacai.model.Register
import com.google.android.material.datepicker.MaterialDatePicker
import java.math.BigDecimal
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class FormActivity : AppCompatActivity() {

    lateinit var binding: ActivityFormBinding
    var dateToModel: String = ""
    private val calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)

    // Creating database instance in class
    private val registerDao by lazy {
        val db = AppDataBase.instance(this)
        db.registerDao()
    }

    private var registerId = 0L
    private lateinit var date: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dateUpdateInView()
        binding.btnDate.setOnClickListener {
            showDateStartRangePicker()
        }

        // Seting date period selector
        setBtnSave()
    }

    private fun showDateStartRangePicker() {
        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                binding.tvDate.setText("" + mDay + "/" + (mMonth + 1) + "/" + mYear)
            }, year, month, day
        )
        dpd.show()
    }

    private fun selectTodayDate() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker().setTitleText("Selecione a data").build()
        datePicker.show(supportFragmentManager, "date_picker")
        datePicker.addOnPositiveButtonClickListener { datePicked ->
            if (datePicked != null) {
                val todayDate = convertLongToDate(datePicked)
                binding.tvDate.text = todayDate
            }
        }
    }

    private fun convertLongToDate(time: Long): String {
        val dateToLong = Date(time)
        val simpleDateFormat = SimpleDateFormat(
            "dd/MM/yyyy",
            Locale("pt", "br")
        )
        return simpleDateFormat.format(dateToLong)
    }

    // Seting listener to get and save values of the EditText's
    private fun setBtnSave() {
        binding.btnSave.setOnClickListener {
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
    private fun addValues(): Register {
        with(binding) {
            val pixValueText = etPix.text.toString()
            val pixValue = if (pixValueText.isBlank()) {
                BigDecimal.ZERO
            } else {
                BigDecimal(pixValueText)
            }

            val cashValueText = etCash.text.toString()
            val cashValue = if (cashValueText.isBlank()) {
                BigDecimal.ZERO
            } else {
                BigDecimal(cashValueText)
            }

            val debitValueText = etDebit.text.toString()
            val debitValue = if (debitValueText.isBlank()) {
                BigDecimal.ZERO
            } else {
                BigDecimal(debitValueText)
            }

            val creditValueText = etCredit.text.toString()
            val creditValue = if (creditValueText.isBlank()) {
                BigDecimal.ZERO
            } else {
                BigDecimal(creditValueText)
            }

            val ifoodValueText = etIfood.text.toString()
            val ifoodValue = if (ifoodValueText.isBlank()) {
                BigDecimal.ZERO
            } else {
                BigDecimal(ifoodValueText)
            }

            dateToModel = binding.tvDate.text.toString();  // where startDate is your TextView
            val simpleDateFormat = SimpleDateFormat(
                "dd/MM/yyyy",
                Locale("pt", "br")
            );  // same date format as your TextView supports
            try {
                date =
                    simpleDateFormat.parse(dateToModel); // parses the string date to get a date object
            } catch (e: ParseException) {
                e.printStackTrace();
            }
            tvTotal.text =
                "R$" + (pixValue + cashValue + debitValue + creditValue + ifoodValue).toString()

            // Inserting register to database
            return Register(
                id = registerId,
                pix = pixValue,
                cash = cashValue,
                debit = debitValue,
                credit = creditValue,
                ifood = ifoodValue,
                date = date
            )
        }
    }

    //Getting the current day date
    private fun dateUpdateInView(): String {
        // Setting date format to brazilian format
        val dateFormatToPtBr = "dd/MM/yyyy"
        val simpleDateFormat = SimpleDateFormat(dateFormatToPtBr, Locale("pt", "br"))
        //tvDate.text = simpleDateFormat.format(calendar.time)
        val todayDate = simpleDateFormat.format(calendar.time)
        binding.tvDate.text = todayDate
        return todayDate
    }
}