package superAdmin.login.positiveTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.loginPage;
import java.time.Duration;

public class login {
    WebDriver driver;
    public loginPage loginPage;

    @BeforeMethod
    public void loadUrl() throws InterruptedException {
        driver = new ChromeDriver();
        loginPage = new loginPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://tentname.stagingsuperadminfe.gces.parallaxtec.dev/user-selection");
        Thread.sleep(2000);

    }

    @Test
    public void loginWithValidCredentials() throws InterruptedException{
        loginPage.login();

        boolean urlVerification = driver.getCurrentUrl().contains("dashboard");
        Assert.assertTrue(urlVerification,"Expecting login success but not navigated to dashboard");

        Assert.assertEquals("Successfully logged in", loginPage.checkSuccessfulLogin(), "Passed");
        System.out.println("Test passed: Actual and expected messages match!");

    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
