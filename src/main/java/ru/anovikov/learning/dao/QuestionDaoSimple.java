package ru.anovikov.learning.dao;

import ru.anovikov.learning.domain.Question;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.InputStream;

public class QuestionDaoSimple implements QuestionDao{

    private String questionFile;

    public QuestionDaoSimple (String questionFile) {
        this.questionFile = questionFile;
    }

    public List<Question> findAll() {

        List<Question> lst = new ArrayList();

        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(questionFile);

        Scanner scanner = new Scanner(inputStream);
        while(scanner.hasNextLine()){
            Scanner valueScanner = new Scanner(scanner.nextLine());
            valueScanner.useDelimiter(";");
            Question qst = new Question();

            int i = 0;
            while (valueScanner.hasNext()) {
                String data = valueScanner.next();
                if (i == 0) {
                    qst.setText(data);
                }
                else if (i == 1) {
                    qst.setRightAnswer(Integer.parseInt(data));
                }
                else if (i >= 2) {
                    qst.addAnswer(data);
                }

                i++;
            }

            lst.add(qst);
        }
        scanner.close();

        return  lst;
    }
}