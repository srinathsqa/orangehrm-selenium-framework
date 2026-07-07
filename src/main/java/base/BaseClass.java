package base;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import utils.ConfigReader;

public class BaseClass {

    // Shared driver and wait, used by every Page Object and Test class
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public void setup() throws Exception {

        ChromeOptions options = new ChromeOptions();
        boolean isCI = System.getenv("CI") != null;

        // GitHub Actions sets the "CI" environment variable automatically.
        // Locally this stays false, so you still see the browser on your machine.
        if (isCI) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }

        // Selenium 4.6+ auto-manages the chromedriver binary, no manual path needed
        driver = new ChromeDriver(options);

        // IMPORTANT: maximize() behaves unreliably in headless mode on Linux CI
        // runners and can silently shrink the window back down, which then hides
        // menu items like "Add Employee" behind a collapsed responsive layout.
        // In CI we explicitly set the size instead of maximizing.
        if (isCI) {
            driver.manage().window().setSize(new Dimension(1920, 1080));
        } else {
            driver.manage().window().maximize();
        }

        // Reusable explicit wait: max 30 seconds for any element condition,
        // with extra margin for slower CI environments
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get(ConfigReader.get("url"));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
