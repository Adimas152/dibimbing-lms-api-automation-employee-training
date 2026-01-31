package qa.services;

import io.restassured.response.Response;
import qa.client.GraphQlClient;
import qa.models.responses.program.AddChapterResponse;
import qa.utils.ApiResponse;
import qa.utils.TestDataLoader;

import java.util.Map;

public class ChapterService {
    public static ApiResponse<AddChapterResponse> addChapter(
            String title,
            String description,
            int order,
            String programId
    ) {

        String query = TestDataLoader.load("graphql/mutations/AddChapter.graphql");

        Map<String, Object> variables = Map.of(
                "input", Map.of(
                        "title", title,
                        "description", description,
                        "order", order,
                        "programId", programId
                )
        );

        Response response = GraphQlClient.execute(query, variables);

        return new ApiResponse<>(
                response.getStatusCode(),
                response.getHeaders(),
                response.as(AddChapterResponse.class)
        );
    }

}
