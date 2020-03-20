package com.developer.chithlal.quizapp;

import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class QuizViewHolder extends
        RecyclerView.ViewHolder {

    public TextView mQNo;
    public TextView mQuestionText;
    public RadioGroup mAnswGroup;
    public QuizViewHolder(@NonNull View itemView) {
        super(itemView);
        mQNo = itemView.findViewById(R.id.tv_q_no);
        mQuestionText = itemView.findViewById(R.id.tv_question);
        mAnswGroup = itemView.findViewById(R.id.radio_group);
    }
}
