package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    @Override
    public void onStart(ITestContext arg0) {
        System.out.println("+Begin test: " + arg0.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    @Override
    public void onTestStart(ITestResult arg0) {
        System.out.println(" Starting test: " + arg0.getName());
    }
    @Override
    public void onTestSuccess(ITestResult arg0) {
        System.out.println(" Test passed: " + arg0.getName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }
}
