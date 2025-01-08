package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage {
    private WebDriver driver;
    String email ="superadmin@gmail.com";
    String password="12345@Aa";


    private By selectSuperAdmin = By.xpath("//span[normalize-space()='Super Admin']");

    // Selectors for Inputs
    private By emailField = By.xpath("//input[@placeholder='Email address']");
    private By passwordField = By.xpath("//input[@placeholder='Password']");

    // Selectors for Buttons
    private By loginButton = By.xpath("//button[text()='Log In']");

    // Selectors for validation messages
    private By successMessage =By.xpath("//*[@id=\"_rht_toaster\"]/div/div/div[1]/div/div[2]/p[2]");
    private By emailIsRequired =By.xpath("//div[normalize-space()='Email is required']");
    private By passwordIsRequired =By.xpath("//div[normalize-space()='Password is required']");
    private By invalidEmailFormat =By.xpath("//div[normalize-space()=\"Invalid email format\"]");

    public loginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickSuperAdmin(){
        driver.findElement(selectSuperAdmin).click();
    }

    public void login() throws InterruptedException {
        Thread.sleep(3000);
        clickSuperAdmin();
        Thread.sleep(3000);
        enterEmailAddress(email);
        enterPassword(password);
        clickLogin();
        Thread.sleep(3000);
    }
    public void enterEmailAddress(String emailAddress){
        driver.findElement(emailField).sendKeys(emailAddress);
    }
    public void enterPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickLogin(){
        driver.findElement(loginButton).click();
    }
    public String checkEmailIsRequired(){
        return driver.findElement(emailIsRequired).getText();
    }
    public String checkPasswordIsRequired(){
        return driver.findElement(passwordIsRequired).getText();
    }
    public String checkInvalidEmailFormat(){
        return driver.findElement(invalidEmailFormat).getText();
    }
    public String checkSuccessfulLogin(){
        return driver.findElement(successMessage).getText();
    }
}