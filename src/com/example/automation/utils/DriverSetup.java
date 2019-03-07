package com.example.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Paths;

public class DriverSetup {
    static String binaryPath = Paths.get("").toAbsolutePath().toString() + File.separator + "bin" + File.separator;

    public enum SupportedBrowser {
        Chrome,
        IE,
        Firefox,
        Safary
    }

    public static <T extends WebDriver> T setup(SupportedBrowser browser) throws Exception {
        String driverClassName;
        String driverExecutable;
        String osName = System.getProperty("os.name").toLowerCase();
        String seleniumDriverProperty;

        switch (browser) {
            case Chrome:
                driverClassName = "chrome.ChromeDriver";
                driverExecutable = "chromedriver";
                seleniumDriverProperty = "webdriver.chrome.driver";
                WebDriverManager.chromedriver().setup();
                break;
            case Firefox:
                driverClassName = "firefox.FirefoxDriver";
                driverExecutable = "geckodriver";
                seleniumDriverProperty = "webdriver.gecko.driver";
                WebDriverManager.firefoxdriver().setup();
            default:
                throw new Exception("Unknown browser");
        }

        try {
            return (T) Class.forName("org.openqa.selenium." + driverClassName).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new Exception("Error while instatiating driver " + browser.toString());
        }
    }
}
