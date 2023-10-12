package com.example.projemanage.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.compose.material3.Snackbar
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.projemanage.R
import com.example.projemanage.databinding.ActivityBaseBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    private var doubleBackToExitPressedOnce = false

    private lateinit var mProgressDialog : Dialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


//    fun showProgressDialogue(text:String){
//        mProgressDialog = Dialog(this)
//        binding = DataBindingUtil.inflate<>(layoutInflater,R.drawable.)
//
//        mProgressDialog.setContentView(binding.root)
//        binding.tv_progress_text.text = text
//        //start the dialog and display it on the screen
//        mProgressDialog.show()
//    }

    fun getCurrentUserID():String{
        return FirebaseAuth.getInstance().currentUser!!.uid
    }

    fun doubleBackToExit(){
        if (doubleBackToExitPressedOnce){
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true

        Toast.makeText(
            this,
            resources.getString(R.string.please_click_back_again_to_exit),
            Toast.LENGTH_SHORT
        ).show()

        Handler().postDelayed({doubleBackToExitPressedOnce = false},2000)
    }

    fun ShowErrorSnackBar(message:String){
        val snackBar = Snackbar.make(findViewById(android.R.id.content),message, Snackbar.LENGTH_LONG
        )
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(this,R.color.snackbar_error_color))
        snackBar.show()
    }
}