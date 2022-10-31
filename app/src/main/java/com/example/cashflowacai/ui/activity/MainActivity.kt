package com.example.cashflowacai.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.cashflowacai.R
import com.example.cashflowacai.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Financeiro Açaí Predileto"
        buttonsConfig()
    }

    fun buttonsConfig(){
        with(binding) {
            btnForm.setOnClickListener {
                val intent = Intent(this@MainActivity, FormActivity::class.java)
                startActivity(intent)
            }

            btnSeeRegister.setOnClickListener {
                val intent = Intent(this@MainActivity, SeeRegistersActivity::class.java)
                startActivity(intent)
            }
        }
    }
}