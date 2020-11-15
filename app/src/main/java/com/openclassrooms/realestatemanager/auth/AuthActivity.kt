package com.openclassrooms.realestatemanager.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import butterknife.BindView
import butterknife.ButterKnife
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.openclassrooms.realestatemanager.MainActivity
import com.openclassrooms.realestatemanager.R
import com.openclassrooms.realestatemanager.repositories.Injection

class AuthActivity : AppCompatActivity() {
    // for design
    @BindView(R.id.buttonSignIn)
    lateinit var btnRegister: Button
    @BindView(R.id.textViewAlready)
    lateinit var tvAlready: TextView
    @BindView(R.id.progressBarAuth)
    lateinit var progressBar: ProgressBar
    @BindView(R.id.et_auth_name)
    lateinit var nameRegister: EditText
    @BindView(R.id.et_auth_mail)
    lateinit var mailRegister: EditText
    @BindView(R.id.et_auth_password)
    lateinit var passWordRegister: EditText


    private  var mLoginViewModel: LoginViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        ButterKnife.bind(this)

        configureViewModel()
        registerAgent()


        btnRegister.setOnClickListener { registerNewAgent() }

        tvAlready.setOnClickListener { launchLoginActivity() }
    }

    private fun registerNewAgent() {
        progressBar.visibility = View.VISIBLE

        val name = nameRegister.text.toString()
        val mail = mailRegister.text.toString()
        val passWord = passWordRegister.text.toString()

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

        }
        mLoginViewModel?.register(mail, passWord)



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
        mLoginViewModel?.getUserLiveData()?.observe(this, Observer<FirebaseUser> { fireBaseUser ->
            if (fireBaseUser != null) {
                createAgent()
                launchMainActivity()

                Log.e("connect", "true")
            }
        })

    }

    private fun createAgent() {
        mLoginViewModel?.createWorkmate(nameRegister.text.toString(), mailRegister.text.toString())
    }

    // -------------------------------------------------------
    // ----------------- Launch main activity -----------------
    // -------------------------------------------------------
    private fun launchMainActivity() {
        startActivity((Intent(this@AuthActivity, MainActivity::class.java)))
        finish()
    }

    // ---------------------------------------------------------
    // ----------------- Launch LogIn activity -----------------
    // ---------------------------------------------------------
    private fun launchLoginActivity() {
        startActivity((Intent(this@AuthActivity, LoginActivity::class.java)))
        finish()

    }






}
