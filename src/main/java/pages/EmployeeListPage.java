package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import base.BaseClass;

public class EmployeeListPage extends BaseClass {

    private By employeeNameField = By.xpath("//input[@placeholder='Type for hints...']");
    private By suggestionDropdown = By.xpath("//div[@role='listbox']");
    private By searchButton = By.xpath("//button[@type='submit']");
    private By resultsTable = By.cssSelector(".oxd-table-body");

    public void searchEmployee(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameField)).sendKeys(name);

        // Wait for the autocomplete suggestion list, then select the first match
        wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionDropdown));
        driver.findElement(employeeNameField).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(employeeNameField).sendKeys(Keys.ENTER);

        driver.findElement(searchButton).click();
    }

    public void verifyEmployeeFound(String name) {
        // Check the whole results table rather than assuming which row the match is in
        String tableText = wait.until(ExpectedConditions.visibilityOfElementLocated(resultsTable)).getText();
        Assert.assertTrue(tableText.contains(name), "Employee \"" + name + "\" was not found in the search results");
    }
}
