package com.marriott.ui.driver;

import com.marriott.ui.config.SettingsReader;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.Locale;

public final class DriverFactory {

    private DriverFactory() {
        // Prevent creation of DriverFactory objects.
    }


    /*
     * =========================================================
     * CREATE DRIVER
     * =========================================================
     *
     * Execution behavior:
     *
     * mode=local
     *
     *      ChromeDriver / FirefoxDriver
     *              |
     *              v
     *      Browser on local machine
     *
     *
     * mode=pipeline
     *
     *      RemoteWebDriver
     *              |
     *              v
     *      Selenium Grid Hub
     *              |
     *              v
     *      Browser Node
     */

    public static WebDriver createDriver() {

        String browser =
                SettingsReader.getBrowser();


        if (browser == null || browser.isBlank()) {

            throw new IllegalStateException(
                    "Browser is missing from the settings configuration."
            );
        }


        System.out.println(
                "Execution mode: "
                        + SettingsReader.getMode()
        );


        System.out.println(
                "Configured browser: "
                        + browser
        );


        WebDriver driver;


        switch (
                browser
                        .trim()
                        .toLowerCase(Locale.ROOT)
        ) {

            case "chrome":

                driver = createChromeDriver();

                break;


            case "firefox":

                driver = createFirefoxDriver();

                break;


            default:

                throw new IllegalArgumentException(
                        "Unsupported browser: "
                                + browser
                                + ". Supported browsers are: chrome and firefox."
                );
        }


        configureDriver(driver);


        return driver;
    }


    /*
     * =========================================================
     * CHROME DRIVER
     * =========================================================
     */

    private static WebDriver createChromeDriver() {

        ChromeOptions chromeOptions =
                new ChromeOptions();


        /*
         * EAGER waits until the DOM is ready but does not
         * wait for every image, analytics call, advertisement,
         * or background network request to complete.
         */
        chromeOptions.setPageLoadStrategy(
                PageLoadStrategy.EAGER
        );


        /*
         * Headless configuration.
         *
         * Pipeline XML currently uses:
         *
         * <headless>true</headless>
         */
        if (SettingsReader.isHeadless()) {

            chromeOptions.addArguments(
                    "--headless=new"
            );

            chromeOptions.addArguments(
                    "--window-size=1920,1080"
            );
        }


        /*
         * General Chrome configuration.
         */
        chromeOptions.addArguments(
                "--remote-allow-origins=*"
        );

        chromeOptions.addArguments(
                "--disable-notifications"
        );

        chromeOptions.addArguments(
                "--disable-popup-blocking"
        );

        chromeOptions.addArguments(
                "--disable-dev-shm-usage"
        );

        chromeOptions.addArguments(
                "--no-default-browser-check"
        );

        chromeOptions.addArguments(
                "--no-first-run"
        );

        chromeOptions.addArguments(
                "--disable-background-networking"
        );


        /*
         * =====================================================
         * PIPELINE EXECUTION
         * =====================================================
         *
         * Jenkins does NOT create Chrome locally.
         *
         * Instead:
         *
         * Jenkins
         *      |
         *      v
         * RemoteWebDriver
         *      |
         *      v
         * Selenium Hub
         *      |
         *      v
         * Chrome Node
         */

        if (SettingsReader.isPipelineMode()) {

            System.out.println(
                    "Creating RemoteWebDriver for Selenium Grid."
            );

            System.out.println(
                    "Grid URL: "
                            + SettingsReader.getGridUrl()
            );


            return createRemoteDriver(
                    chromeOptions
            );
        }


        /*
         * =====================================================
         * LOCAL EXECUTION
         * =====================================================
         *
         * Selenium Manager automatically resolves the
         * compatible ChromeDriver.
         */

        System.out.println(
                "Creating local ChromeDriver."
        );


        return new ChromeDriver(
                chromeOptions
        );
    }


    /*
     * =========================================================
     * FIREFOX DRIVER
     * =========================================================
     */

    private static WebDriver createFirefoxDriver() {

        FirefoxOptions firefoxOptions =
                new FirefoxOptions();


        firefoxOptions.setPageLoadStrategy(
                PageLoadStrategy.EAGER
        );


        if (SettingsReader.isHeadless()) {

            firefoxOptions.addArguments(
                    "-headless"
            );

            firefoxOptions.addArguments(
                    "--width=1920"
            );

            firefoxOptions.addArguments(
                    "--height=1080"
            );
        }


        /*
         * Pipeline execution through Selenium Grid.
         *
         * NOTE:
         * Our current Docker Compose configuration contains
         * only a Chrome Node.
         *
         * Firefox Grid execution will require a Firefox Node
         * to be added later.
         */

        if (SettingsReader.isPipelineMode()) {

            System.out.println(
                    "Creating RemoteWebDriver for Selenium Grid."
            );

            System.out.println(
                    "Grid URL: "
                            + SettingsReader.getGridUrl()
            );


            return createRemoteDriver(
                    firefoxOptions
            );
        }


        /*
         * Local Firefox execution.
         */

        System.out.println(
                "Creating local FirefoxDriver."
        );


        return new FirefoxDriver(
                firefoxOptions
        );
    }


    /*
     * =========================================================
     * REMOTE WEBDRIVER
     * =========================================================
     *
     * Creates the RemoteWebDriver session against
     * Selenium Grid.
     */

    private static WebDriver createRemoteDriver(
            org.openqa.selenium.Capabilities capabilities
    ) {

        try {

            return new RemoteWebDriver(
                    URI.create(
                            SettingsReader.getGridUrl()
                    ).toURL(),
                    capabilities
            );


        } catch (MalformedURLException e) {

            throw new RuntimeException(
                    "Invalid Selenium Grid URL: "
                            + SettingsReader.getGridUrl(),
                    e
            );
        }
    }


    /*
     * =========================================================
     * COMMON DRIVER CONFIGURATION
     * =========================================================
     */

    private static void configureDriver(
            WebDriver driver
    ) {

        driver.manage()
                .timeouts()
                .implicitlyWait(
                        Duration.ofSeconds(
                                SettingsReader.getImplicitWait()
                        )
                );


        driver.manage()
                .timeouts()
                .pageLoadTimeout(
                        Duration.ofSeconds(
                                SettingsReader.getPageLoadTimeout()
                        )
                );


        driver.manage()
                .timeouts()
                .scriptTimeout(
                        Duration.ofSeconds(60)
                );


        /*
         * Only maximize when:
         *
         * maximize=true
         *
         * AND
         *
         * headless=false
         */

        if (
                SettingsReader.isMaximize()
                        &&
                !SettingsReader.isHeadless()
        ) {

            driver.manage()
                    .window()
                    .maximize();
        }
    }
}