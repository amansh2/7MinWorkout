package com.example.a7minworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.example.a7minworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent= Intent(this,ExerciseActivity::class.java)
        binding.frameLayout.setOnClickListener{
            startActivity(intent)
        }
        binding.frameLayout2.setOnClickListener {
            startActivity(Intent(this,BmiActivity::class.java))
        }
        binding.frameLayout3.setOnClickListener {
            startActivity(Intent(this,HistoryActivity::class.java))
        }
    }
}