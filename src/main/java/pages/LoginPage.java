package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BaseClass;

public class LoginPage extends BaseClass {

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By errorAlert = By.xpath("//p[contains(@class,'oxd-alert-content-text')]");

    public void login(String username, String password) {
        WebElement userBox = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        userBox.clear();
        userBox.sendKeys(username);

        WebElement passBox = driver.findElement(passwordField);
        passBox.clear();
        passBox.sendKeys(password);

        driver.findElement(loginButton).click();
    }

    // Used for negative test cases (invalid username/password)
    public boolean isErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorAlert)).isDisplayed();
    }
}
