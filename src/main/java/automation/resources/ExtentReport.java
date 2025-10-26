package automation.resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	
	public static ExtentReports getReportobjcet()
	{
		
		// class Extent Report and ExtentSparkReporter
		 String path = System.getProperty("user.dir")+ "//reports//index.html";
		 ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		 reporter.config().setDocumentTitle("Automation Test");
		 reporter.config().setReportName("End to End Project");
		 ExtentReports extentReport = new ExtentReports();
		 extentReport.attachReporter(reporter);
		 extentReport.setSystemInfo("QA", "Anamika");
		 
		 return extentReport;
		 
		 
		 
		 
	}

}
