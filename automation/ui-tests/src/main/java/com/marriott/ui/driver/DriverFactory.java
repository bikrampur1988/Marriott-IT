package com.marriott.ui.driver;

import com.marriott.ui.config.SettingsReader;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.Locale;

public final class DriverFactory {

    private DriverFactory() {
        // Prevent creation of DriverFactory objects.
    }

    /**
     * Creates and configures a WebDriver instance based on the browser
     * configured in the settings file.
     *
     * Selenium Manager automatically resolves the compatible browser driver.
     *
     * @return configured WebDriver instance
     */
    public static WebDriver createDriver() {

        String browser = SettingsReader.getBrowser();

        if (browser == null || browser.isBlank()) {
            throw new IllegalStateException(
                    "Browser is missing from the settings configuration."
            );
        }

        WebDriver driver;

        switch (browser.trim().toLowerCase(Locale.ROOT)) {

            case "chrome":
                driver = createChromeDriver();
                break;

            case "firefox":
                driver = createFirefoxDriver();
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser
                                + ". Supported browsers are: chrome and firefox."
                );
        }

        configureDriver(driver);

        return driver;
    }

    /**
     * Creates ChromeDriver using Selenium Manager.
     */
    private static WebDriver createChromeDriver() {

        ChromeOptions chromeOptions = new ChromeOptions();

        /*
         * EAGER waits for the HTML document to load, but does not wait
         * for every image, advertisement, analytics call, or background
         * resource to finish loading.
         */
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);

        if (SettingsReader.isHeadless()) {
            chromeOptions.addArguments("--headless=new");
            chromeOptions.addArguments("--window-size=1920,1080");
        }

        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-default-browser-check");
        chromeOptions.addArguments("--no-first-run");
        chromeOptions.addArguments("--disable-background-networking");

        return new ChromeDriver(chromeOptions);
    }

    /**
     * Creates FirefoxDriver using Selenium Manager.
     */
    private static WebDriver createFirefoxDriver() {

        FirefoxOptions firefoxOptions = new FirefoxOptions();

        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);

        if (SettingsReader.isHeadless()) {
            firefoxOptions.addArguments("-headless");
            firefoxOptions.addArguments("--width=1920");
            firefoxOptions.addArguments("--height=1080");
        }

        return new FirefoxDriver(firefoxOptions);
    }

    /**
     * Applies common timeout and window configuration.
     */
    private static void configureDriver(WebDriver driver) {

        driver.manage()
                .timeouts()
                .implicitlyWait(
                        Duration.ofSeconds(SettingsReader.getImplicitWait())
                );

        driver.manage()
                .timeouts()
                .pageLoadTimeout(
                        Duration.ofSeconds(SettingsReader.getPageLoadTimeout())
                );

        driver.manage()
                .timeouts()
                .scriptTimeout(Duration.ofSeconds(60));

        if (SettingsReader.isMaximize() && !SettingsReader.isHeadless()) {
            driver.manage().window().maximize();
        }
    }
}