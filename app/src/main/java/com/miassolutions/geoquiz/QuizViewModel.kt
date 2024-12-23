package com.miassolutions.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel


private const val TAG = "QuizViewModel"
class QuizViewModel : ViewModel() {



    init {
        Log.d(TAG, "viewmodel instance is created")
    }

    override fun onCleared() {
        super.onCleared()
    Log.d(TAG, "viewmodel instance is about to destroy")
    }
}