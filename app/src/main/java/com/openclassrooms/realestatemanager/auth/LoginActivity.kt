package com.openclassrooms.realestatemanager.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import butterknife.BindView
import butterknife.ButterKnife
import com.openclassrooms.realestatemanager.MainActivity
import com.openclassrooms.realestatemanager.R
import com.openclassrooms.realestatemanager.repositories.Injection

class LoginActivity : AppCompatActivity() {

    @BindView(R.id.et_login_name)
    lateinit var etName : TextView
    @BindView(R.id.et_login_passWord)
    lateinit var etPassWord : TextView
    @BindView(R.id.buttonSignInLogin)
    lateinit var button: Button
    @BindView(R.id.progressBarLogin)
    lateinit var progressBar: ProgressBar

    private  var mLoginViewModel: LoginViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)

        configureViewModel()
        registerAgent()
        userAction()

    }

    private fun userAction(){
        button.setOnClickListener { loginAgent() }
    }



    private fun loginAgent() {
        progressBar.visibility = View.VISIBLE

        val name = etName.text.toString()
        val passWord = etPassWord.text.toString()

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(applicationContext, "please enter name", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(passWord)) {
            Toast.makeText(applicationContext, "please enter password", Toast.LENGTH_SHORT).show()

        }
        mLoginViewModel?.logIn(name, passWord)



    }
    // ---------------------------------------------------------
    // ----------------- Configuring ViewModel -----------------
    // ---------------------------------------------------------
    private fun configureViewModel() {
        val factory = Injection.providesViewModelFactory(this.applicationContext)
        mLoginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

    }

    // ---------------------------------
    // ----- Configuring Observers -----
    // ---------------------------------
    private fun registerAgent() {
        mLoginViewModel?.getUserLiveData()?.observe(this, Observer {
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
