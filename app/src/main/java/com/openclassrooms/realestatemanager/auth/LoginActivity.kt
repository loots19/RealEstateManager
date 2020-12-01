package com.openclassrooms.realestatemanager.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.openclassrooms.realestatemanager.MainActivity
import com.openclassrooms.realestatemanager.R
import com.openclassrooms.realestatemanager.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var name: String? = ""
    private var password: String? = ""


    private val mLoginViewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        registerAgent()
        userAction()

    }

    private fun userAction() {
        binding.buttonSignInLogin.setOnClickListener { loginAgent() }
    }


    private fun loginAgent() {
        binding.progressBarLogin.visibility = View.VISIBLE

        name = binding.etLoginName.text.toString()
        password = binding.etLoginPassWord.text.toString()

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(applicationContext, "please enter name", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "please enter password", Toast.LENGTH_SHORT).show()

        }
        mLoginViewModel.logIn(name!!, password!!)

    }


    // ---------------------------------
    // ----- Configuring Observers -----
    // ---------------------------------
    private fun registerAgent() {
        mLoginViewModel.getUserLiveData()?.observe(this, Observer {
            launchMainActivity()
        })

    }

    // -------------------------------------------------------
    // ----------------- Launch main activity -----------------
    // -------------------------------------------------------
    private fun launchMainActivity() {
        startActivity((Intent(this@LoginActivity, MainActivity::class.java)))
        finish()
    }
}
