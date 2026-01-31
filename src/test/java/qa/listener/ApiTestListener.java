package qa.listener;

import org.testng.ITestResult;

public class ApiTestListener extends BaseTestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);

        // 👉 Future bisa taruh:
        // save response
        // save request
        // kirim slack
        // attach report
        System.out.println("📌 Additional Failure Handling...");
    }
}
