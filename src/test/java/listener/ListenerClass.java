package listener;

import lombok.extern.log4j.Log4j2;
import org.testng.*;

@Log4j2
public class ListenerClass implements  ITestListener{
    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test Case Start");
        System.out.println("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test Case Passed");
        System.out.println("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("Test Case Failed");
        System.out.println("Test failed: " + result.getName());
    }

    // Implement other methods as needed...

    @Override
    public void onFinish(ITestContext context) {
        log.info("Test Case Start");
        System.out.println("All tests finished!");
    }
}
