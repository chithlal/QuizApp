package com.developer.chithlal.quizapp;

import java.util.List;

public interface QuizContract {

     interface View{
          void updateQuizView(List<Questions> qList);
          void showMessage(String message);


     }

     interface Presenter{
         void getQuestionList();
         void onQuestionsLoaded();


     }

     interface Model{
         List<Questions> fetchQuiz();
     }

}
