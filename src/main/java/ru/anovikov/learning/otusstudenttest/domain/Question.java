package ru.anovikov.learning.otusstudenttest.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String text;
    private int rightAnswer;
    private List<String> answers;

    public Question () {
        answers = new ArrayList();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public List<String> getAnswers() { return answers; }

    public void setAnswers(List<String> answers) { this.answers = answers; }

    public void addAnswer(String answer) {
        answers.add(answer);
    }
}