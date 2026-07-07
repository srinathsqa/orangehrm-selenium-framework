package listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseClass;

public class ScreenshotListener implements ITestListener {

    // Called automatically by TestNG whenever any @Test method fails
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            // System.getProperty("user.dir") = the project's root folder,
            // regardless of what working directory the IDE/Maven uses internally
            String projectRoot = System.getProperty("user.dir");
            File screenshotsDir = new File(projectRoot, "screenshots");
            Files.createDirectories(screenshotsDir.toPath());

            TakesScreenshot ts = (TakesScreenshot) BaseClass.driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            String fileName = result.getMethod().getMethodName() + "_failure.png";
            File destination = new File(screenshotsDir, fileName);

            Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Screenshot saved at: " + destination.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Could not capture failure screenshot:");
            e.printStackTrace();
        }
    }
}
