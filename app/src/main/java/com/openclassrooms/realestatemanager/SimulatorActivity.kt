package com.openclassrooms.realestatemanager

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulator)

        actionOnShareButton()
        actionOnSkAmount()
        actionOnSkTime()
        actionOnBtnResult()

    }

    // initialize and use seekBar
    private fun actionOnSkAmount() {
        val sk = findViewById<SeekBar>(R.id.seekBarAmount)

        sk.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {

                val textViewAmount = findViewById<TextView>(R.id.tvProgressAmount)
                textViewAmount.text = ("" + progress * 1000 + "" + " " + getString(R.string.dollars))
                valueAmount = (progress * 1000).toString()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    // initialize and use seekBar
    private fun actionOnSkTime() {
        val skT = findViewById<SeekBar>(R.id.seekBarTime)

        skT.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {
                val textViewTime = findViewById<TextView>(R.id.tvProgressTime)
                textViewTime.text = "" + progress + "" + " " + getString(R.string.Years)
                valueTime = progress.toString()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

    }

    // initialize and use Fab
    private fun actionOnShareButton() {
        val fb = findViewById<FloatingActionButton>(R.id.fab_share)
        fb.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            val shareBody = ("" + rounded + " " + getString(R.string.tv_result))
            val shareSubject = "your subject here"
            intent.putExtra(Intent.EXTRA_TEXT, shareBody)
            intent.putExtra(Intent.EXTRA_SUBJECT, shareSubject)
            startActivity(Intent.createChooser(intent, "Share Using"))
        }
    }

    private fun actionOnBtnResult() {
        val btnResult = findViewById<Button>(R.id.buttonResult)
        btnResult.setOnClickListener {
            showLoanPayments()
        }
    }

    private fun showLoanPayments() {
        val etInterest = findViewById<EditText>(R.id.editTextPercent)
        val interestRate = etInterest.text.toString()
        val tvResult = findViewById<TextView>(R.id.textViewResult)
        val tvTotal = findViewById<TextView>(R.id.tvTotalResult)

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

            tvResult.text = ("" + rounded + " " + getString(R.string.tv_result))
            tvTotal.text = ("" + roundedTotal + " " + getString(R.string.dollars))
        }


    }
}





