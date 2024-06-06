package com.javarush.quest.shurygin.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Question {

    private List<Answer> answers = new ArrayList<>();
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
        if (finalAnswer) {
            answers.add(Answer.FINAL_ANSWER);
        }
    }

    public Question(String text) {
        this(text, false);
    }

    public Question getQuestionByAnswerId(int answerId) {
        Optional<Answer> any = getAnswers().stream().filter(answer -> answer.getId() == answerId).findAny();
        return any.isEmpty() ? this : any.get().getQuestion();
    }

    public boolean isFinal() {
        return answers.size() == 1 && answers.get(0) == Answer.FINAL_ANSWER;
    }
}
