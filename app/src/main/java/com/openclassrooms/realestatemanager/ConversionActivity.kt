package com.openclassrooms.realestatemanager

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseUser
import com.openclassrooms.realestatemanager.auth.LoginViewModel
import com.openclassrooms.realestatemanager.databinding.ActivityConversionBinding
import com.openclassrooms.realestatemanager.model.Agent
import com.openclassrooms.realestatemanager.utils.Utils
import com.openclassrooms.realestatemanager.viewModels.AgentViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ConversionActivity : AppCompatActivity() {
    private lateinit var amount: String
    private var result: Int? = null
    private lateinit var binding: ActivityConversionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_conversion)

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


}
