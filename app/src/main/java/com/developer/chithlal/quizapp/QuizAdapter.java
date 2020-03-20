package com.developer.chithlal.quizapp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizViewHolder> {
    private final Context mMContext;
    private final List<Questions> mMListQuestion;
    private onAnswerSelected mView;
    View rootView;

    List<Answer> answerList;

    public QuizAdapter(Context mContext, List<Questions> mListQuestion,QuizAdapter.onAnswerSelected view) {
        mMContext = mContext;
        mMListQuestion = mListQuestion;
        mView = view;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

         rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_quiz,parent,false);
        return new QuizViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, final int position) {
        answerList = new ArrayList<>();
        Questions questions = mMListQuestion.get(position);
        holder.mQNo.setText(questions.getId());
        holder.mQuestionText.setText(questions.getQuestion());
        for (int i = 0; i < holder
                .mAnswGroup.getChildCount(); i++) {
            ((RadioButton) holder.mAnswGroup.getChildAt(i)).setText(questions.getAnswers().get(i));
        }

        holder.mAnswGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
              int ans =   onRadioButtonClicked(group.getCheckedRadioButtonId());

                        RadioButton rb = rootView.findViewById(checkedId);
              Answer answer = new Answer(position,rb.getText().toString());
              answerList.add(answer);
              mView.onAnswerSelected(answerList);




            }
        });

    }

    @Override
    public int getItemCount() {
        if (mMListQuestion!=null)
        return mMListQuestion.size();
        else return 0;
    }
    public int onRadioButtonClicked(int id) {
        // Is the button now checked?
        int answer;

        // Check which radio button was clicked
        switch(id) {
            case R.id.radio_answ_one:

                    answer =1;
                    break;
            case R.id.radio_answ_two:
                    answer = 2;
                    break;
            case R.id.radio_answ_three:

                   answer = 3;
                    break;
            case R.id.radio_answ_four:

                    answer  = 4;
                    break;
            default: answer = 1;

        }
        return answer;
    }
    interface onAnswerSelected{
        void onAnswerSelected(List<Answer> answers);
    }




}
