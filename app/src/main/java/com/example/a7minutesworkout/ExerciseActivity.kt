package com.example.a7minutesworkout

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer

class ExerciseActivity : AppCompatActivity() {

    private var binding: ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0
    private var restTime = 0
    private var bigRest = 10

    private var exerciseProgress = 1
    private var exerciseAmount = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    private var workoutPlan: Queue<WorkoutPlan>? = null
    private var amountSupersets = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
/*
        Toolbar

        setSupportActionBar(binding?.toolbarExercise)

        if(supportActionBar != null){
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }
*/
        exerciseList = Constants.defaultExerciseList()
        workoutPlan = Constants.defineWorkoutPlan()

        setUpExerciseView()

    }

    private fun setUpRestView(){
        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE

        binding?.ivExercise?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.tvAmountReps?.visibility = View.INVISIBLE

        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }

        setRestProgressBar()
    }

    private fun setRestProgressBar(){
        binding?.progressbar?.progress = restProgress
        binding?.progressbar?.max = restTime

        restTimer = object : CountDownTimer((restTime * 1000).toLong(), 1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.tvTimer?.text = (restTime - restProgress).toString()
                binding?.progressbar?.progress = restTime - restProgress
            }

            override fun onFinish() {
                setUpExerciseView()
            }
        }.start()

    }

    private fun setUpExerciseView(){
        binding?.flRestView?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE

        binding?.ivExercise?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.tvAmountReps?.visibility = View.VISIBLE

        workoutPlan?.peek()?.let {
            exerciseAmount = it.getAmountExercises()
        }
        binding?.progressbarExercise?.max = exerciseAmount

        currentExercisePosition++
        binding?.ivExercise?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text = exerciseList!![currentExercisePosition].getName()
        binding?.tvAmountReps?.text = exerciseList!![currentExercisePosition].getRepsAmount()

        binding?.flExerciseView?.setOnClickListener {
            setExerciseProgressBar()
        }

    }

    private fun setExerciseProgressBar(){
        binding?.progressbarExercise?.progress = exerciseProgress

        when {
            exerciseAmount != exerciseProgress -> {
                binding?.progressbarExercise?.progress = exerciseAmount - exerciseProgress
                exerciseProgress++
                setUpExerciseView()
            }
            else -> {
                exerciseProgress = 1

                workoutPlan?.peek()?.let { it ->
                    when (amountSupersets) {

                        it.getAmountSupersets() -> {
                            //need go to another circle
                            workoutPlan?.poll()
                            if (workoutPlan?.iterator()?.hasNext() == false) {
                                Toast.makeText(
                                    this@ExerciseActivity,
                                    "Celebration!",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                onDestroy()
                            }
                            amountSupersets = 1
                            restTime = bigRest
                        }
                        else -> {
                            //we have small rest
                            amountSupersets++
                            restTime = it.getRestTime()
                        }

                    }

                }

                setUpRestView()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }

        binding = null
    }

}