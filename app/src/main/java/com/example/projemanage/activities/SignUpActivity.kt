package com.example.projemanage.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import com.example.projemanage.R
import com.example.projemanage.databinding.ActivitySignUpBinding
import com.example.projemanage.firebase.FirestoreClass
import com.example.projemanage.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpActivity : BaseActivity() {

    private lateinit var binding:ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setupActionBar()
    }


    fun userRegisteredSuccess(){
        Toast.makeText(this,"You have succesfully" +
                "registered.",Toast.LENGTH_LONG).show()
        FirebaseAuth.getInstance().signOut()
        finish()
    }

    @SuppressLint("RestrictedApi")
    private fun setupActionBar(){
        setSupportActionBar(binding.toolbarSignUpActivity)

        val actionBar = supportActionBar

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }

        binding.toolbarSignUpActivity.setNavigationOnClickListener{onBackPressed()}

        binding.btnSignUp.setOnClickListener{
          RegisterUser()
        }
    }


    private fun RegisterUser(){
        val name:String = binding.etName.text.toString().trim{ it <= ' '}
        val email:String = binding.etEmail.text.toString().trim{ it <= ' '}
        val password:String = binding.etPassword.text.toString().trim{ it <= ' '}
        showProgressDialogue("Hello")
        if (validateForm(name,email,password)){

           FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
               task ->

               if (task.isSuccessful){
                   val firebaseUser : FirebaseUser = task.result!!.user!!
                   val registeredEmail = firebaseUser.email!!
                   val user = User(firebaseUser.uid,name,registeredEmail)
                   FirestoreClass().registerUser(this,user)
                   Toast.makeText(this,"$name you have succesfully +" +
                           "registered the email address $registeredEmail",Toast.LENGTH_LONG).show()

               }else{
                   Toast.makeText(this,
                      "Registration failed",Toast.LENGTH_SHORT).show()
               }
           }
        }
    }


    private fun validateForm(name:String,email:String,password:String): Boolean {
        return when{
            TextUtils.isEmpty(name) -> {
                ShowErrorSnackBar("Please enter a name")
                false
            }
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

