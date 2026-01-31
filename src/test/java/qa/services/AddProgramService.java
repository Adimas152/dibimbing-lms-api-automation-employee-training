package qa.services;

import io.restassured.response.Response;
import qa.client.GraphQlClient;
import qa.models.requests.progran.AddProgramVariable;
import qa.models.responses.program.AddProgramResponse;
import qa.utils.ApiResponse;
import qa.utils.TestDataLoader;

import java.util.Map;

public class AddProgramService {

  public static ApiResponse<AddProgramResponse> addProgram() {
    return addProgram(
        "Training Test",
        "Description: Training Desc",
        "training",
        false
    );
  }

  public static ApiResponse<AddProgramResponse> addProgram(
      String title,
      String description,
      String type,
      boolean isSequential
  ) {
    String query = TestDataLoader.load("graphql/mutations/AddProgram.graphql");

    System.out.println("Query: " + query);

    Map<String, Object> variables = AddProgramVariable.variables(
        title,
        description,
        type,
        isSequential
    );

    System.out.println("Variables: " + variables);

    Response response = GraphQlClient.execute(
        query,
        variables
    );

    return new ApiResponse<>(
        response.getStatusCode(),
        response.getHeaders(),
        response.as(AddProgramResponse.class)
    );
  }
}
