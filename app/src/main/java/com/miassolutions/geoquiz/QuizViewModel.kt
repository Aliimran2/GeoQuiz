package com.miassolutions.geoquiz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


private const val TAG = "QuizViewModel"
private const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_pakistan, true),
        Question(R.string.question_africa, false),
        Question(R.string.question_oceans, true),
        Question(R.string.question_asia, true),
        Question(R.string.question_mideast, false)

    )

    private var currentIndex : Int
        get() = savedStateHandle.get<Int>(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

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