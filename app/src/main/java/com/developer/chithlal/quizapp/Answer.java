package com.developer.chithlal.quizapp;

public class Answer {
    int qno;
    String answer;

    public Answer(int qno, String answer) {
        this.qno = qno;
        this.answer = answer;
    }

    public int getQno() {
        return qno;
    }

    public void setQno(int qno) {
        this.qno = qno;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
