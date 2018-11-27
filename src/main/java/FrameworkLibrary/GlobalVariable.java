package FrameworkLibrary;

import java.util.Map;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class GlobalVariable {
	public static ExtentReports report;
	public static ExtentTest TestReport;
	public static String Currentresultsfolder;
	public static String CurrentDateAndTime;
	public static String ResultsFolderPath = "Results";
	public static String ScreenshotsFoLderPath ;
	public static String currentbrowser ;
	public static String ResulthtmlPath ;
	public static Map<String, String> Commondata ;
}

