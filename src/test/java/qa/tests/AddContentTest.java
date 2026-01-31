package qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.models.responses.program.AddContentResponse;
import qa.services.ContentService;
import qa.utils.ApiResponse;

import java.util.HashMap;
import java.util.Map;

public class AddContentTest extends BaseAuthenticatedTest {

    private static final String CHAPTER_ID =
            "88a43411-8595-4187-b2a2-3c4d99e8912a";

    @Test(
            description = "CNT-ADD-004 - Failed add article content when duration invalid and title/description empty",
            groups = {"Training", "Negative"}
    )
    public void CNT_ADD_004_failedAddArticleWhenInvalidDurationAndEmptyFields() {

        Map<String, Object> input = new HashMap<>();
        input.put("title", "");                     // kosong
        input.put("description", "");               // kosong
        input.put("order", null);                   // NULL BOLEH
        input.put("chapterId", CHAPTER_ID);
        input.put("type", "article");
        input.put("thumbnailUrl", "");
        input.put("duration", -999);                // invalid
        input.put("article", "");
        input.put("articleType", "url");
        input.put("isRandomQuestion", false);

        ApiResponse<AddContentResponse> response =
                ContentService.addContent(input);

        Assert.assertEquals(response.getStatusCode(), 200);

        if (response.getResponseBody().data != null) {
            System.out.println("🚨 BUG DETECTED 🚨");
            System.out.println("Article created with invalid duration & empty fields");
            System.out.println("Content ID : " +
                    response.getResponseBody().data.createContent.id);
        }

        Assert.assertNull(
                response.getResponseBody().data,
                "BUG: Article content created when duration & required fields invalid"
        );
    }


    @Test(
            description = "CNT-ADD-005 - Failed add video content when duration invalid and title/description empty",
            groups = {"Training", "Negative"}
    )
    public void CNT_ADD_005_failedAddVideoWhenInvalidDurationAndEmptyFields() {

        Map<String, Object> input = new HashMap<>();
        input.put("title", "");
        input.put("description", "");
        input.put("order", null);
        input.put("chapterId", CHAPTER_ID);
        input.put("type", "video");
        input.put("thumbnailUrl", "");
        input.put("duration", -10);                 // invalid
        input.put("mediaId", "5cafc8db-c757-4a1d-84c3-4d87a3b38d0a");
        input.put("isRandomQuestion", false);

        ApiResponse<AddContentResponse> response =
                ContentService.addContent(input);

        Assert.assertEquals(response.getStatusCode(), 200);

        if (response.getResponseBody().data != null) {
            System.out.println("🚨 BUG DETECTED 🚨");
            System.out.println("Video created with invalid duration & empty fields");
            System.out.println("Content ID : " +
                    response.getResponseBody().data.createContent.id);
        }

        Assert.assertNull(
                response.getResponseBody().data,
                "BUG: Video content created when duration & required fields invalid"
        );
    }

}
