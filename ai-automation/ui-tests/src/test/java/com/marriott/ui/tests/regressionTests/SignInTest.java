package com.marriott.ui.tests.regressionTests;

import com.marriott.ui.base.BaseTest;
import com.marriott.ui.pages.MarriotBonVoyDashboard;
import com.marriott.ui.pages.SignInPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.logging.Logger;
import org.testng.Assert;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class SignInTest extends BaseTest {

private static final Logger LOGGER = Logger.getLogger(DashboardTest.class.getName());

private void logger(String message) {
LOGGER.info(message);
}

private MarriotBonVoyDashboard dashboard;
private SignInPage signInPage;

@BeforeMethod
public void initializePageObjects() {
    dashboard = new MarriotBonVoyDashboard(driver);
    signInPage = new SignInPage(driver);
}

@Test(groups = {"Regression"}, priority = 101, description = "Verify able click on Sign in Button")
public void testVerifySignInNavigation() {
logger("Step-1: Verify user is on Marriott Bonvoy Dashboard");
boolean dashboardDisplayed = dashboard.isMarriottBonvoyHeaderLogoDisplayed();
assertEquals(dashboardDisplayed,true,"Marriott Bonvoy Dashboard was not displayed successfully.");

logger("Step-2: Click Sign In or Join button");
signInPage = dashboard.clickSignInOrJoin();

logger("Step-3: Verify Sign In page object initialized successfully");
assertNotNull(signInPage,"Sign In page object was not initialized.");
}

}