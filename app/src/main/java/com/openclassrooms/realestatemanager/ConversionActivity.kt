package com.openclassrooms.realestatemanager

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.openclassrooms.realestatemanager.databinding.ActivityConversionBinding
import com.openclassrooms.realestatemanager.detailActivity.DetailActivity.Companion.PREF_PRICE
import com.openclassrooms.realestatemanager.utils.Utils


class ConversionActivity : AppCompatActivity() {
    private lateinit var amount: String
    private var result: Int? = null
    private lateinit var binding: ActivityConversionBinding
    private var price: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_conversion)

        loadPref()
        actionOnBtnDollar()
        actionOnButtonEur()
    }

    @SuppressLint("SetTextI18n")
    private fun convertDollarEur() {
        amount = binding.etAmountConversion.text.toString()
        if (amount.isEmpty()) {
            Toast.makeText(this, getString(R.string.toast_amount), Toast.LENGTH_SHORT).show()
        } else {
            result = Utils.convertDollarToEuro(Integer.parseInt(amount))
            binding.tvResultConversion.text = result.toString() + " " + getString(R.string.euro)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun convertEurDollar() {
        amount = binding.etAmountConversion.text.toString()
        if (amount.isEmpty()) {
            Toast.makeText(this, getString(R.string.toast_amount), Toast.LENGTH_SHORT).show()
        } else {
            result = Utils.convertEuroToDollars(Integer.parseInt(amount))
            binding.tvResultConversion.text = result.toString() + " " + getString(R.string.dollars)
        }
    }

    private fun actionOnBtnDollar() {
        binding.btnDollarsEuro.setOnClickListener {
            convertDollarEur()
        }
    }

    private fun actionOnButtonEur() {
        binding.btnEuroDollar.setOnClickListener {
            convertEurDollar()
        }
    }

    private fun loadPref() {
        val sharedPreferences = getSharedPreferences(PREF_PRICE, Context.MODE_PRIVATE)
        price = sharedPreferences.getString("price", "")
        binding.etAmountConversion.setText(price!!)


    }


}
