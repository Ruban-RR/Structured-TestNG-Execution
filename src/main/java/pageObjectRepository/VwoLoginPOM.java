package pageObjectRepository;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VwoLoginPOM {
	private WebDriver driver;
	@FindBy(id="login-username")
	private WebElement usernameField;

	@FindBy(id="login-password")
	private WebElement passwordField;

	@FindBy(id="js-login-btn")
	private WebElement loginButton;

	@FindBy(id="js-notification-box-msg")
	public WebElement errMessage;

	@FindBy(xpath="//*[@id=\"main-container\"]/div/div/div[1]/div[1]/h1")
	public WebElement loginMsg;

	public VwoLoginPOM(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void enterUserName(String username) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-username")));
		usernameField.sendKeys(username);
	}

	public void enterPassword(String password) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-username")));
		passwordField.sendKeys(password);
	}

	public void clickLoginButton() {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-username")));
		loginButton.click();
	}

	public Boolean isErrorDisplayed() {
		return errMessage.isDisplayed();
	}

	public Boolean isLoginMsgDisplayed() {
		return loginMsg.isDisplayed();
	}
	public void implicitWait(int sec) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
	}

}
