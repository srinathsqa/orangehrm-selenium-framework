package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BaseClass;

public class PIMPage extends BaseClass {

    private By addEmployeeTab = By.xpath("//a[text()='Add Employee']");
    private By employeeListTab = By.xpath("//a[text()='Employee List']");

    public void clickAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeTab)).click();
    }

    public void clickEmployeeList() {
        wait.until(ExpectedConditions.elementToBeClickable(employeeListTab)).click();
    }
}
