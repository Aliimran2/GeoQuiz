package com.miassolutions.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel


private const val TAG = "QuizViewModel"
class QuizViewModel : ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_pakistan, true),
        Question(R.string.question_africa, false),
        Question(R.string.question_oceans, true),
        Question(R.string.question_asia, true),
        Question(R.string.question_mideast, false)

    )

    private var currentIndex = 0
    private var score = 0
    private var finalScore = 0.0

    val currentQuestionText : Int
        get() = questionBank[currentIndex].textResId

    val currentQuestionAnswer : Boolean
        get() = questionBank[currentIndex].answer

    fun moveToNext() {
        currentIndex = (currentIndex - 1 + questionBank.size) % questionBank.size
    }

    fun currentScore () {
        score++
    }





}