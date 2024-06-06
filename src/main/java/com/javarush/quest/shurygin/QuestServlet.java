package com.javarush.quest.shurygin;

import com.javarush.quest.shurygin.entity.Answer;
import com.javarush.quest.shurygin.entity.Question;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

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

        Question question = startQuestion;
        int games = 0;

        if (answerId != 0) {
            question = (Question) getAttribute(currentSession, "question");
        }

        games = getAttribute(currentSession, "games") == null ? 0 : (int) getAttribute(currentSession, "games");

        question = question.getQuestionByAnswerId(answerId);
        if (question.isFinal()) games++;

        currentSession.setAttribute("question", question);
        currentSession.setAttribute("games", games);

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private Object getAttribute(HttpSession currentSession, String name) {
        return currentSession.getAttribute(name);
    }

    private int getSelectedAnswerId(HttpServletRequest request) {
        String id = request.getParameter("answer");
        boolean isNumeric = id != null && id.chars().allMatch(Character::isDigit);
        return isNumeric ? Integer.parseInt(id) : 0;
    }

    public void destroy() {
    }
}