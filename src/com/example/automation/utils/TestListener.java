package com.example.automation.utils;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.Result;
import com.codepine.api.testrail.model.ResultField;
import com.codepine.api.testrail.model.Run;
import org.testng.IClass;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.lang.reflect.Method;
import java.util.List;

public class TestListener extends TestListenerAdapter {
    public static Run test_run;
    public static TestRail client;
    protected String comment = "Automation Result!";

    public enum TestRunStatus {
        PASSED (1),
        BLOCKED (2),
        UNTESTED (3),
        RETEST (4),
        FAILED (5);

        protected int status_id;

        TestRunStatus(int status_id) {
            this.status_id = status_id;
        }

        public int getValue() {
            return status_id;
        }
    }


    @Override
    public void onTestFailure(ITestResult result) {
        try {
            Method testMethod = result.getTestClass().getRealClass().getMethod(result.getName());
            if (testMethod.isAnnotationPresent(TestRailId.class)) {
                int testRailId = testMethod.getAnnotation(TestRailId.class).testId();
                System.out.println("Test Rail ID = " + testRailId);


                // Now you can call the TestRail post API call to update the result in TestRail database based on the TestRun Id and TestCase Id.
                List<ResultField> customResultFields = client.resultFields().list().execute();
                client.results().addForCase(test_run.getId(),
                        testRailId,
                        new Result()
                                .setStatusId(TestRunStatus.FAILED.getValue())
                                .setComment(comment),
                        customResultFields
                ).execute();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            Method testMethod = result.getTestClass().getRealClass().getMethod(result.getName());
            if (testMethod.isAnnotationPresent(TestRailId.class)) {
                int testRailId = testMethod.getAnnotation(TestRailId.class).testId();
                System.out.println("Test Rail ID = " + testRailId);


                // Now you can call the TestRail post API call to update the result in TestRail database based on the TestRun Id and TestCase Id.
                List<ResultField> customResultFields = client.resultFields().list().execute();
                client.results().addForCase(test_run.getId(),
                        testRailId,
                        new Result()
                                .setStatusId(TestRunStatus.PASSED.getValue())
                                .setComment(comment),
                        customResultFields
                ).execute();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
