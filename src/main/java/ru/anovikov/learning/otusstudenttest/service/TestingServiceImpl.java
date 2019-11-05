package ru.anovikov.learning.otusstudenttest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusstudenttest.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Service
public class TestingServiceImpl implements TestingService {

    private final BufferedReader reader;
    private List<Question> testingQuestions;
    private final Locale locale;

    private int currentQiestionNumber;
    private int lastResult;
    private boolean hasResult;

    private String name;
    private String lastname;

    private final QuestionService questionService;
    private final MessageSource messageSource;

    @Autowired
    public TestingServiceImpl(QuestionService questionService, MessageSource messageSource, BufferedReader bufferedReader) {
        this.questionService = questionService;
        this.messageSource = messageSource;
        this.reader = bufferedReader;
        this.locale = Locale.getDefault();

        this.name = "";
        this.lastname = "";
        this.currentQiestionNumber = 0;
        this.lastResult = 0;
        this.hasResult = false;
    }

    public String readUserName() {
        try {
            System.out.print(messageSource.getMessage("read.name", new String[] {""}, locale) + ": ");
            this.name = reader.readLine();
        }
        catch (IOException e) {
            System.out.print(messageSource.getMessage("error.read.name", new String[] {""}, locale) + ": " + e.getMessage());
        }

        try {
            System.out.print(messageSource.getMessage("read.lastname", new String[] {""}, locale) + ": ");
            this.lastname = reader.readLine();
        }
        catch (IOException e) {
            System.out.print(messageSource.getMessage("error.read.lastname", new String[] {""}, locale) + ": " + e.getMessage());
        }

        System.out.println(String.format(messageSource.getMessage("write.greetings", new String[]{""}, locale), this.name + " " + this.lastname));

        return this.name + " " + this.lastname;
    }

    public Question getNextQuestion() {
        if (testingQuestions == null) {
            this.testingQuestions = this.questionService.getAllQuestions();
        }

        if (currentQiestionNumber < testingQuestions.size()) {
            Question question = testingQuestions.get(currentQiestionNumber);
            currentQiestionNumber++;
            return question;
        }
        return null;
    }

    public void printQuestion(Question question){

        System.out.println(question.getText());

        int i = 0;
        while (i < question.getAnswers().size()) {
            System.out.println(String.valueOf(i+1) + ":" + question.getAnswers().get(i));
            i++;
        }
    }

    public int readTestAnswer() {
        int answ = 0; boolean answInput = false;
        while (!answInput) {
            try {
                System.out.print(messageSource.getMessage("read.answer", new String[] {""}, locale) + ": ");
                answ = Integer.parseInt(reader.readLine());

                if ((answ <= 0) || (answ > 4)) {
                    System.out.println(messageSource.getMessage("warning.read.answer", new String[] {""}, locale));
                }
                else { answInput = true; };
            }
            catch (NumberFormatException e) {
                System.out.print(messageSource.getMessage("warning.read.answer", new String[] {""}, locale));
            }
            catch (IOException e) {
                System.out.print(messageSource.getMessage("error.read.answer", new String[] {""}, locale) + ": " + e.getMessage());
            }
        }
        return answ;
    }

    public int startTest() {
        if (this.name.equalsIgnoreCase("")) {
            String userName = this.readUserName();
        }
        currentQiestionNumber = 0;
        int correctAnsw = 0;

        Question q = this.getNextQuestion();
        int questionCount = 0;
        while (q != null) {
            this.printQuestion(q);
            if (this.readTestAnswer() == q.getRightAnswer()) { correctAnsw++; }
            q = this.getNextQuestion();
            questionCount++;
        }
        lastResult = correctAnsw;
        hasResult = true;

        return correctAnsw;
    }

    public void printResult() {
        if (!hasResult) {
            System.out.println(messageSource.getMessage("warning.write.result", new String[]{""}, locale));
        }
        else {
            System.out.println(String.format(messageSource.getMessage("write.good.result", new String[]{""}, locale), name + " " + lastname, lastResult, testingQuestions.size()));
        }
    }

    public int getResult() {
        return lastResult;
    }
}