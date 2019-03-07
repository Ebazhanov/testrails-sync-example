package com.example.automation.pages;


import com.example.automation.utils.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {

    @FindBy(name = "")
    protected WebElement username;

    @FindBy(name = "")
    protected WebElement password;

    @FindBy(name = "")
    protected WebElement email;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }
}
