package com.openclassrooms.realestatemanager.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.openclassrooms.realestatemanager.AdapterList
import com.openclassrooms.realestatemanager.databinding.FragmentListBinding
import com.openclassrooms.realestatemanager.detailActivity.DetailActivity
import com.openclassrooms.realestatemanager.model.Property
import com.openclassrooms.realestatemanager.viewModels.PropertyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    companion object {
        fun newInstance(): ListFragment = ListFragment()
    }

    private val mPropertyViewModel by viewModel<PropertyViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, com.openclassrooms.realestatemanager.R.layout.fragment_list, container, false)
        initialization()
        getProperty()
        return binding.root
    }

    private val adapterList = AdapterList { item ->
        val intent = Intent(requireContext(), DetailActivity::class.java)
        val jsonSelectedProperty = Gson().toJson(item)
        intent.putExtra(DetailActivity.EXTRA_PROPERTY, jsonSelectedProperty)
        this.startActivity(intent)
    }


    @SuppressLint("WrongConstant")
    private fun getProperty() {
        mPropertyViewModel.getAllProperty().observe(this, Observer<List<Property>> {
            if (it != null) {
                adapterList.setProperties(it)
            }
        })
    }

    private fun initialization() {
        binding.rvList.adapter = adapterList
        binding.rvList.layoutManager = LinearLayoutManager(this.activity)

    }


}


