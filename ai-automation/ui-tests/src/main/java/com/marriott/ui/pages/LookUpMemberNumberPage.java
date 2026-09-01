package com.marriott.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LookUpMemberNumberPage {

    private WebDriverWait wait;

    /*
     * =========================================================
     * CONSTRUCTOR
     * =========================================================
     *
     * This Page Object is initialized after WebDriver
     * switches from ActivateOnlineAccountPage to the
     * new Look Up Member Number browser tab.
     */

    public LookUpMemberNumberPage(WebDriver driver) {

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );

        /*
         * Initialize all @FindBy elements
         * belonging to this page.
         */
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h4[normalize-space()='Look Up Member Number']")
    private WebElement lookUpMemberNumberHeading;
     
    @FindBy(id = "input-Email")
    private WebElement emailField;

    @FindBy(id = "input-firstName")
    private WebElement firstNameField;

    @FindBy(id = "input-lastName")
    private WebElement lastNameField;
     
    @FindBy(xpath = "//div[@id='dropdownfp-country-code' and @role='combobox']")
    private WebElement countryRegionDropdown;

    @FindBy(css = "button[data-testid='submitButtonLabel']")
    private WebElement submitButton;

    @FindBy(xpath = "//a[contains(@href,'loyalty-customer-support.mi') and contains(normalize-space(),'Contact Us')]")
    private WebElement contactUsLink;


    /*
     * =========================================================
     * LOOK UP MEMBER NUMBER HEADING METHODS
     * =========================================================
     */

    public boolean isLookUpMemberNumberHeadingDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(
                        lookUpMemberNumberHeading
                )
        ).isDisplayed();
    }


    public String getLookUpMemberNumberHeadingText() {

        return wait.until(
                ExpectedConditions.visibilityOf(
                        lookUpMemberNumberHeading
                )
        ).getText().trim();
    }

    public boolean isEmailFieldDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(
                        emailField
                )
        ).isDisplayed();
    }

    public boolean isEmailFieldEnabled() {

        return wait.until(
                ExpectedConditions.visibilityOf(
                        emailField
                )
        ).isEnabled();
    }

    public void enterEmail(String email) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(
                        emailField
                )
        );

        element.clear();
        element.sendKeys(email);
    }

    public String getEnteredEmail() {

        return emailField.getAttribute("value");
    }

    public String getEmailMaxLength() {

        return emailField.getAttribute("maxlength");
    }

    public boolean isFirstNameFieldDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(
                        firstNameField
                )
        ).isDisplayed();
    }

    public boolean isFirstNameFieldEnabled() {

        return wait.until(
                ExpectedConditions.visibilityOf(
                        firstNameField
                )
        ).isEnabled();
    }

    public void enterFirstName(String firstName) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(
                        firstNameField
                )
        );

        element.clear();
        element.sendKeys(firstName);
    }

    public String getEnteredFirstName() {

        return firstNameField.getAttribute("value");
    }

    public String getFirstNameMaxLength() {

        return firstNameField.getAttribute("maxlength");
    }

    public boolean isLastNameFieldDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(
                        lastNameField
                )
        ).isDisplayed();
    }

    public boolean isLastNameFieldEnabled() {

        return wait.until(
                ExpectedConditions.visibilityOf(
                        lastNameField
                )
        ).isEnabled();
    }

    public void enterLastName(String lastName) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(
                        lastNameField
                )
        );

        element.clear();
        element.sendKeys(lastName);
    }

    public String getEnteredLastName() {

        return lastNameField.getAttribute("value");
    }

    public String getLastNameMaxLength() {

        return lastNameField.getAttribute("maxlength");
    }

    public boolean isSubmitButtonDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(
                        submitButton
                )
        ).isDisplayed();
    }

    public boolean isSubmitButtonEnabled() {

        return wait.until(
                ExpectedConditions.visibilityOf(
                        submitButton
                )
        ).isEnabled();
    }

    public String getSubmitButtonText() {

        return wait.until(
                ExpectedConditions.visibilityOf(
                        submitButton
                )
        ).getText().trim();
    }

    public void clickSubmitButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        submitButton
                )
        ).click();
    }

    public boolean isContactUsLinkDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(
                        contactUsLink
                )
        ).isDisplayed();
    }

    public boolean isContactUsLinkEnabled() {

        return wait.until(
                ExpectedConditions.visibilityOf(
                        contactUsLink
                )
        ).isEnabled();
    }
  
}