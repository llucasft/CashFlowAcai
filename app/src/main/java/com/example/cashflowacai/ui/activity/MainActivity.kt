package com.example.cashflowacai.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.cashflowacai.R

class MainActivity : AppCompatActivity() {

    lateinit var btnForm : Button
    lateinit var btnSeeRegiser : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnForm = findViewById(R.id.btnForm)
        btnSeeRegiser = findViewById(R.id.btnSeeRegister)

        btnForm.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }

        btnSeeRegiser.setOnClickListener {
            val intent = Intent(this, SeeRegistersActivity::class.java)
            startActivity(intent)
        }
    }
}