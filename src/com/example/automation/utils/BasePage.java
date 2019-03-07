package com.example.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected final WebDriver driver;

    protected String base_url = "https://www.example.com/";

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public final <Page extends BasePage> Page getInstance (Class<Page> pageClass) {
        // Stolen from https://www.swtestacademy.com/page-factory-selenium-webdriver/
        try {
            //Initialize the Page with its elements and return it.
            return PageFactory.initElements(driver,  pageClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void loadPage() {
        driver.get(getURL());
    }

    public String getURL() {
        return base_url;
    }
}
