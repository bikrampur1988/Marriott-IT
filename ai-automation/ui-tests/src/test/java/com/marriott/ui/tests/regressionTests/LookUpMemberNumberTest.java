package com.marriott.ui.tests.regressionTests;

import com.marriott.ui.base.BaseTest;
import com.marriott.ui.pages.ActivateOnlineAccountPage;
import com.marriott.ui.pages.LookUpMemberNumberPage;
import com.marriott.ui.pages.MarriotBonVoyDashboard;
import com.marriott.ui.pages.SignInPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class LookUpMemberNumberTest extends BaseTest {

private static final Logger LOGGER =
        Logger.getLogger(LookUpMemberNumberTest.class.getName());

private void logger(String message) {
    LOGGER.info(message);
}

private MarriotBonVoyDashboard dashboard;
private SignInPage signInPage;
private ActivateOnlineAccountPage activateOnlineAccountPage;
private LookUpMemberNumberPage lookUpMemberNumberPage;

private static final String EMAIL = "sabiha123.test@example.com";
private static final String FIRST_NAME = "Sabiha Akther";
private static final String LAST_NAME = "Rahman";


@BeforeMethod
public void initializePageObjects() {

    logger("Precondition: Initialize the list of Marriott Bonvoy Page Objects");

    dashboard = new MarriotBonVoyDashboard(driver);
    signInPage = new SignInPage(driver);
    activateOnlineAccountPage = new ActivateOnlineAccountPage(driver);
    lookUpMemberNumberPage = new LookUpMemberNumberPage(driver);
}

@Test
public void testLookUpMemberNumber() throws InterruptedException {

    logger("Step-1: Verify user is on Marriott Bonvoy Dashboard");

    assertEquals(
            dashboard.isMarriottBonvoyHeaderLogoDisplayed(),
            true,
            "Marriott Bonvoy Dashboard was not displayed."
    );

    logger("Step-2: Click Sign In or Join button");
    signInPage = dashboard.clickSignInOrJoin();

    logger("Step-3: Verify Sign In page object initialized");

    assertNotNull(
            signInPage,
            "Sign In page object was not initialized."
    );

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

    logger("Step-7: Verify Look up member number link is displayed");

    assertEquals(
            activateOnlineAccountPage.isLookUpMemberNumberLinkDisplayed(),
            true,
            "Look up member number link was not displayed."
    );

    logger("Step-8: Verify Look up member number link is enabled");

    assertEquals(
            activateOnlineAccountPage.isLookUpMemberNumberLinkEnabled(),
            true,
            "Look up member number link was not enabled."
    );

    logger("Step-9: Click Look up member number link");
    lookUpMemberNumberPage = activateOnlineAccountPage.clickLookUpMemberNumber();

    logger("Step-10: Verify Look Up Member Number page object initialized");

    assertNotNull(
            lookUpMemberNumberPage,
            "Look Up Member Number page object was not initialized."
    );

    logger("Step-11: Verify Look Up Member Number heading is displayed");

    assertEquals(
            lookUpMemberNumberPage.isLookUpMemberNumberHeadingDisplayed(),
            true,
            "Look Up Member Number heading was not displayed."
    );

    logger("Step-12: Verify Look Up Member Number heading text");

    assertEquals(
            lookUpMemberNumberPage.getLookUpMemberNumberHeadingText(),
            "Look Up Member Number",
            "Look Up Member Number heading text did not match."
    );

    logger("Step-13: Verify Email field is displayed");

    assertEquals(
            lookUpMemberNumberPage.isEmailFieldDisplayed(),
            true,
            "Email field was not displayed."
    );

    logger("Step-14: Verify Email field is enabled");

    assertEquals(
            lookUpMemberNumberPage.isEmailFieldEnabled(),
            true,
            "Email field was not enabled."
    );

    logger("Step-15: Enter Email");

    lookUpMemberNumberPage.enterEmail(EMAIL);

    logger("Step-16: Verify entered Email");

    assertEquals(
            lookUpMemberNumberPage.getEnteredEmail(),
            EMAIL,
            "Entered Email did not match the expected value."
    );

    logger("Step-17: Verify Email accepts maximum 80 characters");

    assertEquals(
            lookUpMemberNumberPage.getEmailMaxLength(),
            "80",
            "Email maxlength attribute did not match."
    );

    logger("Step-18: Verify First Name field is displayed");

    assertEquals(
            lookUpMemberNumberPage.isFirstNameFieldDisplayed(),
            true,
            "First Name field was not displayed."
    );

    logger("Step-19: Verify First Name field is enabled");

    assertEquals(
            lookUpMemberNumberPage.isFirstNameFieldEnabled(),
            true,
            "First Name field was not enabled."
    );

    logger("Step-20: Enter First Name");

    lookUpMemberNumberPage.enterFirstName(FIRST_NAME);

    logger("Step-21: Verify entered First Name");

    assertEquals(
            lookUpMemberNumberPage.getEnteredFirstName(),
            FIRST_NAME,
            "Entered First Name did not match the expected value."
    );

    logger("Step-22: Verify First Name accepts maximum 20 characters");

    assertEquals(
            lookUpMemberNumberPage.getFirstNameMaxLength(),
            "20",
            "First Name maxlength attribute did not match."
    );

    logger("Step-23: Verify Last Name field is displayed");

    assertEquals(
            lookUpMemberNumberPage.isLastNameFieldDisplayed(),
            true,
            "Last Name field was not displayed."
    );

    logger("Step-24: Verify Last Name field is enabled");

    assertEquals(
            lookUpMemberNumberPage.isLastNameFieldEnabled(),
            true,
            "Last Name field was not enabled."
    );

    logger("Step-25: Enter Last Name");

    lookUpMemberNumberPage.enterLastName(LAST_NAME);

    logger("Step-26: Verify entered Last Name");

    assertEquals(
            lookUpMemberNumberPage.getEnteredLastName(),
            LAST_NAME,
            "Entered Last Name did not match the expected value."
    );

    logger("Step-27: Verify Last Name accepts maximum 25 characters");

    assertEquals(
            lookUpMemberNumberPage.getLastNameMaxLength(),
            "25",
            "Last Name maxlength attribute did not match."
    );

    logger("Step-28: Verify Submit button is displayed");

    assertEquals(
            lookUpMemberNumberPage.isSubmitButtonDisplayed(),
            true,
            "Submit button was not displayed."
    );

    logger("Step-29: Verify Submit button text");

    assertEquals(
            lookUpMemberNumberPage.getSubmitButtonText(),
            "Submit",
            "Submit button text did not match."
    );

    logger("Step-30: Verify Submit button is enabled");

    assertEquals(
            lookUpMemberNumberPage.isSubmitButtonEnabled(),
            true,
            "Submit button was not enabled."
    );

    logger("Step-31: Click Submit button");
    lookUpMemberNumberPage.clickSubmitButton();
            
    logger("Step-32: Verify Contact Us link is displayed");

    assertEquals(
            lookUpMemberNumberPage.isContactUsLinkDisplayed(),
            true,
            "Contact Us link was not displayed."
    );

    logger("Step-33: Verify Contact Us link is enabled");

    assertEquals(
            lookUpMemberNumberPage.isContactUsLinkEnabled(),
            true,
            "Contact Us link was not enabled."
    );

    /*
     * Country / Region dropdown automation
     * will be handled separately by Sabiha.
     *
     */

    Thread.sleep(5000);
}

}