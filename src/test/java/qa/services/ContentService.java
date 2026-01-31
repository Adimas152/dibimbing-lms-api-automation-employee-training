package qa.services;

import io.restassured.response.Response;
import qa.client.GraphQlClient;
import qa.models.responses.program.AddContentResponse;
import qa.utils.ApiResponse;
import qa.utils.TestDataLoader;

import java.util.Map;

public class ContentService {

    public static ApiResponse<AddContentResponse> addContent(
            Map<String, Object> input
    ) {
        String query = TestDataLoader.load(
                "graphql/mutations/AddContent.graphql"
        );

        Response response = GraphQlClient.execute(
                query,
                Map.of("input", input)
        );

        return new ApiResponse<>(
                response.getStatusCode(),
                response.getHeaders(),
                response.as(AddContentResponse.class)
        );
    }
}
