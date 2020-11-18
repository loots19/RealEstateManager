package com.openclassrooms.realestatemanager

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.chip.Chip
import java.util.*

class AddProperty : AppCompatActivity() {
    @BindView(R.id.spinner_type_create)
    lateinit var spType: Spinner
    @BindView(R.id.chip_market_create)
    lateinit var chMarket: Chip
    @BindView(R.id.CheckBox)
    lateinit var cBox: CheckBox
    @BindView(R.id.IViewCalendar)
    lateinit var ivCalendar: ImageView
    @BindView(R.id.tv_date_create)
    lateinit var tvDate: TextView


    private var c = Calendar.getInstance()
    private var year = c.get(Calendar.YEAR)
    private var month = c.get(Calendar.MONTH)+1
    private var day = c.get(Calendar.DAY_OF_MONTH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_property)
        ButterKnife.bind(this)

        actionOnCBox()
        selectDateOfEntry()


        val adapterSpinner: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spType.adapter = adapterSpinner
    }

    // Action of User

    private fun actionOnCBox() {
        ivCalendar.visibility = View.GONE
        cBox.setOnClickListener{
            if (cBox.isChecked) {
                ivCalendar.visibility = View.VISIBLE
            } else {
                ivCalendar.visibility = View.GONE
            }
        }
        selectDateOfSale()
    }

    private fun selectDateOfSale() {
        ivCalendar.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                Log.e("Date", "$dayOfMonth/$monthOfYear/$year")
            }, year, month, day)
            dpd.show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun selectDateOfEntry() {
        tvDate.setOnClickListener{
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                tvDate.text = "$dayOfMonth/$monthOfYear/$year"
            }, year, month, day)
            dpd.show()
        }
    }

}
