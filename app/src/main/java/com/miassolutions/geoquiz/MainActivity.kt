package com.miassolutions.geoquiz

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.miassolutions.geoquiz.databinding.ActivityMainBinding

private const val TAG = "CALLBACKS_FUNCTIONS"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quizViewModel : QuizViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onStart is called")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }

        binding.previousButton.setOnClickListener {

            quizViewModel.moveToNext()
            updateQuestion()

        }

        binding.trueButton.setOnClickListener {
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener {
            checkAnswer(false)
        }

        updateQuestion()

    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        if (userAnswer == correctAnswer) {
            showSnackBar(getString(R.string.correct_toast))
            quizViewModel.currentScore()
        } else {
            showSnackBar(getString(R.string.incorrect_toast))
        }

    }

    private fun showSnackBar(msg : String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart is called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume is called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause is called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop is called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart is called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy is called")
    }
}