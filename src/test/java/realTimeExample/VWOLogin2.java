package realTimeExample;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.time.Duration;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjectRepository.Base;

public class VWOLogin2 extends Base{
	private WebDriver driver;
	@Test
	@Parameters({"email"})
	public void validEmailAndEmptyPassword(String email) {
		loginPage.enterUserName(email);
		loginPage.enterPassword("");
		loginPage.clickLoginButton();
		loginPage.implicitWait(5);
		Assert.assertTrue(loginPage.isErrorDisplayed(),"Error message should be shown");
	}
	
	@Test
	@Parameters({"pass"})
	public void emptyEmailAndValidPassword(String password) {
		loginPage.enterUserName("");
		loginPage.enterPassword("pass");
		loginPage.clickLoginButton();
		loginPage.implicitWait(5);
		Assert.assertTrue(loginPage.isErrorDisplayed(),"Error message should be shown");
	}
	
	@Test
	public void emptyEmailAndEmptyPassword() {
		loginPage.enterUserName("");
		loginPage.enterPassword("");
		loginPage.clickLoginButton();
		loginPage.implicitWait(5);
		Assert.assertTrue(loginPage.isErrorDisplayed(),"Error message should be shown");
	}
	
	@Test
	@Parameters({"pass"})
	public void emailWithSpaceInBetween(String pass) {
		loginPage.enterUserName("sample address@gmail.com");
		loginPage.enterPassword(pass);
		loginPage.clickLoginButton();
		loginPage.implicitWait(5);
		Assert.assertTrue(loginPage.isErrorDisplayed(),"Error message should be shown");
	}
	
	
}
