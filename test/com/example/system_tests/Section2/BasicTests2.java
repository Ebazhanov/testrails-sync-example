
package com.example.system_tests.Section2;

import com.example.automation.pages.HomePage;
import com.example.automation.utils.BaseTest;
import com.example.automation.utils.TestRailId;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BasicTests2 extends BaseTest {

    protected HomePage page_object;

    @BeforeClass
    @Override
    public void setUp() {
        super.setUp();
        // Initialize page object using selenium factory
        page_object = super.page_object.getInstance(HomePage.class);
        page_object.loadPage();
    }

    @Test
    @TestRailId(testId = 3)
    public void testSomeOtherOtherThing() {
        for(String menuItem: page_object.getMenuItems()) {
            System.out.println(menuItem);
        }
    }

    @Test
    @TestRailId(testId = 4)
    public void testSomeOtherOtherOtherThing() {
        for(String menuItem: page_object.getMenuItems()) {
            System.out.println(menuItem);
        }
    }
}