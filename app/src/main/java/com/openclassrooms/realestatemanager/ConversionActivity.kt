package com.openclassrooms.realestatemanager

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.openclassrooms.realestatemanager.utils.Utils


class ConversionActivity : AppCompatActivity() {
    private lateinit var amount: String
    private var result: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversion)
        actionOnBtnDollar()
        actionOnButtonEur()
    }

    @SuppressLint("SetTextI18n")
    private fun convertDollarEur() {
        val etAmount = findViewById<EditText>(R.id.et_amount_conversion)
        val tvResult = findViewById<TextView>(R.id.tv_result_conversion)
        amount = etAmount.text.toString()
        if (amount.isEmpty()) {
            Toast.makeText(this, getString(R.string.toast_amount), Toast.LENGTH_SHORT).show()
        } else {
            result = Utils.convertDollarToEuro(Integer.parseInt(amount))
            tvResult.text = result.toString() + " " + getString(R.string.euro)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun convertEurDollar() {
        val etAmount = findViewById<EditText>(R.id.et_amount_conversion)
        val tvResult = findViewById<TextView>(R.id.tv_result_conversion)
        amount = etAmount.text.toString()
        if (amount.isEmpty()) {
            Toast.makeText(this, getString(R.string.toast_amount), Toast.LENGTH_SHORT).show()
        } else {
            result = Utils.convertEuroToDollars(Integer.parseInt(amount))
            tvResult.text = result.toString() + " " + getString(R.string.dollars)
        }
    }

    private fun actionOnBtnDollar() {
        val btnDollars = findViewById<Button>(R.id.btn_dollars_euro)
        btnDollars.setOnClickListener {
            convertDollarEur()
        }
    }

    private fun actionOnButtonEur() {
        val btnEur = findViewById<Button>(R.id.btn_euro_dollar)
        btnEur.setOnClickListener {
            convertEurDollar()
        }
    }

}
