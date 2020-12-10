package com.openclassrooms.realestatemanager.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseUser
import com.openclassrooms.realestatemanager.MainActivity
import com.openclassrooms.realestatemanager.R
import com.openclassrooms.realestatemanager.databinding.ActivityAuthBinding
import com.openclassrooms.realestatemanager.utils.Utils
import com.openclassrooms.realestatemanager.utils.UtilsKotlin
import org.koin.androidx.viewmodel.ext.android.viewModel


class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val mLoginViewModel by viewModel<LoginViewModel>()
    private var name: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth)

        //registerAgent()
        checkAvailableNetwork()
        userAction()

    }

    private fun userAction() {
        binding.buttonSignIn.setOnClickListener { registerNewAgent() }

        binding.textViewAlready.setOnClickListener { launchLoginActivity() }
    }

    private fun registerNewAgent() {
        binding.progressBarAuth.visibility = View.VISIBLE

        name = binding.etAuthName.text.toString()
        val mail = binding.etAuthMail.text.toString()
        val passWord = binding.etAuthPassword.text.toString()

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(applicationContext, "please enter name", Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(mail)) {
            Toast.makeText(applicationContext, "please enter email", Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(passWord)) {
            Toast.makeText(applicationContext, "please enter password", Toast.LENGTH_SHORT).show()
            return
        }
        mLoginViewModel.register(mail, passWord)

    }

    // ---------------------------------
    // ----- Configuring Observers -----
    // ---------------------------------
    private fun checkAvailableNetwork() {
        if (UtilsKotlin.verifyAvailableNetwork(this) || (Utils.isInternetAvailable(this))) {
            Toast.makeText(this, "connected to network", Toast.LENGTH_SHORT).show()
            registerAgent()

        } else {
            Toast.makeText(this, "No Connection", Toast.LENGTH_SHORT).show()
            launchMainActivity()
        }
    }

    private fun registerAgent() {
        mLoginViewModel.getUserLiveData()?.observe(this, Observer<FirebaseUser> { fireBaseUser ->
            if (fireBaseUser != null) {
                createAgent()
                launchMainActivity()
            }
        })
    }

    private fun createAgent() {
        mLoginViewModel.createAgent(binding.etAuthName.text.toString(), binding.etAuthMail.text.toString())
    }

    // -------------------------------------------------------
    // ----------------- Launch activities -----------------
    // -------------------------------------------------------
    private fun launchMainActivity() {
        startActivity((Intent(this@AuthActivity, MainActivity::class.java)))
        finish()
    }

    private fun launchLoginActivity() {
        startActivity((Intent(this@AuthActivity, LoginActivity::class.java)))
        finish()

    }


}
