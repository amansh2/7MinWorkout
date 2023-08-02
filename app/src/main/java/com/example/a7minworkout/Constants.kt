package com.example.a7minworkout

object Constants {
    fun defaultExerciseList(): ArrayList<ExerciseModel> {

        var list = ArrayList<ExerciseModel>()
        list.addAll(
            listOf(
                ExerciseModel(1, "Jumping Jacks", R.drawable.jj),
                ExerciseModel(2, "Wall Sits", R.drawable.wallsits),
                ExerciseModel(3,"Push Ups",R.drawable.pushups),
                ExerciseModel(4,"Ab Crunch",R.drawable.abscrunch),
                ExerciseModel(5,"Step Up",R.drawable.stepup),
                ExerciseModel(6,"Squat",R.drawable.squat),
                ExerciseModel(7,"Triceps Dips",R.drawable.tricepsdips),
                ExerciseModel(8,"Plank",R.drawable.plank),
                ExerciseModel(9,"High Knees",R.drawable.highknees),
                ExerciseModel(10,"Lunges",R.drawable.lunges),
                ExerciseModel(11,"Push Ups And Rotation",R.drawable.pushupsceiling),
                ExerciseModel(12,"Side Plank",R.drawable.sideplank),
            )
        )
        return list
    }
}