package com.example.textquest;

import java.io.*;

import com.example.textquest.entity.Answer;
import com.example.textquest.entity.Question;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "questServlet", value = "")
public class QuestServlet extends HttpServlet {

    private Question startQuestion;

    public void init() {

        startQuestion = new Question("Добро пожаловать в квест!");
        Question q2 = new Question("Идти дальше или проиграть?");
        Question q3 = new Question("Вы проиграли:(", true);
        Question q4 = new Question("Выиграть?");
        Question q5 = new Question("Вы не выиграли...", true);
        Question q6 = new Question("Ура! Победа!", true);

        startQuestion.addAnswer(new Answer(q2, "Начать великое приключение"));
        q2.addAnswer(new Answer(q4, "Идти дальше"));
        q2.addAnswer(new Answer(q3, "Проиграть"));
        q4.addAnswer(new Answer(q6, "Да"));
        q4.addAnswer(new Answer(q5, "Нет"));

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession currentSession = request.getSession(true);

        int answerId = getSelectedAnswerId(request);
       // System.out.println(answerId);
        Question question = answerId == 0 ? startQuestion : getQuestionAttribute(currentSession);

        question = question.getQuestionByAnswerId(answerId);

        currentSession.setAttribute("question", question);

       // System.out.println(question.getText());

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private Question getQuestionAttribute(HttpSession currentSession) {
        Object fieldAttribute = currentSession.getAttribute("question");
//        if (Question.class != fieldAttribute.getClass()) {
//            currentSession.invalidate();
//            throw new RuntimeException("Session is broken, try one more time");
//        }
        return (Question) fieldAttribute;
    }

    private int getSelectedAnswerId(HttpServletRequest request) {
        String id = request.getParameter("answer");
        boolean isNumeric = id != null && id.chars().allMatch(Character::isDigit);
        return isNumeric ? Integer.parseInt(id) : 0;
    }

    public void destroy() {
    }
}