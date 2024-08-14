package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import pageObjectRepository.Base;

public class Listeners implements ITestListener{
	@Override  
	public void onTestStart(ITestResult result) {  
		// TODO Auto-generated method stub  
	}  

	@Override  
	public void onTestSuccess(ITestResult result) {  
		// TODO Auto-generated method stub  
		System.out.println("Success of test cases and its details are : "+result.getName());  
	}  

	@Override  
	public void onTestFailure(ITestResult result) {  
		// TODO Auto-generated method stub  
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String filename = System.getProperty("user.dir")+File.separator+"screenshot"+File.separator+result.getMethod().getMethodName()+timeStamp;
		//File f1 = ((TakesScreenshot)BaseTest.driver).getScreenshotAs(OutputType.FILE);
		TakesScreenshot screenshotDriver = ((TakesScreenshot)Base.driver);

		// Capture the screenshot as a file
		//Step 2 (print screeen) JVM
		File screenshotFile = screenshotDriver.getScreenshotAs(OutputType.FILE);
		//Step 3 - Path setup 
		// Specify the location to save the screenshot (change the path as needed)
		// Move the file to the desired location
		//Step 4 - Move to my path
		try {
			FileUtils.moveFile(screenshotFile, new File(filename+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}  

	@Override  
	public void onTestSkipped(ITestResult result) {  
		// TODO Auto-generated method stub  
		System.out.println("Skip of test cases and its details are : "+result.getName());  
	}  

	@Override  
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {  
		// TODO Auto-generated method stub  
		System.out.println("Failure of test cases and its details are : "+result.getName());  
	}  

	@Override  
	public void onStart(ITestContext context) {  
		// TODO Auto-generated method stub  
	}  

	@Override  
	public void onFinish(ITestContext context) {  
		// TODO Auto-generated method stub  
	}  
}
