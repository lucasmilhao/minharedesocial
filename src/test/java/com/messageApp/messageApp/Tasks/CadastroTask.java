package com.messageApp.messageApp.Tasks;

import org.openqa.selenium.WebDriver;

import com.messageApp.messageApp.PageObjects.AuthPage;
import com.messageApp.messageApp.framework.browser.Waits;
import com.messageApp.messageApp.framework.utils.FakersGeneration;
import com.messageApp.messageApp.framework.utils.FileOperations;

public class CadastroTask {
    private final WebDriver driver;
    private Waits wait;
    private AuthPage page;
    private FakersGeneration fakers;

    public CadastroTask(WebDriver driver) {
        this.driver = driver;
        this.page = new AuthPage(driver);
        this.wait = new Waits(driver);
        this.fakers = new FakersGeneration();
    }

    public void realizarCadastro() {
        String email = fakers.getEmail();
        String nome = fakers.getNome();
        String senha = fakers.getSenha();

        FileOperations.setProperties("user", "email", email);
        FileOperations.setProperties("user", "nome", nome);
        FileOperations.setProperties("user", "senha", senha);

        page.getEmailInput().sendKeys(email);
        page.getNomeInput().sendKeys(nome);
        page.getSenhaInput().sendKeys(senha);

        page.getButtonConfirmar().click();
        wait.loadElement(page.getLinkCadastro());
    }
}
