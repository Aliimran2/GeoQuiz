package com.miassolutions.geoquiz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.miassolutions.geoquiz.databinding.ActivityMainBinding

private const val TAG = "Callback_functions"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate is called")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
            enableAnswersButtons(true)

        }

        binding.previousButton.setOnClickListener {

            currentIndex = (currentIndex - 1 + questionBank.size) % questionBank.size

            updateQuestion()
            enableAnswersButtons(true)

        }






        binding.trueButton.setOnClickListener {
            checkAnswer(true)
            enableAnswersButtons(false)

        }

        binding.falseButton.setOnClickListener {
            checkAnswer(false)
            enableAnswersButtons(false)
        }

        enableAnswersButtons(true)
        updateQuestion()

        binding.retryBtn.setOnClickListener {
            currentIndex = -1
            score = 0

            binding.apply {
                nextButton.isEnabled = true
                previousButton.isEnabled = true
                trueButton.isEnabled = true
                falseButton.isEnabled = true
                retryBtn.visibility = View.GONE
            }
        }

    }
    private fun hasAnsweredAllQuestions(): Boolean{
        return currentIndex == questionBank.size - 1
    }

    private fun enableAnswersButtons(isEnable: Boolean) {
        binding.trueButton.isEnabled = isEnable
        binding.falseButton.isEnabled = isEnable

    }

    private fun disableNextButtonWhenLastQuestion() {
        binding.nextButton.isEnabled = currentIndex != questionBank.size - 1
        binding.previousButton.isEnabled = currentIndex != questionBank.size - 1

    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        if (userAnswer == correctAnswer) {
            showSnackBar(getString(R.string.correct_toast))
            score++
        } else {
            showSnackBar(getString(R.string.incorrect_toast))
        }
        if (hasAnsweredAllQuestions()){
            showScore()
            binding.questionTextView.text = "Your score is $finalScore %"
            binding.retryBtn.visibility = View.VISIBLE
        }
    }



    private fun showScore(){
        finalScore = (score.toDouble()/questionBank.size.toDouble())*100
        Toast.makeText(this, "Your score is $finalScore %", Toast.LENGTH_SHORT).show()
    }

    private fun showSnackBar(msg : String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
        disableNextButtonWhenLastQuestion()
    }

}