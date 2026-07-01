package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {

    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter esp = new ExtentSparkReporter(path);
        esp.config().setReportName("Web Automation Results");
        esp.config().setDocumentTitle("Test Automation");
        esp.config().setTheme(Theme.DARK);
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(esp);
        extent.setSystemInfo("Tester", "Shivaraj");
        return extent;
    }
}
