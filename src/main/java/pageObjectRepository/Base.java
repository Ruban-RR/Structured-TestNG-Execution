package pageObjectRepository;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utils.Constants;

public class Base {
	public static WebDriver driver;
	private ExtentHtmlReporter sparkReporter;
	private ExtentReports extent;
	private ExtentTest logger;
	public VwoLoginPOM loginPage;
	@BeforeTest
	public void beforeTestMethod() {
		sparkReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+File.separator+"VWORubanExtendReport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		sparkReporter.config().setTheme(Theme.DARK);
		extent.setSystemInfo("HostName", "Ruban");
		extent.setSystemInfo("Username", "Root");
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Automation Test Results By Ruban");
	}
	@BeforeMethod
	@Parameters("browser")
	public void beforeMethod(String browser, Method testMethod) {
		logger = extent.createTest(testMethod.getName());
		setupDriver(browser);
		driver.manage().window().maximize();
		driver.get(Constants.url);
		driver.get("https://app.vwo.com/#/login");
		loginPage = new VwoLoginPOM(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterTest
	public void afterTest() {
		extent.flush();
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+"- Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable()+"- Test Case Failed", ExtentColor.RED));
		}else if(result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+"- Test Case Skipped", ExtentColor.ORANGE));
		}else if(result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+"- Test Case Passed", ExtentColor.GREEN));
		}
		driver.quit();
	}

	public void setupDriver(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			String driverPath = System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			//options.addArguments("--headless");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			options.merge(capabilities);
			driver = new ChromeDriver(options);
			
		}
		else if (browser.equalsIgnoreCase("firefox")) {
			String geckoPath = System.setProperty("webdriver.firefox.driver", "D:\\geckodriver-win64\\geckodriver.exe");
			FirefoxOptions ffoption = new FirefoxOptions();
			//ffoption.addArguments("--headless");
			driver = new FirefoxDriver(ffoption);
			
		}
		else if (browser.equalsIgnoreCase("edge")) {
			String edgePath = System.setProperty("webdriver.edge.driver", "D:\\edgedriver-win64\\msedgedriver.exe");
			EdgeOptions edgeoption = new EdgeOptions();
			//edgeoption.addArguments("--headless");
			driver = new EdgeDriver(edgeoption);
			
		}
	}
}