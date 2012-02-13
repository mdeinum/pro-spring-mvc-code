package com.apress.prospringmvc.bookstore.web.frontend;

import net.sourceforge.jwebunit.junit.WebTester;
import net.sourceforge.jwebunit.util.TestingEngineRegistry;

import org.junit.Before;
import org.junit.Test;

public class LoginFrontendTest {

    private WebTester webTester;

    @Before
    public void setup() {
        webTester = new WebTester();
        webTester.setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
        webTester.getTestContext().setBaseUrl("http://localhost:8080/chapter9-bookstore/");
    }

    @Test
    public void startTest() {
        webTester.beginAt("/");
        webTester.clickLink("login");
        webTester.assertTextPresent("Username");
        webTester.assertTextPresent("Password");
        webTester.setTextField("username", "john");
        webTester.setTextField("password", "secret");
        webTester.clickButton("startLogin");
    }

}
