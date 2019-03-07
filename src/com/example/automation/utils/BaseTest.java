package com.example.automation.utils;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.Project;
import com.codepine.api.testrail.model.Run;
import com.codepine.api.testrail.model.Suite;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseTest {
    protected WebDriver driver;

    protected BasePage page_object;

    protected static TestRail client;

    protected static Run test_run;

    @BeforeSuite
    public void beforeSuite(ITestContext context) {
        // This method should only be executed once
        if (client == null) {
            final String TESTRAIL_URL = "https://gonzalostesting.testrail.io";
            final String TESTRAIL_USER = "gonzalogarcia243@gmail.com";
            final String TESTRAIL_PASS = "";
            final String TESTRAIL_APPNAME = "Testing";
            final int TESTRAIL_PROJECTID = 1;

            String suite_name = context.getSuite().getName();

            client = TestRail.builder(TESTRAIL_URL, TESTRAIL_USER, TESTRAIL_PASS)
                    .applicationName(TESTRAIL_APPNAME)
                    .build();

            int sid = 0;
            List<Suite> suiteList = client.suites().list(TESTRAIL_PROJECTID).execute();
            for(Suite s : suiteList) {
                String sName = s.getName();
                if(sName.equals(suite_name)) {
                    sid = s.getId();
                    System.out.println("SuiteID=" + Integer.toString(sid));
                }
            }

            if (sid != 0) {
                SimpleDateFormat format = new SimpleDateFormat("dd MMM yyy kk mm s");
                Date date = new Date();
                String dateString = format.format(date);
                String runName = "Automation " + dateString;
                try{
                    TestListener.client = client;
                    test_run = client.runs()
                            .add(TESTRAIL_PROJECTID, new Run().setSuiteId(sid).setName(runName).setIncludeAll(false))
                            .execute();
                    TestListener.test_run = test_run;
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

        }
    }

    @BeforeMethod
    public void updateRun(Method method, ITestContext context) {
        try{
            Integer caseId = method.getAnnotation(TestRailId.class).testId();
            // Get the full list of IDs from the test_run
            List<Integer> caseIds = test_run.getCaseIds();
            if(null == caseIds) {
                caseIds = new ArrayList<>();
            }
            // Add the new test just in case
            caseIds.add(caseId);
            // Update the run with the new IDs
            test_run.setCaseIds(caseIds);
            // Execute the request
            client.runs().update(test_run).execute();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    @BeforeClass
    public void setUp() {
        try {
            driver = DriverSetup.setup(DriverSetup.SupportedBrowser.Chrome);
            page_object = new BasePage(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
