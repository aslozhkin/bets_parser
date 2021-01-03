package ru.allbets.bets_parser.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class DriverManager {
    ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        if (driver.get() == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
//            options.addArguments("start-maximized");
            options.addArguments("--headless");
            driver.set(new ChromeDriver(options));
            driver.get().manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
            driver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
        return driver.get();
    }

    public DriverManager() {
    }
}
