package com.messageApp.messageApp.TestCases;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import com.messageApp.messageApp.Tasks.CadastroTask;
import com.messageApp.messageApp.Tasks.LoginTask;
import com.messageApp.messageApp.TestBase;


public class RealizarCadastroLogin extends TestBase {
    private final WebDriver driver = getDriverManager();
    private CadastroTask cadastroTask = new CadastroTask(driver);
    private LoginTask loginTask = new LoginTask(driver);

    @Test
    public void realizarCadastroLoginSucesso() {
        cadastroTask.realizarCadastro();
        loginTask.realizarLogin();
    }
}
