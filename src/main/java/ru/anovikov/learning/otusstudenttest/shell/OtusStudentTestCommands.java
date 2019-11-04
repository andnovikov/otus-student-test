package ru.anovikov.learning.otusstudenttest.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.anovikov.learning.otusstudenttest.service.TestingService;

@ShellComponent
public class OtusStudentTestCommands {

    @Autowired
    private TestingService testingService;

    @ShellMethod(value = "Start testing", key = {"start-test", "start"})
    public void printPartyMembers() {
        testingService.startTest();
    }

}
