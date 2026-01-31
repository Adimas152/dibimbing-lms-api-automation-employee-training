package qa.services;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import qa.client.GraphQlClient;
import qa.models.requests.employee.AddEmployeeVariable;
import qa.models.responses.employee.AddEmployeeResponse;
import qa.utils.ApiResponse;
import qa.utils.TestDataLoader;

import java.util.HashMap;
import java.util.Map;

public class EmployeeService {

    public static ApiResponse<AddEmployeeResponse> addEmployee() {

        String query = TestDataLoader.load("graphql/mutations/AddEmployee.graphql");

        // AUTO GENERATE DATA
        String name = qa.utils.DataGenerator.randomEmployeeName();
        String employeeId = qa.utils.DataGenerator.randomEmployeeId();
        String email = qa.utils.DataGenerator.randomEmail();
        String phone = qa.utils.DataGenerator.randomPhoneNumber();

        Map<String, Object> variable = AddEmployeeVariable.variables(
                name,
                employeeId,
                email,
                phone,
                "43b462d2-e360-46e2-b6bd-73b3f5fb0721",
                "Mentor",
                2,
                "male",
                "1998-11-04T00:00:00.000Z",
                "",
                "",
                ""
        );

        System.out.println("Generated Name: " + name);
        System.out.println("Generated EmployeeId: " + employeeId);

        Response response = GraphQlClient.execute(query, variable);

        return new ApiResponse<>(
                response.getStatusCode(),
                response.getHeaders(),
                response.as(AddEmployeeResponse.class)
        );
    }

    public static ApiResponse<AddEmployeeResponse> addEmployeeWithEmptyEmail() {

        String query = TestDataLoader.load("graphql/mutations/AddEmployee.graphql");

        String name = qa.utils.DataGenerator.randomEmployeeName();
        String employeeId = qa.utils.DataGenerator.randomEmployeeId();
        String phone = qa.utils.DataGenerator.randomPhoneNumber();

        Map<String, Object> variable = AddEmployeeVariable.variables(
                name,
                employeeId,
                "", // EMAIL KOSONG
                phone,
                "43b462d2-e360-46e2-b6bd-73b3f5fb0721",
                "Mentor",
                2,
                "male",
                "1998-11-04T00:00:00.000Z",
                "",
                "",
                ""
        );

        Response response = GraphQlClient.execute(query, variable);

        return new ApiResponse<>(
                response.getStatusCode(),
                response.getHeaders(),
                response.as(AddEmployeeResponse.class)
        );
    }

    public static ApiResponse<AddEmployeeResponse> addEmployeeWithInvalidEmail() {

        String query = TestDataLoader.load("graphql/mutations/AddEmployee.graphql");

        String name = qa.utils.DataGenerator.randomEmployeeName();
        String employeeId = qa.utils.DataGenerator.randomEmployeeId();
        String invalidemail = qa.utils.DataGenerator.randomInvalidEmail();
        String phone = qa.utils.DataGenerator.randomPhoneNumber();

        Map<String, Object> variable = AddEmployeeVariable.variables(
                name,
                employeeId,
                invalidemail,
                phone,
                "43b462d2-e360-46e2-b6bd-73b3f5fb0721",
                "Mentor",
                2,
                "male",
                "1998-11-04T00:00:00.000Z",
                "",
                "",
                ""
        );

        Response response = GraphQlClient.execute(query, variable);

        return new ApiResponse<>(
                response.getStatusCode(),
                response.getHeaders(),
                response.as(AddEmployeeResponse.class)
        );
    }

    public static ApiResponse<AddEmployeeResponse> addEmployeeDuplicateEmployeeId(String employeeId) {

        String query = TestDataLoader.load("graphql/mutations/AddEmployee.graphql");

        String name = qa.utils.DataGenerator.randomEmployeeName();
        String email = qa.utils.DataGenerator.randomEmail();
        String phone = qa.utils.DataGenerator.randomPhoneNumber();

        Map<String, Object> variable = AddEmployeeVariable.variables(
                name,
                "EMP-1001", //ID EMPLOYEE TERDAFTAR
                email,
                phone,
                "43b462d2-e360-46e2-b6bd-73b3f5fb0721",
                "Mentor",
                2,
                "male",
                "1998-11-04T00:00:00.000Z",
                "",
                "",
                ""
        );

        Response response = GraphQlClient.execute(query, variable);

        return new ApiResponse<>(
                response.getStatusCode(),
                response.getHeaders(),
                response.as(AddEmployeeResponse.class)
        );
    }



}
