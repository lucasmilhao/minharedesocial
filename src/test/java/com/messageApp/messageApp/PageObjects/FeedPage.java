package com.messageApp.messageApp.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.messageApp.messageApp.framework.browser.Waits;
import com.messageApp.messageApp.framework.utils.FileOperations;

public class FeedPage {
    private final WebDriver driver;
    private Waits waits;

    public FeedPage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
    }

    public WebElement getPostarButton() {
        By elemento = By.id(FileOperations.getProperties("form").getProperty("botao-postar"));
        waits.visibilityOfElement(elemento);
        return driver.findElement(elemento);
    }

    public WebElement getFotoUsuarioLogadoButton() {
        return driver.findElement(By.id(FileOperations.getProperties("form").getProperty("botao-perfil-usuario")));
    }

    public WebElement getFotoUsuarioButton() {
        return driver.findElement(By.id(FileOperations.getProperties("form").getProperty("botao-perfil-usuario")));
    }
}
