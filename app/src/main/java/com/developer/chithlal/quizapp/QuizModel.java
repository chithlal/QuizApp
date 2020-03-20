package com.developer.chithlal.quizapp;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class QuizModel implements QuizContract.Model {
    private final Context mMContext;
    private final QuizContract.Presenter mMPresenter;
    private File myExternalFile;

    public QuizModel(Context mContext,QuizContract.Presenter mPresenter) {
        mMContext = mContext;
        mMPresenter = mPresenter;
    }

    @Override
    public List<Questions> fetchQuiz() {

        return getQuestionList(readQuestions());
    }

    String readQuestions(){

        Toast.makeText(mMContext, "read questions", Toast.LENGTH_LONG).show();
        String jsonString = "";
        String yourFilePath = mMContext.getFilesDir() + "/" + "questions.txt";
        Toast.makeText(mMContext, yourFilePath, Toast.LENGTH_LONG).show();
        try {
            InputStream inputStream = mMContext.openFileInput("questions.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                jsonString = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("Quiz", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("Quiz", "Can not read file: " + e.toString());
        }
        Log.d(TAG, "readQuestions: "+jsonString);
        return jsonString;

    }
    List<Questions> getQuestionList(String jsonString){

        Gson gson = new Gson();
        List<Questions> quizList = gson.fromJson(jsonString,new TypeToken<List<Questions>>(){}.getType());
        return quizList;

    }



}