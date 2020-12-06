package listeners;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reportManager.ExtentManager;
import reportManager.statusReport;
import java.util.concurrent.*;

public class TestListener implements ITestListener {
        private ConcurrentHashMap<String, ExtentTest> allTests = new ConcurrentHashMap<>();
        String reportFolderPath = statusReport.reportPath;
        String reportName = "rainAutomationReport.html";


    //This will run at the end of all test runs.
    @Override
    public void onFinish(ITestContext iTestContext) {
        ExtentManager.getInstance().flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        ExtentTest extentTest = ExtentManager.createInstance(reportFolderPath, reportName).createTest(iTestResult.getMethod().getRealClass().getSimpleName());
        allTests.put(iTestResult.getClass().getSimpleName(), extentTest);
        ExtentManager.setTest(extentTest);
        ExtentManager.getInstance().flush();
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(":::::::::::::::::::::::::PASSED:::::::::::::::::::::::::");
        try{
            ExtentManager.getTest().get().assignCategory(iTestResult.getClass().getSimpleName());
//        ExtentManager.getTest().get().createNode(new GherkinKeyword("Scenario"),iTestResult.getMethod().getMethodName()).log(Status.PASS,iTestResult.getMethod().getMethodName());
            ExtentManager.getTest().get().createNode(MarkupHelper.createLabel("Test passed", ExtentColor.GREEN).getMarkup());
            ExtentManager.getInstance().flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println(":::::::::::::::::::::::::FAILED:::::::::::::::::::::::::");
        try {
            ExtentManager.getTest().get().createNode(MarkupHelper.createLabel("Test Failed", ExtentColor.RED).getMarkup())
                    .fail(iTestResult.getThrowable());
            ExtentManager.getInstance().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

        System.out.println(":::::::::::::::::::::::::FAILED:::::::::::::::::::::::::");
        try {
            ExtentManager.getTest().get().createNode(MarkupHelper.createLabel("Test Skipped", ExtentColor.AMBER).getMarkup())
                    .skip(iTestResult.getThrowable());
            ExtentManager.getInstance().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }
}