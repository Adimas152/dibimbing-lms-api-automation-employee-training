package qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.models.responses.employee.AddEmployeeResponse;
import qa.services.EmployeeService;
import qa.utils.ApiResponse;

public class AddEmployeeTest extends BaseAuthenticatedTest {

    @Test(
            description = "EMP-ADD-001 - Add Employee Successfully with valid data",
            groups = {"Employee","Positive"}
    )
    public void EMP_ADD_001_addEmployeeSuccessfully() {

        ApiResponse<AddEmployeeResponse> response =
                EmployeeService.addEmployee();

        Assert.assertEquals(response.getStatusCode(), 200);

        AddEmployeeResponse body = response.getResponseBody();

        Assert.assertNotNull(body);
        Assert.assertNotNull(body.data);
        Assert.assertNotNull(body.data.createEmployee);
        Assert.assertNotNull(body.data.createEmployee.id);

        // Debug log (optional tapi bagus untuk report)
        var emp = body.data.createEmployee;
        System.out.println("===== EMPLOYEE CREATED =====");
        System.out.println("UUID ID     : " + emp.id);
        System.out.println("Name        : " + emp.name);
        System.out.println("Employee ID : " + emp.employeeId);
        System.out.println("Email       : " + emp.email);
    }

    @Test(
            description = "EMP-ADD-002 - Failed add employee when email empty",
            groups = {"Employee","Negative"}
    )
    public void EMP_ADD_002_failedAddEmployeeWhenEmailEmpty() {

        ApiResponse<AddEmployeeResponse> response =
                EmployeeService.addEmployeeWithEmptyEmail();

        Assert.assertEquals(response.getStatusCode(), 200);

        AddEmployeeResponse body = response.getResponseBody();

        if (body.data != null) {
            System.out.println("🚨 BUG - Employee created when email empty 🚨");
            System.out.println("UUID : " + body.data.createEmployee.id);
            System.out.println("Name : " + body.data.createEmployee.name);
        }

        Assert.assertNull(body.data,
                "BUG DETECTED: Employee created even when email empty");
    }

    @Test(
            description = "EMP-ADD-003 - Failed add employee when email invalid",
            groups = {"Employee","Negative"}
    )
    public void EMP_ADD_003_failedAddEmployeeWhenEmailInvalid() {

        ApiResponse<AddEmployeeResponse> response =
                EmployeeService.addEmployeeWithInvalidEmail();

        Assert.assertEquals(response.getStatusCode(), 200);

        AddEmployeeResponse body = response.getResponseBody();

        if (body.data != null) {
            System.out.println("🚨 BUG - Employee created when email invalid 🚨");
            System.out.println("UUID : " + body.data.createEmployee.id);
            System.out.println("Name : " + body.data.createEmployee.name);
            System.out.println("Email : " + body.data.createEmployee.email);
        }

        Assert.assertNull(body.data,
                "BUG: Employee created even when email invalid");
    }

    @Test(
            description = "EMP-ADD-005 - Failed add employee when employee id duplicate",
            groups = {"Employee","Negative"}
    )
    public void EMP_ADD_005_failedAddEmployeeWhenEmployeeIdDuplicate() {

        ApiResponse<AddEmployeeResponse> response =
                EmployeeService.addEmployeeDuplicateEmployeeId("EMP-1001");

        System.out.println("===== DUPLICATE EMPLOYEE TEST RESULT =====");

        if (response.getResponseBody().data != null) {

            var emp = response.getResponseBody().data.createEmployee;

            System.out.println("🚨 BUG DETECTED 🚨");
            System.out.println("UUID ID     : " + emp.id);
            System.out.println("Name        : " + emp.name);
            System.out.println("Employee ID : " + emp.employeeId);
            System.out.println("Email       : " + emp.email);
        }

        Assert.assertNull(
                response.getResponseBody().data,
                "BUG: Employee created even when Employee ID duplicate"
        );
    }

}
