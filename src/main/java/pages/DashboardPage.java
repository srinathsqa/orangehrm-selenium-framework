package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import base.BaseClass;

public class DashboardPage extends BaseClass {

    private By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    private By pimMenu = By.xpath("//span[text()='PIM']");

    public void verifyDashboard() {
        boolean isDisplayed = wait.until(
                ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).isDisplayed();

        Assert.assertTrue(isDisplayed, "Dashboard was not displayed after login");
    }

    public void goToPIM() {
        driver.findElement(pimMenu).click();
    }
}
