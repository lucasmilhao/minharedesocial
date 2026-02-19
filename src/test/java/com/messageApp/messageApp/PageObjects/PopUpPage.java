package com.messageApp.messageApp.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.messageApp.messageApp.framework.browser.Waits;
import com.messageApp.messageApp.framework.utils.FileOperations;

public class PopUpPage {
    private final WebDriver driver;
    private Waits waits;

    public PopUpPage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
    }

    public WebElement getImagemInput() {
        By elemento = By.id(FileOperations.getProperties("form").getProperty("input-link-imagem"));
        waits.visibilityOfElement(elemento);
        return driver.findElement(elemento);
    }

    public WebElement getDescricaoInput() {
        By elemento = By.id(FileOperations.getProperties("form").getProperty("input-descricao"));
        waits.visibilityOfElement(elemento);
        return driver.findElement(elemento);
    }
    
    public WebElement getPostarButton() {
        By elemento = By.id(FileOperations.getProperties("form").getProperty("botao-fazer-postagem"));
        waits.visibilityOfElement(elemento);
        return driver.findElement(elemento);
    }

}
