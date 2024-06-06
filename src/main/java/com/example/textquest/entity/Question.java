package com.example.textquest.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {

    private static int currentID;

    private List<Answer> answers = new ArrayList<>();
    private int id;
    private String text;

    public List<Answer> getAnswers() {
        return answers;
    }

    public String getText() {
        return text;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public Question(String text, boolean finalAnswer) {
        this.text = text;
        id = currentID++;
        if (finalAnswer) {
            answers.add(Answer.getFinalAnswer());
        }
    }

    public Question(String text) {
        this(text, false);
    }

    public Question getQuestionByAnswerId(int answerId) {

        for (Answer answer : getAnswers()) {
            if (answer.getId() == answerId) return answer.getQuestion();
        }

        return this;

    }
}
