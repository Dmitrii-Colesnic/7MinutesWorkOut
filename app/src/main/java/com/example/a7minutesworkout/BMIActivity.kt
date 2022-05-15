package com.example.a7minutesworkout

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a7minutesworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNITS_VIEW"
        private const val US_UNITS_VIEW = "US_UNITS_VIEW"
    }
    private var currentVisibleView: String = METRIC_UNITS_VIEW

    private var binding: ActivityBmiBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)
/*
        binding?.etMetricUnitHeight?.setOnFocusChangeListener(View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus){
                binding?.etMetricUnitHeight?.setBackgroundDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.edittext_selected_background_border
                    ))
            } else {
                binding?.etMetricUnitHeight?.setBackgroundDrawable(
                    ContextCompat.getDrawable(
                    this,
                    R.drawable.edittext_background_border
                ))
            }
        })
*/

        binding?.flCalculate?.setOnClickListener {
            calculateUnits()
        }

        binding?.rgUnits?.setOnCheckedChangeListener { _, checkedId: Int ->
            when(currentVisibleView){
                (METRIC_UNITS_VIEW)->{makeVisibleUSUnits()}
                (US_UNITS_VIEW)->{makeVisibleMetricUnits()}
            }
        }

    }

    private fun makeVisibleMetricUnits(){
        currentVisibleView = METRIC_UNITS_VIEW

        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.VISIBLE

        binding?.tilMetricUSUnitWeight?.visibility = View.INVISIBLE
        binding?.tilUSFeet?.visibility = View.INVISIBLE
        binding?.tilUSInch?.visibility = View.INVISIBLE

        binding?.etMetricUnitWeight?.text!!.clear()
        binding?.etMetricUnitHeight?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun makeVisibleUSUnits(){
        currentVisibleView = US_UNITS_VIEW

        binding?.tilMetricUnitWeight?.visibility = View.INVISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.INVISIBLE

        binding?.tilMetricUSUnitWeight?.visibility = View.VISIBLE
        binding?.tilUSFeet?.visibility = View.VISIBLE
        binding?.tilUSInch?.visibility = View.VISIBLE

        binding?.etUsUnitWeight?.text!!.clear()
        binding?.etUSFeet?.text!!.clear()
        binding?.etUSInch?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true

        if(binding?.etMetricUnitWeight?.text.toString().isEmpty()){
            isValid = false
        } else if (binding?.etMetricUnitHeight?.text.toString().isEmpty()){
            isValid = false
        }

        return  isValid
    }

    private fun validateUSUnits(): Boolean {
        var isValid = true

        if(binding?.etUsUnitWeight?.text.toString().isEmpty()){
            isValid = false
        } else if (binding?.etUSFeet?.text.toString().isEmpty()){
            isValid = false
        } else if (binding?.etUSInch?.text.toString().isEmpty()){
            isValid = false
        }

        return  isValid
    }

    private fun calculateUnits(){
        when(currentVisibleView){
            (METRIC_UNITS_VIEW) -> {
                if(validateMetricUnits()){
                    val weightValue: Float = binding?.etMetricUnitWeight?.text.toString().toFloat()
                    val heightValue: Float = binding?.etMetricUnitHeight?.text.toString().toFloat() / 100

                    val bmi: Float = weightValue / (heightValue*heightValue)

                    displayBMIValue(bmi)
                }else{
                    Toast.makeText(
                        this@BMIActivity,
                        "Enter Valid Values",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            (US_UNITS_VIEW) -> {
                if(validateUSUnits()){
                    val UsUnitWeight = binding?.etUsUnitWeight?.text.toString().toFloat()
                    val USFeet = binding?.etUSFeet?.text.toString().toFloat()
                    val USInch = binding?.etUSInch?.text.toString().toFloat()

                    //Here the Height feed and Inch value are merged and multiply by 12 for convert
                    val heightValue = USInch + USFeet * 12

                    val bmi = 703 * (UsUnitWeight / (heightValue * heightValue))

                    displayBMIValue(bmi)
                }else{
                    Toast.makeText(
                        this@BMIActivity,
                        "Enter Valid Values",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun displayBMIValue(bmi: Float){

        val bmiLabel: String
        val bmiDescription: String

        when{
            bmi.compareTo(15f) <= 0 -> {
                bmiLabel = "Very severely underweight"
                bmiDescription = "Oops! You really need to take care of yourself! Eat more"
            }

            bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0 -> {
                bmiLabel = "Severely underweight"
                bmiDescription = "Oops! You really need to take care of yourself! Eat more"
            }

            bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0 -> {
                bmiLabel = "Underweight"
                bmiDescription = "Oops! You really need to take care of yourself! Eat more"
            }

            bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0 -> {
                bmiLabel = "Normal"
                bmiDescription = "Congratulations! You are in good shape!"
            }

            bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0 -> {
                bmiLabel = "Overweight"
                bmiDescription = "Oops! You really need to take care of yourself! Workout maybe!"
            }

            bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0 -> {
                bmiLabel = "Obese Class | (Moderately obese)"
                bmiDescription = "Oops! You really need to take care of yourself! Workout maybe!"
            }

            bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0 -> {
                bmiLabel = "Obese Class || (Severely obese)"
                bmiDescription = "Oops! You really need to take care of yourself! Workout maybe!"
            }

            else -> {
                bmiLabel = "Obese Class ||| (Very severely obese)"
                bmiDescription = "Oops! You really need to take care of yourself! Workout maybe!"
            }
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription
    }

}

