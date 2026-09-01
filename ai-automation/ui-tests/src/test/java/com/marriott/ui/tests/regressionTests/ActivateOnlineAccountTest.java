package com.marriott.ui.tests.regressionTests;

import com.marriott.ui.base.BaseTest;
import com.marriott.ui.pages.ActivateOnlineAccountPage;
import com.marriott.ui.pages.MarriotBonVoyDashboard;
import com.marriott.ui.pages.SignInPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.logging.Logger;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class ActivateOnlineAccountTest extends BaseTest {

private static final Logger LOGGER = Logger.getLogger(DashboardTest.class.getName());

private void logger(String message) {
    LOGGER.info(message);
}

private MarriotBonVoyDashboard dashboard;
private SignInPage signInPage;
private ActivateOnlineAccountPage activateOnlineAccountPage;

private static final String MEMBER_NUMBER = "123456789";
private static final String FIRST_NAME = "Sabiha";
private static final String LAST_NAME = "Rahman";
private static final String ZIP_POSTAL_CODE = "20155";


    /*
     * Initialize only the page where the test begins.
     *
     * BaseTest already creates the driver
     * and opens the Marriott application.
     */
    @BeforeMethod
    public void initializePageObjects() {
    logger("Precondition: Initialize the list of Marriot Bon Voy Page");
    dashboard = new MarriotBonVoyDashboard(driver);
    signInPage = new SignInPage(driver);
    activateOnlineAccountPage = new ActivateOnlineAccountPage(driver);
    }

    @Test
    public void testActivateOnlineAccount() throws InterruptedException {

    logger("Step-1: Verify user is on Marriott Bonvoy Dashboard");
    assertEquals(dashboard.isMarriottBonvoyHeaderLogoDisplayed(),true,"Marriott Bonvoy Dashboard was not displayed.");

    logger("Step-2: Click Sign In or Join button");
    signInPage = dashboard.clickSignInOrJoin();

    logger("Step-3: Verify Sign In page object initialized");
    assertNotNull(signInPage,"Sign In page object was not initialized.");

    logger("Step-4: Verify Activate online account link is displayed");
    assertEquals(
        signInPage.isActivateOnlineAccountLinkDisplayed(),
            true,
                "Activate online account link was not displayed."
        );

    logger("Step-5: Click Activate online account link");
    activateOnlineAccountPage = signInPage.clickActivateOnlineAccount();

    logger("Step-6: Verify Activate Online Account page object initialized");
    assertNotNull(
        activateOnlineAccountPage,
            "Activate Online Account page object was not initialized."
        );
    
    logger("Step-7: Enter Member Number");
     logger(
                "Step-5: Verify Member Number field is displayed"
        );

        assertEquals(
                activateOnlineAccountPage.isMemberNumberFieldDisplayed(),
                true,
                "Member Number field was not displayed."
        );

    activateOnlineAccountPage.enterMemberNumber(MEMBER_NUMBER);

    assertEquals(
                activateOnlineAccountPage.getEnteredMemberNumber(),
                MEMBER_NUMBER,
                "Entered Member Number did not match the expected value."
        );

    logger("Step-8: Enter First Name");
    assertEquals(
                activateOnlineAccountPage.isFirstNameFieldDisplayed(),
                true,
                "First Name field was not displayed."
        );

    activateOnlineAccountPage.enterFirstName(FIRST_NAME);

    assertEquals(
                activateOnlineAccountPage.getEnteredFirstName(),
                FIRST_NAME,
                "Entered First Name did not match the expected value."
        );

    logger("Step-9: Enter Last Name");
    assertEquals(
                activateOnlineAccountPage.isLastNameFieldDisplayed(),
                true,
                "Last Name field was not displayed."
        );

    activateOnlineAccountPage.enterLastName(LAST_NAME);

     assertEquals(
                activateOnlineAccountPage.getEnteredLastName(),
                LAST_NAME,
                "Entered Last Name did not match the expected value."
        );

    logger("Step-10: Enter Zip or Postal Code");
     assertEquals(
                activateOnlineAccountPage.isZipPostalCodeFieldDisplayed(),
                true,
                "Zip or Postal Code field was not displayed."
        );

    activateOnlineAccountPage.enterZipPostalCode(ZIP_POSTAL_CODE);

    assertEquals(
                activateOnlineAccountPage.getEnteredZipPostalCode(),
                ZIP_POSTAL_CODE,
                "Entered Zip or Postal Code did not match the expected value."
        );

    logger("Step-11: Verify Member Number accepts maximum 9 characters");
        assertEquals(
                activateOnlineAccountPage.getMemberNumberMaxLength(),
                "9",
                "Member Number maxlength attribute did not match."
        );

    logger("Step-12: Verify First Name accepts maximum 20 characters");
        assertEquals(
                activateOnlineAccountPage.getFirstNameMaxLength(),
                "20",
                "First Name maxlength attribute did not match."
        );

    logger("Step-13: Verify Last Name accepts maximum 25 characters");
        assertEquals(
                activateOnlineAccountPage.getLastNameMaxLength(),
                "25",
                "Last Name maxlength attribute did not match."
    );

    logger("Step-14: Verify Zip or Postal Code accepts maximum 10 characters");
        assertEquals(
                activateOnlineAccountPage.getZipPostalCodeMaxLength(),
                "10",
                "Zip or Postal Code maxlength attribute did not match."
        );

    logger("Step-15: Verify Continue button is displayed");
        assertEquals(
                activateOnlineAccountPage.isContinueButtonDisplayed(),
                true,
                "Continue button was not displayed."
        );

    logger("Step-16: Verify Continue button text");
        assertEquals(
                activateOnlineAccountPage.getContinueButtonText(),
                "Continue",
                "Continue button text did not match."
        );

    logger("Step-17: Verify Continue button is enabled");
        assertEquals(
                activateOnlineAccountPage.isContinueButtonEnabled(),
                true,
                "Continue button was not enabled."
        );

    logger("Step-18: Click Continue button");
    activateOnlineAccountPage.clickContinueButton();
    Thread.sleep(5000);

}}