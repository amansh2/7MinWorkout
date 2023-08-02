package com.example.a7minworkout

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.a7minworkout.databinding.ActivityBmiBinding
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode

class BmiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBmiBinding
    private var metric: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding?.toolbarBmiActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.apply {
            lifecycleScope.launch {
                radioGroup.setOnCheckedChangeListener { _, id: Int ->
                    if (id == R.id.metric_units) {
                        metric = true
                        setMetricLayout()
                    } else {
                        metric = false
                        setUSLayout()
                    }
                }
                calculate.setOnClickListener {
                    try {
                        if (metric) {
                            calculateBMI(
                                weight.text.toString().toFloat(),
                                height.text.toString().toFloat() / 100
                            )
                        } else {
                            var ft = 0f
                            var inches = 0f
                            if (heightFtText.text.toString().isNotEmpty()) {
                                ft = heightFtText.text.toString().toFloat()

                            }
                            if (heightInchesText.text.toString().isNotEmpty()) {
                                inches = heightInchesText.text.toString().toFloat()
                            }
                            calculateBMI(
                                weight.text.toString().toFloat() * 0.453592f,
                                (ft * 30 + inches * 2.5).toFloat() / 100
                            )
                        }
                    } catch (e: Exception) {
                        Toast.makeText(
                            this@BmiActivity,
                            "Error " + e.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }


    }

    private fun setMetricLayout() {
        binding.apply {
            heightFt.visibility = View.INVISIBLE
            heightInches.visibility = View.INVISIBLE
            llDiplayBMIResult.visibility = View.INVISIBLE
            tilMetricUnitHeight.visibility = View.VISIBLE
            heightFtText.text?.clear()
            heightInchesText.text?.clear()
            weight.text?.clear()
            tilMetricUnitWeight.hint = "WEIGHT (in kg)"
        }
    }

    private fun setUSLayout() {
        binding.apply {
            tilMetricUnitHeight.visibility = View.INVISIBLE
            llDiplayBMIResult.visibility=View.INVISIBLE
            heightFt.visibility = View.VISIBLE
            heightInches.visibility = View.VISIBLE
            height.text?.clear()
            weight.text?.clear()
            tilMetricUnitWeight.hint="WEIGHT (in pounds)"
        }
    }

    private fun calculateBMI(weight: Float, height: Float) {
        var BMI = weight / (height * height)
        val value = BigDecimal(BMI.toDouble()).setScale(2, RoundingMode.HALF_EVEN)
        binding.apply {
            bmiValue.text = value.toString()
            if (BMI < 18.50) {
                bmiType.text = "UNDERWEIGHT"
                bmiDescription.text =
                    "Oops! You really need to take better care of yourself! Eat more!"
            } else if (BMI >= 18.5 && BMI <= 24.9) {
                bmiType.text = "HEALTHY"
                bmiDescription.text = "Congratulations! You are in a good shape!"
            } else if (BMI > 24.9 && BMI <= 29.9) {
                bmiType.text = "OVER WEIGHT"
                bmiDescription.text = "Oops! You need to take care of your yourself! Workout maybe!"
            } else {
                bmiType.text = "OBESE"
                bmiDescription.text =
                    "Oops! You really need to take care of your yourself! You have to Workout!"
            }
            llDiplayBMIResult.visibility = View.VISIBLE
        }


    }
}