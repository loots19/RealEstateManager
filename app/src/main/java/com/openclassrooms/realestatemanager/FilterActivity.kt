package com.openclassrooms.realestatemanager

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.openclassrooms.realestatemanager.databinding.ActivityFilterBinding
import com.openclassrooms.realestatemanager.viewModels.PropertyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class FilterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilterBinding
    private var resultHouse: String = ""
    private var resultFlat: String = ""
    private var resultDuplex: String = ""
    private var resultNbrBed1: String = ""
    private var resultNbrBed2: String = ""
    private var resultNbrBed3: String = ""
    private var resultNbrBed4: String = ""
    private var resultNbrBed5: String = ""
    private var resultNbrBath1: String = ""
    private var resultNbrBath2: String = ""
    private var resultNbrBath3: String = ""
    private var resultNbrBath4: String = ""
    private var resultNbrBath5: String = ""
    private var resultSurfaceMin: String = ""
    private var resultSurfaceMax: String = ""
    private var resultCity: String = ""
    private var isColor: Boolean = true
    private var c = Calendar.getInstance()
    private var year = c.get(Calendar.YEAR)
    private var month = c.get(Calendar.MONTH)
    private var day = c.get(Calendar.DAY_OF_MONTH)
    private var city: String = ""


    private val mPropertyViewModel by viewModel<PropertyViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter)
        selectType()
        selectNbrBed()
        selectNbrBath()
        selectEditText()
        selectPoi()
        selectDate()
        actionOnBtnBack()
        actionOnBtnFilter()

    }

    private fun selectType() {
        binding.tvDuplexFilter.setOnClickListener {
            binding.tvDuplexFilter.setBackgroundColor(resources.getColor(R.color.colorAccent))
            binding.tvDuplexFilter.setTextColor(resources.getColor(R.color.quantum_white_text))
            binding.tvHouseFilter.setTextColor(resources.getColor(R.color.quantum_black_text))
            binding.tvFlatFilter.setTextColor(resources.getColor(R.color.quantum_black_text))
            binding.tvFlatFilter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvHouseFilter.setBackgroundResource(R.drawable.btn_filter)
            resultDuplex = binding.tvDuplexFilter.text.toString()
        }
        binding.tvHouseFilter.setOnClickListener {
            binding.tvHouseFilter.setBackgroundColor(resources.getColor(R.color.colorAccent))
            binding.tvHouseFilter.setTextColor(resources.getColor(R.color.quantum_white_text))
            binding.tvDuplexFilter.setTextColor(resources.getColor(R.color.quantum_black_text))
            binding.tvFlatFilter.setTextColor(resources.getColor(R.color.quantum_black_text))
            binding.tvDuplexFilter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvFlatFilter.setBackgroundResource(R.drawable.btn_filter)
            resultHouse = binding.tvHouseFilter.text.toString()
        }
        binding.tvFlatFilter.setOnClickListener {
            binding.tvFlatFilter.setBackgroundColor(resources.getColor(R.color.colorAccent))
            binding.tvFlatFilter.setTextColor(resources.getColor(R.color.quantum_white_text))
            binding.tvDuplexFilter.setTextColor(resources.getColor(R.color.quantum_black_text))
            binding.tvHouseFilter.setTextColor(resources.getColor(R.color.quantum_black_text))
            binding.tvDuplexFilter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvHouseFilter.setBackgroundResource(R.drawable.btn_filter)
            resultFlat = binding.tvFlatFilter.text.toString()

        }
    }

    private fun selectNbrBed() {
        binding.tvBed1Filter.setOnClickListener {
            binding.tvBed1Filter.setBackgroundColor(resources.getColor(R.color.colorAccent))
            binding.tvBed2Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBed3Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBed4Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBed5Filter.setBackgroundResource(R.drawable.btn_filter)
            resultNbrBed1 = binding.tvBed1Filter.text.toString()

        }
        binding.tvBed2Filter.setOnClickListener {
            binding.tvBed2Filter.setBackgroundColor(resources.getColor(R.color.colorAccent))
            binding.tvBed1Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBed3Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBed4Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBed5Filter.setBackgroundResource(R.drawable.btn_filter)
            resultNbrBed2 = binding.tvBed2Filter.text.toString()

        }
        binding.tvBed3Filter.setOnClickListener {
            binding.tvBed3Filter.setBackgroundColor(resources.getColor(R.color.colorAccent))
            binding.tvBed2Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBed1Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBed4Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBed5Filter.setBackgroundResource(R.drawable.btn_filter)
            resultNbrBed3 = binding.tvBed3Filter.text.toString()

        }
        binding.tvBed4Filter.setOnClickListener {
            binding.tvBed4Filter.setBackgroundColor(resources.getColor(R.color.colorAccent))
            binding.tvBed2Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBed3Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBed1Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBed5Filter.setBackgroundResource(R.drawable.btn_filter)
            resultNbrBed4 = binding.tvBed4Filter.text.toString()

        }
        binding.tvBed5Filter.setOnClickListener {
            binding.tvBed5Filter.setBackgroundColor(resources.getColor(R.color.colorAccent))
            binding.tvBed2Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBed3Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBed4Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBed1Filter.setBackgroundResource(R.drawable.btn_filter)
            resultNbrBed5 = binding.tvBed5Filter.text.toString()

        }
    }

    private fun selectNbrBath() {
        binding.tvBath1Filter.setOnClickListener {
            binding.tvBath1Filter.setBackgroundColor(resources.getColor(R.color.colorAccent))
            binding.tvBath2Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBath3Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBath4Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBath5Filter.setBackgroundResource(R.drawable.btn_filter)
            resultNbrBath1 = binding.tvBath1Filter.text.toString()
        }
        binding.tvBath2Filter.setOnClickListener {
            binding.tvBath2Filter.setBackgroundColor(resources.getColor(R.color.colorAccent))
            binding.tvBath1Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBath3Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBath4Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBath5Filter.setBackgroundResource(R.drawable.btn_filter)
            resultNbrBath2 = binding.tvBath2Filter.text.toString()

        }
        binding.tvBath3Filter.setOnClickListener {
            binding.tvBath3Filter.setBackgroundColor(resources.getColor(R.color.colorAccent))
            binding.tvBath2Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBath1Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBath4Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBath5Filter.setBackgroundResource(R.drawable.btn_filter)
            resultNbrBath3 = binding.tvBath3Filter.text.toString()


        }
        binding.tvBath4Filter.setOnClickListener {
            binding.tvBath4Filter.setBackgroundColor(resources.getColor(R.color.colorAccent))
            binding.tvBath2Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBath3Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBath1Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBath5Filter.setBackgroundResource(R.drawable.btn_filter)
            resultNbrBath4 = binding.tvBath4Filter.text.toString()

        }
        binding.tvBath5Filter.setOnClickListener {
            binding.tvBath5Filter.setBackgroundColor(resources.getColor(R.color.colorAccent))
            binding.tvBath2Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBath3Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBath4Filter.setBackgroundResource(R.drawable.btn_filter)
            binding.tvBath1Filter.setBackgroundResource(R.drawable.btn_filter)
            resultNbrBath5 = binding.tvBath5Filter.text.toString()


        }
    }

    private fun selectEditText() {
        resultSurfaceMin = binding.etSquareMinFilter.editText?.text.toString()
        resultSurfaceMax = binding.etSquareMaxFilter.editText?.text.toString()
        resultCity = binding.etCityFilter.editText?.text.toString()
        Log.e("City", resultCity)
    }

    private fun selectPoi() {
        binding.ivTransportFilter.setOnClickListener {
            isColor = if (isColor) {
                binding.ivTransportFilter.setBackgroundColor(resources.getColor(R.color.colorAccent))
                binding.ivTransportFilter.setColorFilter(resources.getColor(R.color.quantum_white_text))
                false

            } else {
                binding.ivTransportFilter.setBackgroundResource(R.drawable.btn_filter)
                binding.ivTransportFilter.setColorFilter(resources.getColor(R.color.quantum_black_text))
                true
            }
        }
        binding.ivHospitalFilter.setOnClickListener {
            isColor = if (isColor) {
                binding.ivHospitalFilter.setBackgroundColor(resources.getColor(R.color.colorAccent))
                binding.ivHospitalFilter.setColorFilter(resources.getColor(R.color.quantum_white_text))
                false

            } else {
                binding.ivHospitalFilter.setBackgroundResource(R.drawable.btn_filter)
                binding.ivHospitalFilter.setColorFilter(resources.getColor(R.color.quantum_black_text))
                true
            }
        }
        binding.ivSchoolFilter.setOnClickListener {
            isColor = if (isColor) {
                binding.ivSchoolFilter.setBackgroundColor(resources.getColor(R.color.colorAccent))
                binding.ivSchoolFilter.setColorFilter(resources.getColor(R.color.quantum_white_text))
                false

            } else {
                binding.ivSchoolFilter.setBackgroundResource(R.drawable.btn_filter)
                binding.ivSchoolFilter.setColorFilter(resources.getColor(R.color.quantum_black_text))
                true
            }
        }
        binding.ivMarketFilter.setOnClickListener {
            isColor = if (isColor) {
                binding.ivMarketFilter.setBackgroundColor(resources.getColor(R.color.colorAccent))
                binding.ivMarketFilter.setColorFilter(resources.getColor(R.color.quantum_white_text))
                false

            } else {
                binding.ivMarketFilter.setBackgroundResource(R.drawable.btn_filter)
                binding.ivMarketFilter.setColorFilter(resources.getColor(R.color.quantum_black_text))
                true
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun selectDate() {
        binding.tvDateMinFilter.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                binding.tvDateMinFilter.text = "$dayOfMonth/$monthOfYear/$year"
            }, year, month, day)
            dpd.show()
        }
        binding.tvDateMaxFilter.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                binding.tvDateMaxFilter.text = "$dayOfMonth/$monthOfYear/$year"
            }, year, month, day)
            dpd.show()
        }
    }

    private fun actionOnBtnBack() {
        binding.imageButtonBack.setOnClickListener {
            finish()
        }
    }

    private fun actionOnBtnFilter() {
        binding.btnFilter.setOnClickListener {
            getAllInfo()
        }
    }

    @SuppressLint("WrongConstant")
    private fun getProperty() {
        mPropertyViewModel.getAllProperty().observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                Log.e("Vm",it.toString())
                val size = it.size
                for (i in 0 until size) {
                    city = it[i].city
                    Log.e("result", city)
                    if(city ==(resultCity)){
                        Log.e("ok","ok")
                    }

                }
            }

        })

    }

    private fun getAllInfo() {
        resultCity = binding.etCityFilter.editText?.text.toString()
        resultNbrBed3 = binding.tvBed3Filter.text.toString()
        resultHouse = binding.tvHouseFilter.text.toString()
        resultDuplex = binding.tvDuplexFilter.text.toString()
        getProperty()
    }

}
