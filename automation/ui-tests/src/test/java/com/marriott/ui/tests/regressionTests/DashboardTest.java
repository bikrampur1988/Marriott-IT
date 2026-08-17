package com.marriott.ui.tests.regressionTests;

import com.marriott.ui.base.BaseTest;
import com.marriott.ui.pages.MarriotBonVoyDashboard;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {

private MarriotBonVoyDashboard dashboard;

@BeforeMethod
public void initializePageObjects() {
dashboard = new MarriotBonVoyDashboard(driver);

}

@Test(groups = {"Regression"},priority = 1, description = "Verify the Marriott Bonvoy Dashboard page title.")
    public void verifyDashboardPageTitle() {
        String actualTitle = dashboard.getPageTitle();
        Assert.assertTrue(actualTitle.contains("Marriott Bonvoy"),"Dashboard page title validation failed. Actual title: " + actualTitle);

}

@Test(groups = {"Regression"}, priority = 2, description = "Verify the Marriott Bonvoy Dashboard header logo is displayed.")
public void verifyDashboardHeaderLogoIsDisplayed() {
    Assert.assertTrue(dashboard.isMarriottBonvoyHeaderLogoDisplayed(), "Marriott Bonvoy header logo is not displayed.");

}

}