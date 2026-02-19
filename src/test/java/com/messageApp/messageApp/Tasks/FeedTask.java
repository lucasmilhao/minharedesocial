package com.messageApp.messageApp.Tasks;

import org.openqa.selenium.WebDriver;

import com.messageApp.messageApp.PageObjects.FeedPage;
import com.messageApp.messageApp.PageObjects.PopUpPage;
import com.messageApp.messageApp.framework.browser.Waits;
import com.messageApp.messageApp.framework.utils.FakersGeneration;

public class FeedTask {
    private final WebDriver driver;
    private FeedPage page;
    private PopUpPage popUpPage;
    private Waits waits;
    private FakersGeneration fakers;

    public FeedTask(WebDriver driver) {
        this.driver = driver;
        this.page = new FeedPage(driver);
        this.popUpPage = new PopUpPage(driver);
        this.waits = new Waits(driver);
        this.fakers = new FakersGeneration();
    }

    public void realizarPostagem() {
        waits.loadElement(page.getPostarButton());
        page.getPostarButton().click();
        popUpPage.getImagemInput().sendKeys(fakers.getImagem());
        popUpPage.getDescricaoInput().sendKeys(fakers.getDescricao());
        popUpPage.getPostarButton().click();
    }
}
