package com.example.automation.pages;

import com.example.automation.utils.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class HomePage extends BasePage {

    @FindBy(css = "nav[role='navigation']")
    protected WebElement navMenu;

    @FindBy(linkText = "Sign up")
    protected WebElement sign_up;

    @FindBy(linkText = "Sign in")
    protected WebElement sign_in;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ArrayList<String> getMenuItems() {
        ArrayList <String> results = new ArrayList<>();
        for(WebElement item : navMenu.findElements(By.xpath("*"))) {
            results.add(item.getText());
        }
        return results;
    }

    public SignUpPage signUp() {
        sign_up.click();

        return getInstance(SignUpPage.class);
    }

    public SignInPage signIn() {
        sign_in.click();

        return getInstance(SignInPage.class);
    }
}
