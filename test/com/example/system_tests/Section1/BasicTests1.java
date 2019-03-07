
package com.example.system_tests.Section1;

import com.example.automation.utils.TestRailId;
import com.example.automation.pages.HomePage;
import com.example.automation.utils.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BasicTests1 extends BaseTest {

    private HomePage page_object;

    @BeforeClass
    @Override
    public void setUp() {
        super.setUp();
        // Initialize page object using selenium factory
        page_object = super.page_object.getInstance(HomePage.class);
        page_object.loadPage();
    }

    @Test(groups = { "sanity" })
    @TestRailId(testId = 1)
    public void testAllMenuItems() {
        for(String menuItem: page_object.getMenuItems()) {
            System.out.println(menuItem);
        }
    }

    @Test
    @TestRailId(testId = 2)
    public void testSomeOtherThings() {
    }
}