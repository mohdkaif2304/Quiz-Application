package com.example.quizapplication

object Constants {
const val USERNAME : String = "username"
const val TOTAL_QUESTIONS : String = "total_questions"
const val CORRECT_ANSWERS : String = "correctAnswers"

    fun getQuestions() : ArrayList<Questions>{
        val quesList = ArrayList<Questions>() // quesList is equal to emty array list now later we will add wlwmwnts in it

        val ques1 = Questions(1 ,
            "Who is the Most Badmaash in the house " ,
            R.drawable.ic_bg ,
            "Kaif" ,
            "Parthiv" ,
            "Neelesh" ,
            "Obaid" , 2)
        val ques2 = Questions(2 ,
            "Who is the Bhalu in the House  " ,
            R.drawable.ic_bg ,
            "Kaif" ,
            "Parthiv" ,
            "Neelesh" ,
            "Obaid" , 2)
        val ques3 = Questions(3 ,
            "Who is Natkhat Bacha in the House  " ,
            R.drawable.ic_bg ,
            "Kaif" ,
            "Parthiv" ,
            "Neelesh" ,
            "Obaid" , 3)
        val ques4 = Questions(4 ,
            "Who is the most handsome guy here  " ,
            R.drawable.ic_bg ,
            "Kaif" ,
            "Parthiv" ,
            "Neelesh" ,
            "Obaid" , 1)
        val ques5 = Questions(5 ,
            " Person Which is Self Drive and motivated" ,
            R.drawable.ic_bg ,
            "Kaif" ,
            "Madhav Dhingra" ,
            "Neelesh" ,
            "Obaid" , 2)
        quesList.add(ques1)
        quesList.add(ques2)
        quesList.add(ques3)
        quesList.add(ques4)
        quesList.add(ques5)

        return quesList
    }
}