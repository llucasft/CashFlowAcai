package com.example.cashflowacai.ui.activity

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.cashflowacai.R
import com.example.cashflowacai.database.AppDataBase
import com.example.cashflowacai.databinding.ActivitySeeRegistersBinding
import com.example.cashflowacai.extensions.toBrazilianReal
import com.example.cashflowacai.model.Register
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class SeeRegistersActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeeRegistersBinding
    var toQuerySartDate = ""
    var toQueryEndDate = ""
    var date1: Date? = null
    var date2: Date? = null
    private val calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)

    private var pixValue: String = "0"
//    private var cashValue: String = "0"
//    private var debitValue: String = "0"
//    private var creditValue: String = "0"
//    private var ifoodValue: String = "0"
//    private var totalValue: String = "0"

    // Creating database instance in class
    private val registerDao by lazy {
        val db = AppDataBase.instance(this)
        db.registerDao()
    }

    private var registerId = 0L

    private val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeRegistersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Consultar vendas"

        //val day = dateUpdateInView()

        //register = registerDao.selectFromDbByDate(day)

        // Seting date period selector
        binding.btnSeeRegisterStartDate.setOnClickListener {
            //toQuerySartDate = showDateStartRangePicker()
            showDateStartRangePicker()
        }

        binding.btnSeeRegisterEndDate.setOnClickListener {
            //toQueryEndDate = showDateEndRangePicker()
            showDateEndRangePicker()
        }

        with(binding) {
            btnQuery.setOnClickListener {
                scope.launch {
                    val pixText = withContext(Dispatchers.IO) {
                        registerDao.selectPixFromDbByDate(date1, date2)
                    }
                    val pixValue = pixText.toBrazilianReal()

                    val cashText = withContext(IO) {
                        registerDao.selectCashFromDbByDate(date1, date2)
                    }
                    val cashValue = cashText.toBrazilianReal()

                    val debitText = withContext(IO) {
                        registerDao.selectDebitFromDbByDate(date1, date2)
                    }
                    val debitValue = debitText.toBrazilianReal()

                    val creditText = withContext(IO) {
                        registerDao.selectCreditFromDbByDate(date1, date2)
                    }
                    val creditValue = creditText.toBrazilianReal()

                    val ifoodText = withContext(IO) {
                        registerDao.selectIfoodFromDbByDate(date1, date2)
                    }
                    val ifoodValue = ifoodText.toBrazilianReal()

                    val totalValue =
                        "R$" + (pixText + cashText + debitText + creditText + ifoodText).toString()

                    tvSeeRegisterPix.text = pixValue
                    tvSeeRegisterCash.text = cashValue
                    tvSeeRegisterDebit.text = debitValue
                    tvSeeRegisterCredit.text = creditValue
                    tvSeeRegisterIfood.text = ifoodValue
                    tvSeeRegisterTotalValue.text = totalValue
                }
            }
        }
    }

    private fun showDateStartRangePicker() {
        val dpd = DatePickerDialog(
            this@SeeRegistersActivity,
            DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                val correctMonth = mMonth + 1
                binding.tvSeeRegisterStartDate.setText("" + mDay + "/" + (mMonth + 1) + "/" + mYear)
                toQuerySartDate = "$mDay/$correctMonth/$mYear"
                //dateToModel = tvDate.text.toString() ;  // where startDate is your TextView
                val simpleDateFormat = SimpleDateFormat(
                    "dd/MM/yyyy",
                    Locale("pt", "br")
                );  // same date format as your TextView supports
                date1 =
                    simpleDateFormat.parse(toQuerySartDate); // parses the string date to get a date object
            }, year, month, day
        )
        dpd.show()
    }

    private fun showDateEndRangePicker() {
        val dpd = DatePickerDialog(
            this@SeeRegistersActivity,
            DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                val correctMonth = mMonth + 1
                binding.tvSeeRegisterEndDate.setText("" + mDay + "/" + (mMonth + 1) + "/" + mYear)
                toQueryEndDate = "$mDay/$correctMonth/$mYear"
                //dateToModel = tvDate.text.toString() ;  // where startDate is your TextView
                val simpleDateFormat = SimpleDateFormat(
                    "dd/MM/yyyy",
                    Locale("pt", "br")
                );  // same date format as your TextView supports
                date2 =
                    simpleDateFormat.parse(toQueryEndDate); // parses the string date to get a date object
            }, year, month, day
        )
        dpd.show()
    }
}