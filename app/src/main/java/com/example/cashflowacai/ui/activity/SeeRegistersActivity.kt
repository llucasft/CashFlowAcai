package com.example.cashflowacai.ui.activity

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.cashflowacai.R
import com.example.cashflowacai.database.AppDataBase
import com.example.cashflowacai.model.Register
import java.util.*

class SeeRegistersActivity : AppCompatActivity() {

    lateinit var tvSeeRegiseterStartDate : TextView
    lateinit var tvSeeRegisterEndDate : TextView
    lateinit var btnSeeRegisterStartDate : Button
    lateinit var btnSeeRegisterEndDate : Button
    lateinit var tvSeeRegisterPix : TextView
    lateinit var tvSeeRegisterCash : TextView
    lateinit var tvSeeRegisterDebit : TextView
    lateinit var tvSeeRegisterCredit : TextView
    lateinit var tvSeeRegisterIfood : TextView
    lateinit var tvSeeRegisterTotalHeader : TextView
    lateinit var tvSeeRegisterTotalValue : TextView
    lateinit var toQuerySartDate: String
    lateinit var toQueryEndDate: String
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    lateinit var register : Register

    // Creating database instance in class
    private val registerDao by lazy {
        val db = AppDataBase.instance(this)
        db.registerDao()
    }

    private var registerId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_registers)

        val db = AppDataBase.instance(this)
        val registerDao = db.registerDao()

        // Initiating fields
        tvSeeRegiseterStartDate = findViewById(R.id.tvSeeRegisterStartDate)
        tvSeeRegisterEndDate = findViewById(R.id.tvSeeRegisterEndDate)
        btnSeeRegisterStartDate = findViewById(R.id.btnSeeRegisterStartDate)
        btnSeeRegisterEndDate = findViewById(R.id.btnSeeRegisterEndDate)
        tvSeeRegisterPix = findViewById(R.id.tvSeeRegisterPix)
        tvSeeRegisterCash = findViewById(R.id.tvSeeRegisterCash)
        tvSeeRegisterDebit = findViewById(R.id.tvSeeRegisterDebit)
        tvSeeRegisterCredit = findViewById(R.id.tvSeeRegisterCredit)
        tvSeeRegisterIfood = findViewById(R.id.tvSeeRegisterIfood)
        tvSeeRegisterTotalHeader = findViewById(R.id.tvSeeRegisterTotalHeader)
        tvSeeRegisterTotalValue = findViewById(R.id.tvSeeRegisterTotalValue)
        //val day = dateUpdateInView()

        //register = registerDao.selectFromDbByDate(day)

        //tvSeeRegisterTotalValue.text = "R$" + (register.pix + register.cash + register.debit + register.credit + register.ifood).toString()

//        val pixText : String = register.pix.toBrazilianReal()
//        tvSeeRegisterPix.text = "Pix: " + pixText
//
//        val cashText : String = register.cash.toBrazilianReal()
//        tvSeeRegisterCash.text = "Dinheiro: " + cashText
//
//        val debitText : String = register.debit.toBrazilianReal()
//        tvSeeRegisterDebit.text = "Débito: " + debitText
//
//        val creditText : String = register.credit.toBrazilianReal()
//        tvSeeRegisterCredit.text = "Crédito: " + creditText
//
//        val ifoodText : String = register.ifood.toBrazilianReal()
//        tvSeeRegisterIfood.text = "Ifood: " + ifoodText

        // Seting date period selector
        btnSeeRegisterStartDate.setOnClickListener {
            //toQuerySartDate = showDateStartRangePicker()
            showDateStartRangePicker()
        }

        btnSeeRegisterEndDate.setOnClickListener {
            //toQueryEndDate = showDateEndRangePicker()
            showDateEndRangePicker()
        }

        //registerDao.convertToLong(toQuerySartDate, toQueryEndDate)
    }

    private fun showDateStartRangePicker(){
        //var dateToReturn = ""
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                tvSeeRegiseterStartDate.setText("" + mDay + "/" + (mMonth+1) + "/" + mYear)
                //dateToReturn = "" + mDay + "/" + (mMonth+1) + "/" + mYear
            }, year, month, day)

        dpd.show()

//        val dateRangePicker = MaterialDatePicker.Builder
//            .dateRangePicker()
//            .setTitleText("Selecione a data")
//            .build()
//        dateRangePicker.show(
//            supportFragmentManager,
//            "date_range_picker"
//        )

//        dateRangePicker.addOnPositiveButtonClickListener { datePicked ->
//
//            val startDate = datePicked.first
//            val endDate = datePicked.second
//
//            if (startDate!=null && endDate!=null){
//                val startDateText = convertLongToDate(startDate)
//                val endDateText = convertLongToDate(endDate)
//                tvSeeRegiseterStartDate.text = startDateText.toString()
//                tvSeeRegisterEndDate.text = endDateText.toString()
//            }
//        }
    }

    private fun showDateEndRangePicker() {
        //var dateToReturn = ""
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                tvSeeRegisterEndDate.setText("" + mDay + "/" + (mMonth+1) + "/" + mYear)
                //dateToReturn = "" + mDay + "/" + (mMonth+1) + "/" + mYear
            }, year, month, day)
        dpd.show()
    }

//    private fun convertLongToDate(time: Long): String{
//        val date = Date(time)
//        val simpleDateFormat = SimpleDateFormat(
//            "dd/MM/yyyy",
//            Locale("pt", "br")
//        )
//        return simpleDateFormat.format(date)
//    }

    // Getting the current day date
//    private fun dateUpdateInView(): String{
//        // Setting date format to brazilian format
//        val dateFormatToPtBr = "dd/MM/yyyy"
//        val simpleDateFormat = SimpleDateFormat(dateFormatToPtBr, Locale("pt", "br"))
//        //tvDate.text = simpleDateFormat.format(calendar.time)
//        val todayDate = simpleDateFormat.format(calendar.time)
//        tvSeeRegiseterStartDate.text = todayDate
//        return todayDate
//    }
}