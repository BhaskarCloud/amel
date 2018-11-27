package FrameworkLibrary;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import com.relevantcodes.extentreports.ExtentReports;

public class ListenerClass extends GlobalVariable implements IAnnotationTransformer, ITestListener
{
	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation iAno, Class testClass, Constructor testConstructor,Method method) 
	{
		String TestCaseName = method.getName();
		boolean TestCaseExecutionStatus = ExcelLibrary.getExecuteStatus(TestCaseName);
		if(TestCaseExecutionStatus  = true)
			{
				int priority = ExcelLibrary.getPriority(TestCaseName); 
				String Description = ExcelLibrary.getDescription(TestCaseName);
				
				iAno.setEnabled(true); 
				iAno.setPriority(priority); 
				iAno.setDescription(Description);
			}
			else
			{
				iAno.setEnabled(false); 
			}
	}	// line 33
	
	@Override
	public void onStart(ITestContext context)  // line 36
	{
		System.out.println("Execution Start"); 
		Currentresultsfolder = WebLibrary.createresultsfolder(); 
		ScreenshotsFoLderPath = Currentresultsfolder + "\\Screenshots"; 
		ResulthtmlPath = Currentresultsfolder + "\\Summary.html"; //Create report
		System.out.println("result folder path is "+ResulthtmlPath);
		report = new ExtentReports(ResulthtmlPath, true); 
		//ExcelLibrary.loadCammonTestData();
	
	}
	
	@Override
	public void onTestStart(ITestResult result) // line 49
	{
		System.out.println(""); 
		System.out.println("");
		System.out.println("Start:  " + result.getMethod().getMethodName()+" "+ result.getMethod().getDescription());
		TestReport = report.startTest( result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) // line 58
	{
		System.out.println("with success End:   " + result.getMethod().getMethodName()); 
	// 	ExcelLibrary.setExecuteStatus(result.getMethod().getMethodName(), "Passed"); 
	// 	ExcelLibrary.setLastExecuted(result.getMethod().getMethodName()); 
		report.endTest(TestReport);	
	}
	
	@Override
	public void onTestFailure(ITestResult result) // line 67
	{
		System.out.println("with failure End:   " + result.getMethod().getMethodName()); 
		report.endTest(TestReport);	
	}
	
	@Override
	public void onTestSkipped(ITestResult result) // line 74
	{
		System.out.println("with Skip End:   " + result.getMethod().getMethodName()); 
		report.endTest(TestReport);	
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) // line 81
	{
		System.out.println("End: " + result.getMethod().getMethodName()); 
		report.endTest(TestReport);	
	}
	
	
	
	@Override	
	public void onFinish(ITestContext result) // line 90
	{	report.flush();
		System.out.println(""); 
		System.out.println("");
		System.out.println("Execution Finish"); 
		System.out.println("");
		System.out.println(""); 
		report.close();	
	}
}
