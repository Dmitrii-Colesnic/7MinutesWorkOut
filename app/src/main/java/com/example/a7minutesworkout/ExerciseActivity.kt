package com.example.a7minutesworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding
import com.example.a7minutesworkout.databinding.DialogCustomBackConfirmationBinding
import java.util.*

class ExerciseActivity : AppCompatActivity()/*, TextToSpeech.OnInitListener */{

    private var binding: ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0
    private var restTime = 0
    private var bigRest = 600

    private var exerciseProgress = 1
    private var exerciseAmount = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    private var workoutPlan: Queue<WorkoutPlan>? = null
    private var amountSupersets = 1

//    private var tts: TextToSpeech? = null
    private var player: MediaPlayer? = null

    private var exerciseAdapter: ExerciseStatusAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        try {
            val soundURI = Uri.parse("android.resource://com.example.a7minutesworkout/" + R.raw.press_start)

            player = MediaPlayer.create(applicationContext, soundURI)
            player?.isLooping = false
        }catch (e: Exception){
            e.printStackTrace()
        }
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

        setUpExerciseStatusRecyclerView()

//        tts = TextToSpeech(this, this)

        setUpExerciseView()

    }

    private fun setUpExerciseStatusRecyclerView(){
        binding?.rvExerciseStatus?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!)
        binding?.rvExerciseStatus?.adapter = exerciseAdapter
    }

    private fun setUpRestView(){
        binding?.clExercise?.visibility = View.GONE
        binding?.clRest?.visibility = View.VISIBLE

        if(restTime == workoutPlan?.peek()?.getRestTime()){

            binding?.tvCongratulations?.visibility = View.GONE
            binding?.tvNextSets?.visibility = View.GONE
            binding?.tvAnnouncementExercise?.visibility = View.VISIBLE
            binding?.tvNextExercise?.visibility = View.VISIBLE
            binding?.tvNextExercise?.text = exerciseList!![currentExercisePosition+1].getName()

        }else{

            binding?.tvAnnouncementExercise?.visibility = View.GONE
            binding?.tvNextExercise?.visibility = View.GONE
            binding?.tvCongratulations?.visibility = View.VISIBLE
            binding?.tvNextSets?.visibility = View.VISIBLE
            val nextSet = "next superset, ${workoutPlan?.peek()?.getAmountSupersets()} " +
                    "sets of ${workoutPlan?.peek()?.getAmountExercises()} exercises"
            binding?.tvNextSets?.text = nextSet

        }

        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }

        exerciseList!![currentExercisePosition].setIsSelected(true)
        exerciseAdapter!!.notifyDataSetChanged()

        setRestProgressBar()
    }

    private fun setRestProgressBar(){
        binding?.progressbar?.progress = restProgress
        binding?.progressbar?.max = restTime

        restTimer = object : CountDownTimer((restTime * 1000).toLong(), 1000){
            override fun onTick(p0: Long) {
                if(restTime - restProgress == 4){
                    player?.start()
                }

                restProgress++
                binding?.tvTimer?.text = (restTime - restProgress).toString()
                binding?.progressbar?.progress = restTime - restProgress
            }

            override fun onFinish() {
                exerciseList!![currentExercisePosition].setIsSelected(false)
                exerciseAdapter!!.notifyDataSetChanged()

                setUpExerciseView()
            }
        }.start()

    }

    private fun setUpExerciseView(){
        binding?.clRest?.visibility = View.GONE
        binding?.clExercise?.visibility = View.VISIBLE

        workoutPlan?.peek()?.let {
            exerciseAmount = it.getAmountExercises()
        }
        binding?.progressbarExercise?.max = exerciseAmount

        currentExercisePosition++
        binding?.ivExercise?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text = exerciseList!![currentExercisePosition].getName()
        binding?.tvAmountReps?.text = exerciseList!![currentExercisePosition].getRepsAmount()

//        speakOut(binding?.tvExerciseName?.text.toString())

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
                                startActivity(Intent(this, FinishActivity::class.java))
                                finish()
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
/*
        if(tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
*/
        if(player != null){
            player!!.stop()
        }

        binding = null
    }

    override fun onBackPressed() {

        val dialog = Dialog(this@ExerciseActivity)
        val dialogBinding = DialogCustomBackConfirmationBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        dialogBinding.btnYes.setOnClickListener {
            this@ExerciseActivity.finish()
            dialog.dismiss()
        }

        dialogBinding.btnNo.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }


/*
    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){

            val result = tts?.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result ==TextToSpeech.LANG_NOT_SUPPORTED
            ){
                Log.e("TTS", "The Language Specified is not supported!")
            }

        } else {
            Log.e("TTS", "Initialized Failed!")
        }
    }

    private fun speakOut(text: String){
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
*/
}