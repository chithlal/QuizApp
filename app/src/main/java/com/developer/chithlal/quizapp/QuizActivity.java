package com.developer.chithlal.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class QuizActivity extends AppCompatActivity implements QuizContract.View ,QuizAdapter.onAnswerSelected{

    private static final int PERMISSION_REQ_CODE = 101;
    private RecyclerView mRecyclerView;
    private QuizAdapter mQuizAdapter;
    private QuizPresenter mQuizPresenter;
    List<Answer> answersList;
    CardView button;
    public static final String PERMISSION_STORAGE_READ = Manifest.permission.READ_EXTERNAL_STORAGE;
    private List<Questions> qsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.rv_quiz);
        button = findViewById(R.id.cv_submit_button);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mQuizPresenter = new QuizPresenter(this,this);
        if (checkPermissionIfNotRequest(PERMISSION_STORAGE_READ))
        mQuizPresenter.getQuestionList();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total = 0;
                if(answersList!=null){
                    /*for(int i =0; i<qsList.size();i++)
                    {
                            if(qsList.get(i).getRightAnswer().equals(answersList.get(i).getAnswer()))
                                total ++;
                    }*/

                }
                else{
                    Toast.makeText(getApplicationContext(),"Please select all answers",Toast.LENGTH_LONG).show();
                }

                if (total==0)
                    Toast.makeText(getApplicationContext(),"You are passed the quiz",Toast.LENGTH_LONG).show();
            }


        });
    }

    @Override
    public void updateQuizView(List<Questions> qList) {
        qsList = qList;
        mQuizAdapter = new QuizAdapter(this, qList,this);
        mRecyclerView.setAdapter(mQuizAdapter);
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    public boolean checkPermissionIfNotRequest(String PERMISSION_ID) {
        if (ContextCompat.checkSelfPermission(this, PERMISSION_ID)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermission(PERMISSION_ID);

            return true;
        } else
            return true;
    }

    int requestPermission(String PERMISSION_ID) {

        ActivityCompat.requestPermissions(this,
                new String[]{PERMISSION_ID},
                PERMISSION_REQ_CODE);


        return 0;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_REQ_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "We need this permission to proceed, Please grant it.",
                            Toast.LENGTH_LONG).show();
                }

            }


        }

    }

    @Override
    public void onAnswerSelected(List<Answer> answers) {
      answersList = answers;

    }
}
