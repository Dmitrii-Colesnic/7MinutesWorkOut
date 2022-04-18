package com.example.a7minutesworkout

class WorkoutPlan(
    private var amountExercises: Int,
    private var amountSupersets: Int,
    private var restTime: Int = 0,
) {

    fun setAmountExercises(amountExercises: Int){this.amountExercises = amountExercises}
    fun getAmountExercises(): Int {return amountExercises}

    fun setAmountSupersets(amountSupersets: Int){this.amountSupersets = amountSupersets}
    fun getAmountSupersets(): Int {return amountSupersets}

    fun setRestTime(restTime: Int){this.restTime = restTime}
    fun getRestTime(): Int {return restTime}

}