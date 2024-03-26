package com.example.quizapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity() , View.OnClickListener{
    lateinit var progressBar : ProgressBar
    lateinit var tv_progress : TextView
    lateinit var tv_question : TextView
    lateinit var iv_image : ImageView
    lateinit var option1 : TextView
    lateinit var option2 : TextView
    lateinit var option3 : TextView
    lateinit var option4 : TextView
    lateinit var submitBtn : Button

    private var mCurrentPosition = 1
    private var mSelectedOptionPosition : Int = 0
    private var mQuestionsList :ArrayList<Questions>? = null
    private var mCorrectAnswers : Int = 0
    private var mUserName : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz_questions)
        mUserName = intent.getStringExtra(Constants.USERNAME)
        // Initialize views after setContentView()
        progressBar = findViewById(R.id.progressBar)
        tv_progress = findViewById(R.id.tvProgress)
        tv_question = findViewById(R.id.tv_question)
        iv_image = findViewById(R.id.image_view)
        option1 = findViewById(R.id.tv_option1)
        option2 = findViewById(R.id.tv_option2)
        option3 = findViewById(R.id.tv_option3)
        option4 = findViewById(R.id.tv_option4)
        submitBtn = findViewById(R.id.submitBtn)

        mQuestionsList  = Constants.getQuestions()
        setQuestion()
        option1.setOnClickListener(this)
        option2.setOnClickListener(this)
        option3.setOnClickListener(this)
        option4.setOnClickListener(this)
        submitBtn.setOnClickListener(this)


    }
    private fun setQuestion(){

        val question : Questions = mQuestionsList!![mCurrentPosition- 1]
        defaultOptionsView() // at the time of setting up the question the question will be in there default view which is the normal bg

        if (mCurrentPosition == mQuestionsList!!.size){ // as if we are in the last Question then we need to change the text from submit to finish
            submitBtn.text = "FINISH"
        }else {
            submitBtn.text = "SUBMIT"
        }
        progressBar.progress = mCurrentPosition // progress bar position will be as per the current position and we will keep tracking the position of the progress Bar
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.max // 0/10 like this we are showing in our textview
        tv_question.text = question.question
        iv_image.setImageResource(question.image)
        option1.text = question.option1
        option2.text = question.option2
        option3.text = question.option3
        option4.text = question.option4
    }
    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0 ,  option1)
        options.add(1 , option2)
        options.add(2 , option3)
        options.add(3 , option4)

        for ( option in options ){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this , R.drawable.default_bg)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option1->{ // If the id is of this type then paas the textview and the option so that we can change the color of that Particular button
                selectedOptionView(option1 , 1)
            }
            R.id.tv_option2->{
                selectedOptionView(option2 , 2)
            }
            R.id.tv_option3->{
                selectedOptionView(option3 , 3)
            }
            R.id.tv_option4->{
                selectedOptionView(option4 , 4)
            }
            R.id.submitBtn->{
                if (mSelectedOptionPosition == 0){
                    mCurrentPosition++
                    when{
                        mCurrentPosition <= mQuestionsList!!.size ->{
                            setQuestion()
                        }else->{
                        val intent = Intent(this ,ResultActivity::class.java)
                        intent.putExtra(Constants.USERNAME , mUserName)
                        intent.putExtra(Constants.CORRECT_ANSWERS , mCorrectAnswers)
                        intent.putExtra(Constants.TOTAL_QUESTIONS , mQuestionsList!!.size)
                        startActivity(intent)
                    }

                    }
                }else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition , R.drawable.wrong_option_bg)
                    }else {
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer , R.drawable.correct_option_bg)
                    if (mCurrentPosition == mQuestionsList!!.size){
                        submitBtn.text = "FINISH"
                    }else {
                        submitBtn.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }
    private fun answerView(answer : Int , drawableView : Int){
           when (answer){
               1->{
                   option1.background = ContextCompat.getDrawable(this , drawableView)
               }
               2->{
                   option2.background = ContextCompat.getDrawable(this , drawableView)
               }
               3->{
                   option3.background = ContextCompat.getDrawable(this , drawableView)
               }
               4->{
                   option4.background = ContextCompat.getDrawable(this , drawableView)
               }
           }
    }
    private fun selectedOptionView(tv : TextView , selectedOptionNum : Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface , Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this , R.drawable.select_bg)
    }
}