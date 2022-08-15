package com.yc.qa.mobile.utils;

import org.testng.*;

public class BaseListener implements ITestListener, IInvokedMethodListener {

    /* Methods of Interface 'ITestListener' */
    @Override
    public void onTestStart(ITestResult result) {
        // NOP
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // NOP
    }

    @Override
    public void onTestFailure(ITestResult result) {
            //BaseUtils.makeScreenAsShot("Test_FAIL_", false, ThreadStoreLocal.getWebDriver());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // NOP
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // NOP
    }

    @Override
    public void onStart(ITestContext context) {
        // NOP
    }

    @Override
    public void onFinish(ITestContext context) {
        // NOP
    }

    @Override
    public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
        // NOP
    }

    @Override
    public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
        // NOP
    }
}
