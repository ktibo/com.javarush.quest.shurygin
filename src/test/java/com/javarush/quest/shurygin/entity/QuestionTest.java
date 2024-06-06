package com.javarush.quest.shurygin.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuestionTest {

    private Question question;

    @BeforeEach
    void init(){
        question = new Question("test");
    }

    @Test
    void getAndAddAnswer() {
        Answer answer = new Answer(null, "test");
        question.addAnswer(answer);
        Assertions.assertEquals(answer, question.getAnswers().get(0));
    }

    @Test
    void getText() {
        Assertions.assertEquals("test", question.getText());
    }

    @Test
    void getQuestionByAnswerId() {

        Question q1 = new Question("test");
        Question q2 = new Question("test");
        Question q3 = new Question("test");

        question.addAnswer(new Answer(q1, "test"));
        question.addAnswer(new Answer(q2, "test"));
        question.addAnswer(new Answer(q3, "test"));

        Question questionByAnswerId = question.getQuestionByAnswerId(2);

        Assertions.assertEquals(q2, questionByAnswerId);

    }

}