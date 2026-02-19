package com.messageApp.messageApp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import com.messageApp.messageApp.framework.browser.DriverManager;
import com.messageApp.messageApp.framework.browser.TypeBrowser;
import com.messageApp.messageApp.framework.utils.FileOperations;

public class TestBase extends DriverManager {

    private static WebDriver driver;
    private static String URL = FileOperations.getProperties("url").getProperty("index");

    public static WebDriver getDriverManager(){
        driver = getDriver(TypeBrowser.EDGE);
        return driver;
    }

    @BeforeEach
    public void setUp(){

        getDriverManager().get(URL);
    }

    @AfterEach
    public void finish(){

        fecharDriver();

    }

}