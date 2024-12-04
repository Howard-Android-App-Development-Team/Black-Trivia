package com.example.blacktrivia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class TriviaActivity: AppCompatActivity() {
    private lateinit var questionTextView: TextView
    private lateinit var nextButton: Button
    private lateinit var AnsA: Button
    private lateinit var AnsB: Button
    private lateinit var AnsC: Button
    private lateinit var AnsD: Button
    private var currentCorrectAnswer: String? = null
    private var currentQuestionIndex = 0
    private var triviaQuestions: List<TriviaQuestion> = listOf(
        TriviaQuestion(
            question = "What year was Howard University Founded?",
            correct_answer = "1867",
            incorrect_answers = listOf("1902", "1888", "2004")
        ),
        TriviaQuestion(
            question = "Which Supreme Court Justice attended Howard University?",
            correct_answer = "Thurgood Marshall",
            incorrect_answers = listOf("Clarence Thomas", "Ketanji Brown Jackson", "Neil Gorsuch")
        ),
        TriviaQuestion(
            question = "Who was the first African American President of the United States?",
            correct_answer = "Barack Obama",
            incorrect_answers = listOf("Bill Clinton", "Kamala Harris", "Wayne Frederick")
        ),
        TriviaQuestion(
            question = "Which American city does director Tyler Perry base his film studio out of?",
            correct_answer = "Atlanta",
            incorrect_answers = listOf("Baltimore", "Detroit", "New York")
        ),
        TriviaQuestion(
            question = "Which famous singer is known as the 'Queen of Soul'?",
            correct_answer = "Aretha Franklin",
            incorrect_answers = listOf("Fantasia", "Alicia Keys", "Diana Ross")
        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.q_and_a)

        questionTextView = findViewById(R.id.questionTv)
        AnsA = findViewById(R.id.optionABtn)
        AnsB = findViewById(R.id.optionBBtn)
        AnsC = findViewById(R.id.optionCBtn)
        AnsD = findViewById(R.id.optionDBtn)
        nextButton = findViewById(R.id.nextBtn)

        // Display the first question
        displayNextQuestion()

        // Set the OnClickListener for the Next button
        nextButton.setOnClickListener { displayNextQuestion() }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.menu_trivia

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_trivia -> {
                    if (this::class != TriviaActivity::class) {
                        val intent = Intent(this, TriviaActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        startActivity(intent)
                        finish()
                    }
                    true
                }
                R.id.menu_profile -> {
                    if (this::class != ProfileActivity::class) {
                        val intent = Intent(this, ProfileActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        startActivity(intent)
                        finish()
                    }
                    true
                }
                else -> false
            }
        }
    }
    private fun displayNextQuestion() {
        if (currentQuestionIndex < triviaQuestions.size) {
            val currentQuestion = triviaQuestions[currentQuestionIndex]

            // Display question text
            questionTextView.text = currentQuestion.question

            // Create a list of answers (correct answer + incorrect answers)
            val allAnswers = currentQuestion.incorrect_answers.toMutableList()
            currentQuestion.correct_answer?.let { allAnswers.add(it) }

            // Shuffle the answers to randomize their order
            val randomizedAnswers = allAnswers.shuffled()

            // Display the answers
            AnsA.text = randomizedAnswers[0]
            AnsB.text = randomizedAnswers[1]
            AnsC.text = randomizedAnswers[2]
            AnsD.text = randomizedAnswers[3]

            // Store the correct answer
            currentCorrectAnswer = currentQuestion.correct_answer

            AnsA.isEnabled = true
            AnsB.isEnabled = true
            AnsC.isEnabled = true
            AnsD.isEnabled = true

            // Set up click listeners for answer buttons
            AnsA.setOnClickListener { checkAnswer(randomizedAnswers[0]) }
            AnsB.setOnClickListener { checkAnswer(randomizedAnswers[1]) }
            AnsC.setOnClickListener { checkAnswer(randomizedAnswers[2]) }
            AnsD.setOnClickListener { checkAnswer(randomizedAnswers[3]) }

            // Move to the next question
            currentQuestionIndex++
        } else {
            // No more questions, end of trivia
            questionTextView.text = "End of trivia questions."
            nextButton.isEnabled = false
        }
    }

    private fun checkAnswer(selectedAnswer: String) {
        if (selectedAnswer == currentCorrectAnswer) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Incorrect! The correct answer was: $currentCorrectAnswer", Toast.LENGTH_LONG).show()
        }

        // Disable the answer buttons after selection
        AnsA.isEnabled = false
        AnsB.isEnabled = false
        AnsC.isEnabled = false
        AnsD.isEnabled = false

        // Show next question button after answering
        nextButton.isEnabled = true
    }
}