package com.example.projemanage.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.projemanage.databinding.ActivityIntroBinding

//import kotlinx.android.synthetic.main.activity_intro.btn_sign_in_intro
//import kotlinx.android.synthetic.main.activity_intro.btn_sign_up_intro

class IntroActivity : BaseActivity() {

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding.btnSignInIntro.setOnClickListener{
            startActivity(Intent(this, SignInActivity::class.java))
        }

        binding.btnSignUpIntro.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }


    }
}