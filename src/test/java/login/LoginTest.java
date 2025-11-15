package login;

import base.BaseTest;
import components.HeaderBar;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @Test
    public void VerifyLoginPageDisplayedTest() {
        HeaderBar headerBar = new HeaderBar(driver);
        LoginPage loginPage = headerBar.clickOnLoginLink();
        Assert.assertTrue(loginPage.getPageTitleElement().contains("Welcome, Please Sign In!"));
    }

    @Test
    public void VerifyUserLoginSuccessfulTest(){
        HeaderBar headerBar = new HeaderBar(driver);
        LoginPage loginPage = headerBar.clickOnLoginLink();

        loginPage.enterUserName("testaffaires8@gmail.com");
        loginPage.enterPassword("Demo-shop-1234");

        HomePage homePage = loginPage.clickLoginButton();

        try {
            Alert alert = driver.switchTo().alert();
            logger.warn("Unexpected alert found: {}", alert.getText());
            alert.accept();
        } catch (NoAlertPresentException e) {
            logger.info("No alert present after login.");
        }

        Assert.assertTrue(headerBar.getWebsiteLogoElement());
        logger.info("User Login Successful!");
    }
}
