package com.messageApp.messageApp.Tasks;

import org.openqa.selenium.WebDriver;

import com.messageApp.messageApp.PageObjects.AuthPage;
import com.messageApp.messageApp.framework.browser.Waits;
import com.messageApp.messageApp.framework.utils.FileOperations;

public class LoginTask {
    private final WebDriver driver;
    private AuthPage page;
    private Waits waits;

    public LoginTask(WebDriver driver) {
        this.driver = driver;
        this.page = new AuthPage(driver);
        this.waits = new Waits(driver);
    }

    public void realizarLogin() {
        waits.loadElement(page.getLinkCadastro()); 

        String email = FileOperations.getProperties("user").getProperty("email");
        String senha = FileOperations.getProperties("user").getProperty("senha");

        page.getEmailInput().sendKeys(email);
        page.getSenhaInput().sendKeys(senha);
        page.getButtonConfirmar().click();
    }

    
    public void realizarLoginComConta() {
        page.getLinkLogin().click();

        String email = FileOperations.getProperties("user").getProperty("email");
        String senha = FileOperations.getProperties("user").getProperty("senha");

        page.getEmailInput().sendKeys(email);
        page.getSenhaInput().sendKeys(senha);
        page.getButtonConfirmar().click();
    }
}
