package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding
import kotlin.concurrent.timer

class ExerciseActivity : AppCompatActivity() {

    private var binding: ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)

        if(supportActionBar != null){
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }

        setUpRestView()

    }

    private fun setUpRestView(){
        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }

        setRestProgressBar()
    }

    private fun setRestProgressBar(){
        binding?.progressbar?.progress = restProgress

        restTimer = object : CountDownTimer(10000, 1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.tvTimer?.text = (10 - restProgress).toString()
                binding?.progressbar?.progress = 10 - restProgress
            }

            override fun onFinish() {
                Toast.makeText(
                    this@ExerciseActivity,
                    "",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

        }.start()

    }

    private fun startExercise(){
        isExerciseTime = false
        timerDuration = 30000
//        restProgress = 0
        binding?.progressbar?.max = (timerDuration/1000).toInt()
        binding?.tvTimer?.text = (timerDuration/1000).toString()
        toastText = "exercise end"
        binding?.tvTitle?.text = "GET READY FOR"
    }

    private fun startRest(){
        isExerciseTime = true
        timerDuration = 10000
//        restProgress = 0
        binding?.progressbar?.max = (timerDuration/1000).toInt()
        binding?.tvTimer?.text = (timerDuration/1000).toString()
        toastText = "rest end"
        binding?.tvTitle?.text = "EXERCISE NAME"
    }

    override fun onDestroy() {
        super.onDestroy()

        if(countDownTimer != null){
            countDownTimer?.cancel()
            timerDuration = 0
        }

        binding = null
    }

}