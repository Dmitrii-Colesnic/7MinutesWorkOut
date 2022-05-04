package com.example.a7minutesworkout

import java.util.*
import kotlin.collections.ArrayList as ArrayList

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel> {

        val exerciseList = ArrayList<ExerciseModel>()

        val pullUps1: ExerciseModel = ExerciseModel(
            1,
            "Wide Grip Pull-Ups",
            "7 reps",
            R.drawable.ic_pullups_400x500_1_1,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pullUps1)

        val pushUps1: ExerciseModel = ExerciseModel(
            2,
            "Push-Ups On Narrow Bars",
            "15 reps",
            R.drawable.ic_pushups_400x500_1_1,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pushUps1)

        val pullUps2: ExerciseModel = ExerciseModel(
            3,
            "Wide Grip Pull-Ups",
            "7 reps",
            R.drawable.ic_pullups_400x500_1_2,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pullUps2)

        val pushUps2: ExerciseModel = ExerciseModel(
            4,
            "Push-Ups On Narrow Bars",
            "15 reps",
            R.drawable.ic_pushups_400x500_1_2,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pushUps2)

        val pullUps3: ExerciseModel = ExerciseModel(
            5,
            "Wide Grip Pull-Ups",
            "7 reps",
            R.drawable.ic_pullups_400x500_1_3,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pullUps3)

        val pushUps3: ExerciseModel = ExerciseModel(
            6,
            "Push-Ups On Narrow Bars",
            "15 reps",
            R.drawable.ic_pushups_400x500_1_3,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pushUps3)

        val pullUps4: ExerciseModel = ExerciseModel(
            7,
            "Wide Grip Pull-Ups",
            "7 reps",
            R.drawable.ic_pullups_400x500_1_4,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pullUps4)

        val pushUps4: ExerciseModel = ExerciseModel(
            8,
            "Push-Ups On Narrow Bars",
            "15 reps",
            R.drawable.ic_pushups_400x500_1_4,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pushUps4)

        val pullUps5: ExerciseModel = ExerciseModel(
            9,
            "Wide Grip Pull-Ups",
            "7 reps",
            R.drawable.ic_pullups_400x500_1_5,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pullUps5)

        val pushUps5: ExerciseModel = ExerciseModel(
            10,
            "Push-Ups On Narrow Bars",
            "15 reps",
            R.drawable.ic_pushups_400x500_1_5,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pushUps5)

        val pullUps2_1: ExerciseModel = ExerciseModel(
            11,
            "Narrow Grip Pull-Ups",
            "10 reps",
            R.drawable.ic_pullups_400x500_2_1,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pullUps2_1)

        val pushUps2_1: ExerciseModel = ExerciseModel(
            13,
            "Push-Ups On Wide Bars",
            "15 reps",
            R.drawable.ic_pushups_400x500_2_1,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pushUps2_1)

        val press1: ExerciseModel = ExerciseModel(
            12,
            "Raising Legs On The Horizontal Bar",
            "10 reps",
            R.drawable.ic_press_400x500_1,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(press1)

        val pullUps2_2: ExerciseModel = ExerciseModel(
            14,
            "Narrow Grip Pull-Ups",
            "10 reps",
            R.drawable.ic_pullups_400x500_2_2,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pullUps2_2)

        val pushUps2_2: ExerciseModel = ExerciseModel(
            16,
            "Push-Ups On Wide Bars",
            "15 reps",
            R.drawable.ic_pushups_400x500_2_2,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pushUps2_2)

        val press2: ExerciseModel = ExerciseModel(
            15,
            "Raising Legs On The Horizontal Bar",
            "10 reps",
            R.drawable.ic_press_400x500_2,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(press2)

        val pullUps2_3: ExerciseModel = ExerciseModel(
            17,
            "Narrow Grip Pull-Ups",
            "10 reps",
            R.drawable.ic_pullups_400x500_2_3,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pullUps2_3)

        val pushUps2_3: ExerciseModel = ExerciseModel(
            19,
            "Push-Ups On Wide Bars",
            "15 reps",
            R.drawable.ic_pushups_400x500_2_3,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pushUps2_3)

        val press3: ExerciseModel = ExerciseModel(
            18,
            "Raising Legs On The Horizontal Bar",
            "10 reps",
            R.drawable.ic_press_400x500_3,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(press3)

        val pullUps2_4: ExerciseModel = ExerciseModel(
            20,
            "Narrow Grip Pull-Ups",
            "10 reps",
            R.drawable.ic_pullups_400x500_2_4,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pullUps2_4)

        val pushUps2_4: ExerciseModel = ExerciseModel(
            22,
            "Push-Ups On Wide Bars",
            "15 reps",
            R.drawable.ic_pushups_400x500_2_4,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pushUps2_4)

        val press4: ExerciseModel = ExerciseModel(
            21,
            "Raising Legs On The Horizontal Bar",
            "10 reps",
            R.drawable.ic_press_400x500_4,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(press4)

        val pullUps2_5: ExerciseModel = ExerciseModel(
            23,
            "Narrow Grip Pull-Ups",
            "10 reps",
            R.drawable.ic_pullups_400x500_2_5,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pullUps2_5)

        val pushUps2_5: ExerciseModel = ExerciseModel(
            24,
            "Push-Ups On Wide Bars",
            "15 reps",
            R.drawable.ic_pushups_400x500_2_5,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pushUps2_5)

        val press5: ExerciseModel = ExerciseModel(
            25,
            "Raising Legs On The Horizontal Bar",
            "10 reps",
            R.drawable.ic_press_400x500_5,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(press5)

        return exerciseList;
    }

    fun defineWorkoutPlan() : Queue<WorkoutPlan> {

        val workoutPlan: Queue<WorkoutPlan> = LinkedList<WorkoutPlan>()

        val firstCircle = WorkoutPlan(
            2,
            5,
            1
        )
        workoutPlan.add(firstCircle)

        val secondCircle = WorkoutPlan(
            3,
            5,
            1
        )
        workoutPlan.add(secondCircle)

        return workoutPlan

    }
}