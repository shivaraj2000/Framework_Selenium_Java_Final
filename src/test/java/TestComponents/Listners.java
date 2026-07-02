package TestComponents;

import Resources.ExtentReporterNG;
import TestComponents.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listners extends BaseTest implements ITestListener {
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    ExtentReports extent= ExtentReporterNG.getReportObject();
    ExtentTest test;
    @Override
    public void onTestStart(ITestResult result) {
        //TO DO
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //TO DO
        extentTest.get().pass("Test Passed");    }

    @Override
    public void onTestFailure(ITestResult result) {
        //TO DO
        extentTest.get().fail(result.getThrowable());

        try {
            driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String filePath= null;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        //TO DO
    }

    @Override
    public void onFinish(ITestContext context) {
        //TO DO
        extent.flush();
    }
}