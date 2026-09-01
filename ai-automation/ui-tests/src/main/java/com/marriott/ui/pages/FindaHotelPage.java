package com.marriott.ui.pages;

import org.openqa.selenium.WebDriver;

public class FindaHotelPage {

    private WebDriver driver;

    public FindaHotelPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}