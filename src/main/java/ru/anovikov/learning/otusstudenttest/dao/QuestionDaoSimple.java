package ru.anovikov.learning.otusstudenttest.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusstudenttest.domain.Question;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.InputStream;

@PropertySource("classpath:application.yml")
@Service
public class QuestionDaoSimple implements QuestionDao{

    @Value("${application.questionfile}")
    private String questionFile;

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