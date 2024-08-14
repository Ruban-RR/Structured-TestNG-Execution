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
public class VWOlogin extends Base {
	private WebDriver driver;
	@Test
	@Parameters({"email","pass"})
	public void validEmailandPassword(String email,String pass) {
		loginPage.enterUserName(email);
		loginPage.enterPassword(pass);
		loginPage.clickLoginButton();
		loginPage.implicitWait(10);
		Assert.assertTrue(loginPage.isLoginMsgDisplayed(),"Unsucessfull login with valid mail and password");
	}
	@Test(enabled=true)
	@Parameters({"email","invalidPass"})
	public void validEmailandInvalidPassword(String email,String Invalidpass) {
		loginPage.enterUserName(email);
		loginPage.enterPassword(Invalidpass);
		loginPage.clickLoginButton();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(loginPage.errMessage));
		Assert.assertTrue(loginPage.isErrorDisplayed(),"Error message should be shown");//assertTrue("Error message should be shown",loginPage.isErrorDisplayed());
	}
	@Test(enabled=true)
	@Parameters({"Invalidemail","pass"})
	public void invalidEmailandValidPassword(String Invalidemail,String pass) {
		loginPage.enterUserName(Invalidemail);
		loginPage.enterPassword(pass);
		loginPage.clickLoginButton();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(loginPage.errMessage));
		Assert.assertTrue(loginPage.isErrorDisplayed(),"Error message should be shown");
	}
	@Test(enabled=true)
	@Parameters({"Invalidemail","invalidPass"})
	public void invalidEmailandInvalidPassword(String Invalidemail,String Invalidpass) {
		loginPage.enterUserName(Invalidemail);
		loginPage.enterPassword(Invalidpass);
		loginPage.clickLoginButton();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(loginPage.errMessage));
		Assert.assertTrue(loginPage.isErrorDisplayed(),"Error message should be shown");
	}
}
