package com.javarush.quest.shurygin.entity;

public class Answer {

    public static final Answer FINAL_ANSWER = new Answer(null, "Заново");
    private static int currentID = 1;

    private Question question;
    private String text;
    private int id;

    public String getText() {
        return text;
    }

    public Answer(Question question, String test) {
        this.question = question;
        this.text = test;
        id = currentID++;
    }

    public Question getQuestion() {
        return question;
    }

    public int getId() {
        return id;
    }
}
