package com.marriott.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActivateOnlineAccountPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ActivateOnlineAccountPage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );

        PageFactory.initElements(driver, this);
    }


    /*
     * =========================================================
     * PAGE ELEMENTS
     * =========================================================
     */

    @FindBy(id = "memberNum")
    private WebElement memberNumberField;

    @FindBy(id = "firstname")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(name = "input-text-Zip/Postal Code")
    private WebElement zipPostalCodeField;

    @FindBy(xpath = "//button[@data-testid='continue-btn' and @aria-label='Continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//a[@href='/lookUpMemberNumber.mi' and normalize-space()='Look up member number']")
    private WebElement lookUpMemberNumberLink;


    /*
     * =========================================================
     * PAGE VALIDATION METHODS
     * =========================================================
     */

public boolean isMemberNumberFieldDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(memberNumberField)
        ).isDisplayed();
    }

public boolean isFirstNameFieldDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOf(firstNameField)
        ).isDisplayed();
    }

public boolean isLastNameFieldDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOf(lastNameField)
        ).isDisplayed();
    }

public boolean isZipPostalCodeFieldDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOf(zipPostalCodeField)
        ).isDisplayed();
    }

public boolean isContinueButtonDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(continueButton)
        ).isDisplayed();
    }

public boolean isContinueButtonEnabled() {

        return wait.until(
                ExpectedConditions.visibilityOf(continueButton)
        ).isEnabled();
    }

 /*
 * =========================================================
* INDIVIDUAL INPUT METHODS
* =========================================================
*/

public void enterMemberNumber(String memberNumber) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(memberNumberField)
        );
        element.clear();
        element.sendKeys(memberNumber);
    }

public void enterFirstName(String firstName) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(firstNameField)
        );
        element.clear();
        element.sendKeys(firstName);
    }

public void enterLastName(String lastName) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(lastNameField)
        );

        element.clear();
        element.sendKeys(lastName);
    }

public void enterZipPostalCode(String zipPostalCode) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(zipPostalCodeField)
        );

        element.clear();
        element.sendKeys(zipPostalCode);
    }

    /*
     * =========================================================
     * GET ENTERED FIELD VALUES
     * =========================================================
     *
     * These methods are useful for assertEquals validations.
     */

    public String getEnteredMemberNumber() {
    return memberNumberField.getAttribute("value");
    }

    public String getEnteredFirstName() {
    return firstNameField.getAttribute("value");
    }

    public String getEnteredLastName() {
    return lastNameField.getAttribute("value");
    }

    public String getEnteredZipPostalCode() {
    return zipPostalCodeField.getAttribute("value");
    }

    /*
     * =========================================================
     * ATTRIBUTE VALIDATIONS
     * =========================================================
     */

    public String getMemberNumberMaxLength() {
    return memberNumberField.getAttribute("maxlength");
    }

    public String getFirstNameMaxLength() {
    return firstNameField.getAttribute("maxlength");
    }

    public String getLastNameMaxLength() {
    return lastNameField.getAttribute("maxlength");
    }

    public String getZipPostalCodeMaxLength() {
    return zipPostalCodeField.getAttribute("maxlength");
    }

    public String getContinueButtonText() {
    return wait.until(
            ExpectedConditions.visibilityOf(continueButton)
        ).getText().trim();
    }

    public String getLookUpButtonText() {
    return wait.until(
            ExpectedConditions.visibilityOf(lookUpMemberNumberLink)
        ).getText().trim();
    }

    /*
     * =========================================================
     * CONTINUE ACTION
     * =========================================================
     */

    public void clickContinueButton() {
        wait.until(
            ExpectedConditions.elementToBeClickable(continueButton)
        ).click();
    }

    public void clickLookUpButton() {
        wait.until(
            ExpectedConditions.elementToBeClickable(lookUpMemberNumberLink)
        ).click();
    }

    /*
     * =========================================================
     * BASIC PAGE INFORMATION
     * =========================================================
     */

    public String getPageTitle() {
    return driver.getTitle();
    }

    public String getCurrentUrl() {
    return driver.getCurrentUrl();
    }

/*
 * =========================================================
 * LOOK UP MEMBER NUMBER METHODS
 * =========================================================
 */

public boolean isLookUpMemberNumberLinkDisplayed() {

    return wait.until(
            ExpectedConditions.visibilityOf(
                    lookUpMemberNumberLink
            )
    ).isDisplayed();
}


public boolean isLookUpMemberNumberLinkEnabled() {

    return wait.until(
            ExpectedConditions.visibilityOf(
                    lookUpMemberNumberLink
            )
    ).isEnabled();
}

public LookUpMemberNumberPage clickLookUpMemberNumber() {

    wait.until(
            ExpectedConditions.elementToBeClickable(
                    lookUpMemberNumberLink
            )
    ).click();

    return new LookUpMemberNumberPage(driver);
}
 

}