package qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.models.responses.program.AddChapterResponse;
import qa.services.ChapterService;
import qa.utils.ApiResponse;
import qa.utils.DataGenerator;

public class AddChapterTest extends BaseAuthenticatedTest{
    @Test(
            description = "CHP-ADD-001 - Add Chapter Successfully",
            groups = {"Training","Positive"}
    )
    public void CHP_ADD_001_addChapterSuccessfully() {

        String chapterName = DataGenerator.randomChapterName();
        String description = DataGenerator.randomDescription();
        String PROGRAM_ID =
                "a817681c-142c-4809-b78a-e02534193b67"; // ambil dari DB / Postman

        System.out.println("===== CREATE CHAPTER REQUEST =====");
        System.out.println("Program ID   : " + PROGRAM_ID);
        System.out.println("Chapter Name : " + chapterName);
        System.out.println("Description  : " + description);

        ApiResponse<AddChapterResponse> response =
                ChapterService.addChapter(
                        chapterName,
                        description,
                        1,
                        PROGRAM_ID
                );

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.getResponseBody().data);
        Assert.assertNotNull(response.getResponseBody().data.createChapter.id);

        System.out.println("===== CHAPTER CREATED SUCCESS =====");
        System.out.println("Chapter ID   : " +
                response.getResponseBody().data.createChapter.id);
    }


    @Test(
            description = "CHP-ADD-002 - Failed add chapter when title empty",
            groups = {"Training","Negative"}
    )
    public void CHP_ADD_002_failedAddChapterWhenTitleEmpty() {

        String description = DataGenerator.randomDescription();
        String PROGRAM_ID =
                "a817681c-142c-4809-b78a-e02534193b67"; // ambil dari DB / Postman

        System.out.println("===== CREATE CHAPTER NEGATIVE TEST =====");
        System.out.println("Program ID   : " + PROGRAM_ID);
        System.out.println("Chapter Name : <EMPTY>");
        System.out.println("Description  : " + description);

        ApiResponse<AddChapterResponse> response =
                ChapterService.addChapter(
                        "",
                        description,
                        1,
                        PROGRAM_ID
                );

        Assert.assertEquals(response.getStatusCode(), 200);

        // ❌ Chapter tidak boleh dibuat
        Assert.assertNull(response.getResponseBody().data,
                "BUG: Chapter created even when title empty");
    }


}
