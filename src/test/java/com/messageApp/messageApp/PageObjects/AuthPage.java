package com.messageApp.messageApp.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.messageApp.messageApp.framework.browser.Waits;
import com.messageApp.messageApp.framework.utils.FileOperations;

public class AuthPage {
    private final WebDriver driver;
    private Waits waits;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
    }

    public WebElement getEmailInput() {
        return driver.findElement(By.id(FileOperations.getProperties("form").getProperty("email-input")));
    }
    
    public WebElement getSenhaInput() {
        return driver.findElement(By.id(FileOperations.getProperties("form").getProperty("senha-input")));
    }

    public WebElement getNomeInput() {
        return driver.findElement(By.id(FileOperations.getProperties("form").getProperty("nome-input")));
    }

    public WebElement getButtonConfirmar() {
        return driver.findElement(By.id(FileOperations.getProperties("form").getProperty("botao-submit")));
    }

    public WebElement getLinkCadastro() {
        By elemento = By.id(FileOperations.getProperties("form").getProperty("link-cadastrar-se"));
        waits.visibilityOfElement(elemento);
        return driver.findElement(elemento);
    }

    public WebElement getLinkLogin() {
        By elemento = By.id(FileOperations.getProperties("form").getProperty("link-fazer-login"));
        waits.visibilityOfElement(elemento);
        return driver.findElement(elemento);
    }
}
