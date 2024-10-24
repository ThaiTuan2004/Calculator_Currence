package com.example.calculator_currency

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var text1: EditText
    lateinit var text2: EditText
    lateinit var selected1: String
    lateinit var selected2: String
    lateinit var testtext: TextView
    val exchangeRates = mapOf(
        "USD" to 1.0,
        "EUR" to 0.85,
        "VND" to 24000.0,
        "JPY" to 110.0
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val spiner1: Spinner = findViewById(R.id.Spinner1)
        val spiner2: Spinner = findViewById(R.id.Spinner2)

        val moneylist = listOf("USD", "EUR", "VND", "JPY")

        val adapter = Money_Adapter(moneylist)

        spiner1.adapter = adapter
        spiner2.adapter = adapter

        text1 = findViewById(R.id.inputEditText)
        text2 = findViewById(R.id.inputEditText1)


        spiner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
                selected1 = p0.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        spiner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
                selected2 = p0.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        text1.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                val result=convertCurrency(text1.text.toString().toDouble(),selected1,selected2)
                text2.setText("$result")
                true
            } else {
                false
            }
        }
        text2.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                val result=convertCurrency(text2.text.toString().toDouble(),selected2,selected1)
                text1.setText("$result")
                true
            } else {
                false
            }
        }

    }


    fun convertCurrency(amount: Double, fromCurrency: String, toCurrency: String): Double {
        val fromRate = exchangeRates[fromCurrency] ?: 1.0
        val toRate = exchangeRates[toCurrency] ?: 1.0
        return (amount / fromRate) * toRate
    }
}