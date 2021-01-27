package com.openclassrooms.realestatemanager

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.openclassrooms.realestatemanager.databinding.ActivitySimulatorBinding
import com.openclassrooms.realestatemanager.model.Agent
import com.openclassrooms.realestatemanager.viewModels.AgentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.math.RoundingMode
import kotlin.math.pow

class SimulatorActivity : AppCompatActivity() {

    private var valueAmount: String = ""
    private var valueTime: String = ""
    private var rounded: Double = 0.0
    private var totalPayments: Double = 0.0
    private var roundedTotal: Double = 0.0
    private var monthlyPayment: Double = 0.0
    private var loanAmount: Int = 0
    private var loanPeriod: Int = 0
    private lateinit var binding: ActivitySimulatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_simulator)

        actionOnShareButton()
        actionOnSkAmount()
        actionOnSkTime()
        actionOnBtnResult()


    }

    // initialize and use seekBar
    private fun actionOnSkAmount() {
        binding.seekBarAmount.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {
                binding.tvProgressAmount.text = ("" + progress * 10000 + "" + " " + getString(R.string.dollars))
                valueAmount = (progress * 10000).toString()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    // initialize and use seekBar
    private fun actionOnSkTime() {
        binding.seekBarTime.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {
                binding.tvProgressTime.text = "" + progress + "" + " " + getString(R.string.Years)
                valueTime = progress.toString()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

    }

    // initialize and use Fab
    private fun actionOnShareButton() {
        binding.fabShare.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            val shareBody = ("" + rounded + " " + getString(R.string.tv_result)
                    + "\n" + getString(R.string.tv_total_payments) + " " + roundedTotal + " " + getString(R.string.dollars))
            val shareSubject = getString(R.string.share_subject)
            intent.putExtra(Intent.EXTRA_TEXT, shareBody)
            intent.putExtra(Intent.EXTRA_SUBJECT, shareSubject)
            startActivity(Intent.createChooser(intent, "Share Using"))
        }
    }

    private fun actionOnBtnResult() {
        binding.buttonResult.setOnClickListener {
            showLoanPayments()
        }
    }

    private fun showLoanPayments() {
        val interestRate = binding.editTextPercent.text.toString()

        if (valueAmount.isEmpty()) {
            Toast.makeText(this, getString(R.string.toast_amount), Toast.LENGTH_SHORT).show()
        }
        if (valueTime.isEmpty()) {
            Toast.makeText(this, getString(R.string.toast_period), Toast.LENGTH_SHORT).show()
        }
        if (interestRate.isEmpty()) {
            Toast.makeText(this, getString(R.string.toast_percent), Toast.LENGTH_SHORT).show()
        } else {

            loanAmount = Integer.parseInt(valueAmount)
            loanPeriod = Integer.parseInt(valueTime)
            val r = Integer.parseInt(interestRate)
            val r1 = (r + 1).toDouble().pow(loanPeriod.toDouble())
            monthlyPayment = ((r + (r / (r1 - 1))) * loanAmount) / 1200
            rounded = monthlyPayment.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
            totalPayments = monthlyPayment * loanPeriod
            roundedTotal = totalPayments.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()

            binding.textViewResult.text = ("" + rounded + " " + getString(R.string.tv_result))
            binding.tvTotalResult.text = ("" + roundedTotal + " " + getString(R.string.dollars))
        }


    }

}





