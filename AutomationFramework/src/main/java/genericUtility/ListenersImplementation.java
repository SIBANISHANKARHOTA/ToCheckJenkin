package genericUtility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplementation implements ITestListener {
	ExtentReports report;                          // Extent Reports made variable as Global because required method is inside anothor method
	ExtentTest test;                               //Extent Reports made variable as Global because required method is inside anothor method

	@Override
	public void onTestStart(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "----Started");
		test = report.createTest(methodname); // Extent Report
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "----Passed");
		test.log(Status.PASS, methodname + "----Passed"); // Extent Test
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "----Failed");
		test.log(Status.FAIL, methodname + "----Failed"); // Extent Test
		test.log(Status.INFO, result.getThrowable()); // Extent Test

		WebDriverUtility wutil = new WebDriverUtility();
		JavaUtility jutil = new JavaUtility();

		String screenshotname = methodname + "-" + jutil.toGetSystemDateAndTime();
		try {
			String EntireInfo = wutil.toTakeScreenShot(BaseClass.sDriver, screenshotname); // Extent Test
			test.addScreenCaptureFromPath(EntireInfo); // Extent Test
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "----Skipped");
		test.log(Status.SKIP, methodname + "----Skipped"); // Extent Test
		test.log(Status.INFO, result.getThrowable()); // Extent Test
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("--Suite Execution Started--");
		// ExtentReports
		ExtentSparkReporter htmlReport = new ExtentSparkReporter(
				"./ExtentReports/Reports-" + new JavaUtility().toGetSystemDateAndTime() + ".html");
		htmlReport.config().setDocumentTitle("Vtiger Execution Report");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setReportName("VTIGER EXECUTION REPORT");

		report = new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("BaseUrl", "http://localhost:8888/");
		report.setSystemInfo("Username", "admin");
		report.setSystemInfo("Password", "password");
		report.setSystemInfo("Reporter Name", "Sibani Shankar Hota");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("--Suite Execution Finished--");
		report.flush(); // Extent Report
	}
}