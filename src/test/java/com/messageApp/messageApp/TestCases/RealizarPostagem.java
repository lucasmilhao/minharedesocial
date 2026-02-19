package com.messageApp.messageApp.TestCases;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import com.messageApp.messageApp.Tasks.FeedTask;
import com.messageApp.messageApp.Tasks.LoginTask;
import com.messageApp.messageApp.TestBase;

public class RealizarPostagem extends TestBase {
    private final WebDriver driver = getDriverManager();
    private FeedTask feedTask = new FeedTask(driver);
    private LoginTask loginTask = new LoginTask(driver);

    @Test
    public void fazerPostagem() {
        loginTask.realizarLoginComConta();
        feedTask.realizarPostagem();
    }
}
