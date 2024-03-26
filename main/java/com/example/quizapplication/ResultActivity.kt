package com.example.quizapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapplication.databinding.ActivityResult2Binding

class ResultActivity : AppCompatActivity() {
    lateinit var binding : ActivityResult2Binding
     lateinit var tv_username : TextView
     lateinit var tv_score : TextView
     lateinit var btn_finish : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result2)
        binding = ActivityResult2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        tv_username = findViewById(R.id.tv_username)
        tv_score = findViewById(R.id.tv_score)
        btn_finish = findViewById(R.id.btn_finish)
        val username = intent.getStringExtra(Constants.USERNAME , )
         tv_username.text = username.toString()
        val score = intent.getIntExtra(Constants.CORRECT_ANSWERS , 0)
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS , 0)

        tv_score.text = "Your Score is $score Out Of $totalQuestions"

        btn_finish.setOnClickListener {
            startActivity(Intent(this , MainActivity::class.java))
            finish()
        }

    }
}