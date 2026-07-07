package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import base.BaseClass;

public class AddEmployeePage extends BaseClass {

    private By firstNameField = By.name("firstName");
    private By lastNameField = By.name("lastName");
    private By saveButton = By.xpath("//button[@type='submit']");
    private By successToast = By.xpath("//p[text()='Successfully Saved']");
    private By pageLoader = By.className("oxd-form-loader");

    public void addEmployee(String firstName, String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);

        // The page shows a loading overlay while it fetches the auto Employee ID.
        // Wait for it to disappear, otherwise the overlay intercepts the click.
        wait.until(ExpectedConditions.invisibilityOfElementLocated(pageLoader));

        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void verifyEmployeeAdded() {
        boolean isSaved = wait.until(
                ExpectedConditions.visibilityOfElementLocated(successToast)).isDisplayed();

        Assert.assertTrue(isSaved, "Employee was not added successfully");
    }
}
