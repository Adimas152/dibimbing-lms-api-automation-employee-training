package qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.models.responses.employee.AddEmployeeResponse;
import qa.services.EmployeeService;
import qa.utils.ApiResponse;

public class AddEmployeeTest extends BaseAuthenticatedTest {

  @Test
  public void addEmployeeSuccessfully() {
    ApiResponse<AddEmployeeResponse> response = EmployeeService.addEmployee();

    AddEmployeeResponse responseBody = response.getResponseBody();

    Assert.assertNotNull(responseBody.data.createEmployee.id);
  }
}
