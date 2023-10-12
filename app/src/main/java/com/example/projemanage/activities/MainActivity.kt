package com.example.projemanage.activities

import android.os.Bundle
import com.example.projemanage.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun sum(a:Int,b:Int){
        val result = a+b
        println(result)

    }

//
//    private fun setupActionBar(){
//        setSupportActionBar(binding)
    }
//}