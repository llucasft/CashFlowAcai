package com.example.cashflowacai.ui.activity

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.cashflowacai.R
import com.example.cashflowacai.database.AppDataBase
import com.example.cashflowacai.extensions.toBrazilianReal
import com.example.cashflowacai.model.Register
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class SeeRegistersActivity : AppCompatActivity() {

    lateinit var tvSeeRegiseterStartDate : TextView
    lateinit var tvSeeRegisterEndDate : TextView
    lateinit var btnSeeRegisterStartDate : Button
    lateinit var btnSeeRegisterEndDate : Button
    lateinit var btnQuery : Button
    lateinit var tvSeeRegisterPix : TextView
    lateinit var tvSeeRegisterCash : TextView
    lateinit var tvSeeRegisterDebit : TextView
    lateinit var tvSeeRegisterCredit : TextView
    lateinit var tvSeeRegisterIfood : TextView
    lateinit var tvSeeRegisterTotalHeader : TextView
    lateinit var tvSeeRegisterTotalValue : TextView
    var toQuerySartDate = ""
    var toQueryEndDate = ""
    var date1 : Date? = null
    var date2 : Date? = null
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
        btnQuery = findViewById(R.id.btnQuery)
        tvSeeRegisterPix = findViewById(R.id.tvSeeRegisterPix)
        tvSeeRegisterCash = findViewById(R.id.tvSeeRegisterCash)
        tvSeeRegisterDebit = findViewById(R.id.tvSeeRegisterDebit)
        tvSeeRegisterCredit = findViewById(R.id.tvSeeRegisterCredit)
        tvSeeRegisterIfood = findViewById(R.id.tvSeeRegisterIfood)
        tvSeeRegisterTotalHeader = findViewById(R.id.tvSeeRegisterTotalHeader)
        tvSeeRegisterTotalValue = findViewById(R.id.tvSeeRegisterTotalValue)
        //val day = dateUpdateInView()

        //register = registerDao.selectFromDbByDate(day)

        // Seting date period selector
        btnSeeRegisterStartDate.setOnClickListener {
            //toQuerySartDate = showDateStartRangePicker()
            showDateStartRangePicker()
        }

        btnQuery.setOnClickListener {
            val pixText = registerDao.selectPixFromDbByDate(date1, date2)
            val pixValue : String = pixText.toBrazilianReal()
            tvSeeRegisterPix.text = "Pix: " + pixValue

            val cashText = registerDao.selectCashFromDbByDate(date1, date2)
            val cashValue : String = cashText.toBrazilianReal()
            tvSeeRegisterCash.text = "Pix: " + cashValue

            val debitText = registerDao.selectDebitFromDbByDate(date1, date2)
            val debitValue : String = debitText.toBrazilianReal()
            tvSeeRegisterDebit.text = "Pix: " + debitValue

            val creditText = registerDao.selectCreditFromDbByDate(date1, date2)
            val creditValue : String = creditText.toBrazilianReal()
            tvSeeRegisterCredit.text = "Pix: " + creditValue

            val ifoodText = registerDao.selectIfoodFromDbByDate(date1, date2)
            val ifoodValue : String = ifoodText.toBrazilianReal()
            tvSeeRegisterIfood.text = "Pix: " + ifoodValue

            tvSeeRegisterTotalValue.text = "R$" + (pixText + cashText + debitText + creditText + ifoodText).toString()

            //var pixText = BigDecimal(toQuerySartDate, toQueryEndDate)


//            val cashText : String = register.cash.toBrazilianReal()
//            tvSeeRegisterCash.text = "Dinheiro: " + cashText
//
//            val debitText : String = register.debit.toBrazilianReal()
//            tvSeeRegisterDebit.text = "Débito: " + debitText
//
//            val creditText : String = register.credit.toBrazilianReal()
//            tvSeeRegisterCredit.text = "Crédito: " + creditText
//
//            val ifoodText : String = register.ifood.toBrazilianReal()
//            tvSeeRegisterIfood.text = "Ifood: " + ifoodText
        }

        btnSeeRegisterEndDate.setOnClickListener {
            //toQueryEndDate = showDateEndRangePicker()
            showDateEndRangePicker()
        }
    }

    private fun showDateStartRangePicker(){
        //var dateToReturn = ""
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                var correctMonth = mMonth+1
                tvSeeRegiseterStartDate.setText("" + mDay + "/" + (mMonth+1) + "/" + mYear)
                toQuerySartDate = "$mDay/$correctMonth/$mYear"
                //dateToModel = tvDate.text.toString() ;  // where startDate is your TextView
                val simpleDateFormat =  SimpleDateFormat("dd/MM/yyyy", Locale("pt", "br"));  // same date format as your TextView supports
                date1 = simpleDateFormat.parse(toQuerySartDate); // parses the string date to get a date object
            }, year, month, day)
        dpd.show()
    }

    private fun showDateEndRangePicker() {
        //var dateToReturn = ""
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                var correctMonth = mMonth+1
                tvSeeRegisterEndDate.setText("" + mDay + "/" + (mMonth+1) + "/" + mYear)
                toQueryEndDate = "$mDay/$correctMonth/$mYear"
                //dateToModel = tvDate.text.toString() ;  // where startDate is your TextView
                val simpleDateFormat =  SimpleDateFormat("dd/MM/yyyy", Locale("pt", "br"));  // same date format as your TextView supports
                date2 = simpleDateFormat.parse(toQueryEndDate); // parses the string date to get a date object
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