package com.example;

import TestComponents.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class ExtentReportsDemo {
    ExtentReports extent;
    //@BeforeTest
    public  void config()
    {
        String path=System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter esp = new ExtentSparkReporter(path);
        esp.config().setReportName("Web Automation Results");
        esp.config().setDocumentTitle("Test Automation");
        esp.config().setTheme(Theme.DARK);
         extent= new ExtentReports();
        extent.attachReporter(esp);
        extent.setSystemInfo("Tester","Shivaraj");

    }

    //@Test
    public void extentReportDemo() throws IOException {
        ExtentTest ex=extent.createTest("Initial Demo");
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/");
        System.out.println(driver.getTitle());
        //ex.fail("test failed");
        driver.quit();

        extent.flush();

    }
}
