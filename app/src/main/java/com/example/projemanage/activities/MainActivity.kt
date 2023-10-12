package com.example.projemanage.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projemanage.R
import com.example.projemanage.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
//
//    private fun setupActionBar(){
//        setSupportActionBar(binding)
    }
//}