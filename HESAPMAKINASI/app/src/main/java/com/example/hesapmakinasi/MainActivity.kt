package com.example.hesapmakinasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hesapmakinasi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculator.setOnClickListener {
            val fragment = CalculatorFragment()
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
        }

        binding.buttonChronometer.setOnClickListener {
            val fragment = ChronometerFragment()
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
        }
    }
}


