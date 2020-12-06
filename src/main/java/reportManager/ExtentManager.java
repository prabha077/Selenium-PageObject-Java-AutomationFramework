package reportManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import java.util.ArrayList;
import java.util.List;

public class ExtentManager {

    private static ExtentReports extentReports = null;
    private static ThreadLocal<ExtentReports> extent = new ThreadLocal();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal();
    private static ThreadLocal<ExtentTest> child = new ThreadLocal();
    private static ThreadLocal<ExtentTest> subchild = new ThreadLocal();

    public ExtentManager() {
    }


    public synchronized static ThreadLocal<ExtentTest> getTest() {
        return test;
    }

    public synchronized static void setTest(ExtentTest test) {
        getTest().set(test);
    }

    public synchronized static ThreadLocal<ExtentTest> getChild() {
        return child;
    }

    public synchronized static void setChild(ExtentTest child) {
        getChild().set(child);
    }

    public static ThreadLocal<ExtentReports> getExtent() {
        return extent;
    }

    public static void setExtent(ExtentReports extent) {
        getExtent().set(extent);
    }

    public static ExtentReports getInstance() {
        return extentReports;
    }

    public static ExtentReports createInstance(String reportFolderPath, String reportName) {
        if (extentReports == null) {
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportFolderPath);
            htmlReporter.config().setDocumentTitle("Automation Report");
            htmlReporter.config().setReportName("Automation Reports ");
            htmlReporter.config().setTheme(Theme.STANDARD);
            htmlReporter.config().setEncoding("utf-8");

            List<ServerAddress> address=new ArrayList<>();
            address.add(new ServerAddress("10.119.13.80",27017));
            MongoCredential credential=MongoCredential.createCredential("jenkins","extent","test".toCharArray());
//            ExtentKlovReporter KlovReporter = new ExtentKlovReporter("rain-Project","rain-run-Report").initMongoDbConnection(address, Arrays.asList(credential)).initKlovServerConnection("http://10.119.13.80:27017");
            extentReports = new ExtentReports();
            extentReports.attachReporter(htmlReporter);
//           extentReports.attachReporter(KlovReporter);
            setExtent(extentReports);
            return getExtent().get();
        }
        return extentReports;
    }

    public static void addExecutionDetails_ExtentReport() {
        getExtent().get().flush();
    }
}

