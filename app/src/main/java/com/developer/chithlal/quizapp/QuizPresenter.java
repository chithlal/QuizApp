package com.developer.chithlal.quizapp;

import android.content.Context;

public class QuizPresenter implements QuizContract.Presenter {
    private Context mMContext;
    private QuizContract.View mMView;
    private QuizModel mQuizModel;

    public QuizPresenter(Context mContext,QuizContract.View mView) {
        mMContext = mContext;
        mMView = mView;
    }

    @Override
    public void getQuestionList() {
        mQuizModel = new QuizModel(mMContext,this);
        mMView.updateQuizView(mQuizModel.fetchQuiz());


    }

    @Override
    public void onQuestionsLoaded() {

    }
}
