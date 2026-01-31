package qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.models.responses.division.AddDivisionResponse;
import qa.services.DivisionService;
import qa.utils.ApiResponse;
import qa.utils.DataGenerator;

public class AddDivisionTest extends BaseAuthenticatedTest {

    @Test(
            description = "DIV-ADD-001 - Add Division Successfully with valid data",
            groups = {"Division", "Positive"}
    )
    public void DIV_ADD_001_addDivisionSuccessfully() {

        String divisionName = "Division Auto " + System.currentTimeMillis();
        String description = DataGenerator.randomDescription();

        ApiResponse<AddDivisionResponse> response =
                DivisionService.addDivision(divisionName, description);

        Assert.assertEquals(response.getStatusCode(), 200);

        AddDivisionResponse body = response.getResponseBody();

        Assert.assertNotNull(body);
        Assert.assertNotNull(body.data);
        Assert.assertNotNull(body.data.createDivision);
        Assert.assertNotNull(body.data.createDivision.id);

        System.out.println("===== DIVISION CREATED =====");
        System.out.println("Division ID   : " + body.data.createDivision.id);
        System.out.println("Division Name : " + body.data.createDivision.name);
    }


    @Test(
            description = "DIV-ADD-002 - Failed add division when name empty",
            groups = {"Division", "Negative"}
    )
    public void DIV_ADD_002_failedAddDivisionWhenNameEmpty() {

        String description = DataGenerator.randomDescription();

        ApiResponse<AddDivisionResponse> response =
                DivisionService.addDivision(
                        "", // ❌ Name kosong
                        description
                );

        Assert.assertEquals(response.getStatusCode(), 200);

        if (response.getResponseBody().data != null) {
            System.out.println("🚨 BUG DETECTED 🚨");
            System.out.println("Division created with EMPTY name");
            System.out.println("Division ID : " +
                    response.getResponseBody().data.createDivision.id);
        }

        Assert.assertNull(
                response.getResponseBody().data,
                "BUG: Division created even when name is empty"
        );
    }
}
