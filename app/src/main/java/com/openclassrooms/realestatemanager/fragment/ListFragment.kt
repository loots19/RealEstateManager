package com.openclassrooms.realestatemanager.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import org.antlr.runtime.misc.IntArray




class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding


    private val mPropertyViewModel by viewModel<PropertyViewModel>()

     private val adapterList = AdapterList{item->
         val intent = Intent(requireContext(),DetailActivity::class.java)
         val jsonSelectedRestaurant = Gson().toJson(item)
         intent.putExtra(DetailActivity.EXTRA_PROPERTY, jsonSelectedRestaurant)
         this.startActivity(intent)
     }




    companion object {
        fun newInstance(): ListFragment = ListFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, com.openclassrooms.realestatemanager.R.layout.fragment_list, container, false)
        initialization()
        getProperty()
        return binding.root
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
        val dividerItemDecoration = DividerItemDecoration(binding.rvList.context, DividerItemDecoration.VERTICAL)
        binding.rvList.addItemDecoration(dividerItemDecoration)

    }



}


