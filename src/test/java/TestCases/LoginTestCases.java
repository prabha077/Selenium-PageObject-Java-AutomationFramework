package TestCases;

import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Listeners(TestListener.class) //Listener for Reporting


public class LoginTestCases extends BaseTest {

    private static final Logger slf4jLogger = LoggerFactory.getLogger(LoginTestCases.class);

    @Test(enabled = true,groups = {"test1","test2"})
    public void TEST_CASE_ONE() {
        slf4jLogger.info("This is first test");
        driver.get(URL);
        loginPage.pressExc();
        slf4jLogger.info("This is prabha");

    }

    @Test
    public void TEST_CASE_TWO() throws Exception {
        slf4jLogger.info("This is second test");
        driver.get(URL);
        loginPage.pressExc();
        homePage.searchForProducts();

    }


}
