package com.example.projemanage.activities

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import com.example.projemanage.R
import com.example.projemanage.databinding.ActivitySignInBinding
import com.example.projemanage.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log



class SignInActivity : BaseActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivitySignInBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding.btnSignIn.setOnClickListener{
            signInRegisteredUser()
        }
        setupActionBar()
    }

    fun signInSucces (user : User){
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    @SuppressLint("RestrictedApi")
    private fun setupActionBar(){
        setSupportActionBar(binding.toolbarSignInActivity)


        val actionBar = supportActionBar

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }

        binding.toolbarSignInActivity.setNavigationOnClickListener{onBackPressed()}
    }

    private fun signInRegisteredUser(){
        val email : String = binding.etEmailSignIn.text.toString().trim{ it <= ' '}
        val password : String = binding.etPasswordSignIn.text.toString().trim{ it <= ' '}

        if(validateForm(email,password)){
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                task ->
                if (task.isSuccessful){
//                    Log.d("Sign in","signInWithEmail:succes")
                    val user = auth.currentUser
                    startActivity(Intent(this,MainActivity::class.java))
                }else{
//                    Log.w(
//                        "Sign in",
//                        "signInWithEmail:failure",
//                        task.exception
//                    )
                    Toast.makeText(baseContext,"Authentication failed.",Toast.LENGTH_SHORT).show()

                }
            }

        }
    }

    private fun validateForm(email:String,password:String): Boolean {
        return when{

            TextUtils.isEmpty(email) -> {
                ShowErrorSnackBar("Please enter an email address")
                false
            }
            TextUtils.isEmpty(password) -> {
                ShowErrorSnackBar("Please enter a password")
                false
            }else -> {
                true
            }
        }
    }
}