package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {


    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    // Get or create ExtentReports instance
    public static synchronized ExtentReports getExtentReports() {
        if (extent == null) {
            String path = System.getProperty("user.dir") + "/reports/ExtentReport.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(path);
            spark.config().setDocumentTitle("Selenium Automation Report");
            spark.config().setReportName("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Tester", "Vyshnavi");
        }
        return extent;
    }

    // Create a test for the scenario and store in ThreadLocal
    public static ExtentTest createTest(String name) {
        ExtentTest test = getExtentReports().createTest(name);
        extentTest.set(test);
        return test;
    }
    // Get current test from ThreadLocal
    public static ExtentTest getTest() {
        return extentTest.get();
    }

    // Remove test from ThreadLocal (important for parallel runs)
    public static void removeTest() {
        extentTest.remove();
    }

    // Flush report
    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }

}
