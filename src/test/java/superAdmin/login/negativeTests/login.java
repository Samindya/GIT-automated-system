package superAdmin.login.negativeTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.loginPage;

import java.time.Duration;

public class login {
    WebDriver driver;
    public pages.loginPage loginPage;

    @BeforeMethod
    public void loadUrl() throws InterruptedException {
        driver = new ChromeDriver();
        loginPage = new loginPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://tentname.stagingsuperadminfe.gces.parallaxtec.dev/user-selection");
        Thread.sleep(2000);
        loginPage.clickSuperAdmin();
    }

    @DataProvider(name="loginData")
    public Object [][] negativeLoginDataProvider(){

        String [][] data = {
                {"superadmin@gmail.com","invalidPassword","Invalid"}, //valid email and invalid password
                {"superadmin@gmail.com","","Password is required"}, //valid email and empty password
                {"invalidemail@gmail.com","12345@Aa","Invalid"}, //invalid email and valid password
                {"invalidemail@gmail.com","","Invalid"}, //invalid email and empty password
                {"invalidemail@gmail.com","invalidPassword","Invalid"}, //invalid email and invalid password
                {"invalidemail@","12345@Aa","Invalid email format"}, //invalid email format and valid password
                {"invalidemail@","","Invalid email format and Password is required"}, //invalid email format and empty password
                {"invalidemail@","invalidPassword","Invalid"}, //invalid email format and invalid password
                {"","12345@Aa","Email is required"}, //empty email and valid password
                {"","invalidPassword","Invalid"}, //empty email and invalid password
                {"","","Email and Password are required"} //empty email and empty password
        };
        return data;
    }

    @Test(dataProvider = "loginData")
    public void loginWithInvalidCredentials(String email, String pass, String expValidation) throws InterruptedException{
        loginPage.enterEmailAddress(email);
        loginPage.enterPassword(pass);
        loginPage.clickLogin();

        Thread.sleep(3000);

        if(expValidation.equals("Email is required")){
            Assert.assertEquals("Email is required", loginPage.checkEmailIsRequired(), "Passed");
            System.out.println("Test passed: Actual and expected messages match!");
        }else if(expValidation.equals("Password is required")){
            Assert.assertEquals("Password is required", loginPage.checkPasswordIsRequired(), "Passed");
            System.out.println("Test passed2: Actual and expected messages match!");
        }else if(expValidation.equals("Email and Password are required")){
            Assert.assertEquals("Email is required", loginPage.checkEmailIsRequired(), "Passed");
            Assert.assertEquals("Password is required", loginPage.checkPasswordIsRequired(), "Passed");
            System.out.println("Test passed3: Actual and expected messages match!");
        }else if(expValidation.equals("Invalid email format")) {
            Assert.assertEquals("Invalid email format", loginPage.checkInvalidEmailFormat(), "Passed");
            System.out.println("Test passed4: Actual and expected messages match!");
        }else if(expValidation.equals("Invalid email format and Password is required")) {
            Assert.assertEquals("Invalid email format", loginPage.checkInvalidEmailFormat(), "Passed");
            Assert.assertEquals("Password is required", loginPage.checkPasswordIsRequired(), "Passed");
            System.out.println("Test passed5: Actual and expected messages match!");
        }
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
