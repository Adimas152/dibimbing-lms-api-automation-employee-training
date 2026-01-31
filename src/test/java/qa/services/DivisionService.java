package qa.services;

import io.restassured.response.Response;
import qa.client.GraphQlClient;
import qa.models.responses.division.AddDivisionResponse;
import qa.utils.ApiResponse;
import qa.utils.TestDataLoader;

import java.util.Map;

public class DivisionService {

    public static ApiResponse<AddDivisionResponse> addDivision(
            String name,
            String description
    ) {
        String query = TestDataLoader.load("graphql/mutations/AddDivision.graphql");

        Map<String, Object> variables = Map.of(
                "input", Map.of(
                        "name", name,
                        "description", description
                )
        );

        System.out.println("===== CREATE DIVISION REQUEST =====");
        System.out.println("Division Name : " + name);
        System.out.println("Description   : " + description);

        Response response = GraphQlClient.execute(query, variables);

        return new ApiResponse<>(
                response.getStatusCode(),
                response.getHeaders(),
                response.as(AddDivisionResponse.class)
        );
    }
}
