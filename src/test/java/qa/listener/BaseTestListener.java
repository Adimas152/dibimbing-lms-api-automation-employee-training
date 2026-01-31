package qa.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class BaseTestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("======================================");
        System.out.println("START EXECUTION: " + context.getName());
        System.out.println("======================================");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("======================================");
        System.out.println("FINISH EXECUTION: " + context.getName());
        System.out.println("======================================");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("START TEST: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ PASS: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ FAIL: " + result.getMethod().getMethodName());
        System.out.println("Reason: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠️ SKIPPED: " + result.getMethod().getMethodName());
    }
}
