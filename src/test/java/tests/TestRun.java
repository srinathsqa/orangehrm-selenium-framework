package tests;

import org.testng.annotations.Test;

import base.BaseClass;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.EmployeeListPage;
import pages.LoginPage;
import pages.PIMPage;
import utils.ConfigReader;

public class TestRun extends BaseClass {

    @Test(priority = 0, description = "Attempt login with invalid credentials and expect an error message")
    public void testInvalidLogin() {
        LoginPage login = new LoginPage();
        login.login("WrongUser", "WrongPass123");

        boolean errorShown = login.isErrorDisplayed();
        org.testng.Assert.assertTrue(errorShown, "Error message was not shown for invalid login");
    }

    @Test(priority = 1, description = "Login to OrangeHRM with valid credentials")
    public void testLogin() throws Exception {
        LoginPage login = new LoginPage();
        login.login(ConfigReader.get("username"), ConfigReader.get("password"));

        DashboardPage dashboard = new DashboardPage();
        dashboard.verifyDashboard();
    }

    @Test(priority = 2, description = "Add a new employee via the PIM module",
          dependsOnMethods = "testLogin")
    public void testAddEmployee() {
        DashboardPage dashboard = new DashboardPage();
        dashboard.goToPIM();

        PIMPage pim = new PIMPage();
        pim.clickAddEmployee();

        AddEmployeePage addEmployee = new AddEmployeePage();
        addEmployee.addEmployee("John", "Doe");
        addEmployee.verifyEmployeeAdded();
    }

    @Test(priority = 3, description = "Search for the newly added employee and verify they appear in the list",
          dependsOnMethods = "testAddEmployee")
    public void testSearchEmployee() {
        DashboardPage dashboard = new DashboardPage();
        dashboard.goToPIM();

        PIMPage pim = new PIMPage();
        pim.clickEmployeeList();

        EmployeeListPage empList = new EmployeeListPage();
        empList.searchEmployee("John");
        empList.verifyEmployeeFound("John");
    }
}
