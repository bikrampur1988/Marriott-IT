package com.marriott.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MarriotBonVoyDashboard {

    private WebDriver driver;
    private WebDriverWait wait;

    public MarriotBonVoyDashboard(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@data-testid='ui-library-HeaderLogo']//a[@aria-label='Marriott Bonvoy']")
    private WebElement marriottBonvoyHeaderLogo;

    @FindBy(xpath = "//a[@role='menuitem' and @aria-label='Book']")
    private WebElement bookMenuLink;

    @FindBy(xpath = "//a[@role='menuitem' and @aria-label='Offers']")
    private WebElement offersMenuLink;

    @FindBy(xpath = "//*[self::a or self::button][contains(normalize-space(),'Find a Hotel')]")
    private WebElement findAHotelLink;

    //@FindBy(xpath = "//a[@data-dialog-id='m-header-signin-dialog-header']")
    //private WebElement headerSignInLin;

    @FindBy (xpath = "//input[@aria-label='email or member number']")
    private WebElement emailAddress;

    @FindBy (xpath = "//input[@aria-label='sign in password']")
    private WebElement Password;

    @FindBy(xpath = "//span[@aria-hidden='true' and normalize-space()='Sign In or Join']")
    private WebElement signInButton;

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isMarriottBonvoyHeaderLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(marriottBonvoyHeaderLogo)).isDisplayed();
    }

    public void clickBookMenuLink() {
        wait.until(ExpectedConditions.elementToBeClickable(bookMenuLink)).click();
    }

    public void clickOffersMenuLink() {
        wait.until(ExpectedConditions.elementToBeClickable(offersMenuLink)).click();
    }

    public FindaHotelPage clickFindAHotel() {
        wait.until(ExpectedConditions.elementToBeClickable(findAHotelLink)).click();
        return new FindaHotelPage(driver);
    }

    public void loginToMarriottBonVoy(String email, String password) {

        
        WebElement headerSignInLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-dialog-id='m-header-signin-dialog-header']")));
        
        headerSignInLink.click();
        

        wait.until(ExpectedConditions.visibilityOf(emailAddress)).clear();
        emailAddress.sendKeys(email);

        wait.until(ExpectedConditions.visibilityOf(Password)).clear();
        Password.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    public SignInPage clickSignInOrJoin() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
        return new SignInPage(driver);
    }
}








        
