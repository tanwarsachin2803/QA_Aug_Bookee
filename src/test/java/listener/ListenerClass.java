package listener;

import org.testng.*;

public class ListenerClass implements  ITestListener{
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
    }

    // Implement other methods as needed...

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("All tests finished!");
    }
}
