package qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.models.responses.program.AddProgramResponse;
import qa.services.AddProgramService;
import qa.utils.ApiResponse;
import qa.utils.DataGenerator;

public class AddProgramServiceTest extends BaseAuthenticatedTest {

    @Test(
            description = "TRN-ADD-001 - Add Training Successfully with valid data",
            groups = {"Training","Positive"}
    )
    public void TRN_ADD_001_addTrainingSuccessfully() {

        String trainingName = DataGenerator.randomTrainingName();
        String trainingDesc = DataGenerator.randomTrainingDescription();

        ApiResponse<AddProgramResponse> response =
                AddProgramService.addProgram(
                        trainingName,
                        trainingDesc,
                        "training",
                        false
                );

        Assert.assertEquals(response.getStatusCode(), 200);

        AddProgramResponse body = response.getResponseBody();

        Assert.assertNotNull(body);
        Assert.assertNotNull(body.data);
        Assert.assertNotNull(body.data.createProgram);
        Assert.assertNotNull(body.data.createProgram.id);

        System.out.println("===== TRAINING CREATED =====");
        System.out.println("Training ID : " + body.data.createProgram.id);
        System.out.println("Training Name : " + trainingName);
    }


    @Test(
            description = "TRN-ADD-002 - Failed add training when training name empty",
            groups = {"Training","Negative"}
    )
    public void TRN_ADD_002_failedAddTrainingWhenNameEmpty() {

        ApiResponse<AddProgramResponse> response =
                AddProgramService.addProgram(
                        "", // ❌ kosong by design
                        DataGenerator.randomTrainingDescription(),
                        "training",
                        false
                );

        Assert.assertEquals(response.getStatusCode(), 200);

        AddProgramResponse body = response.getResponseBody();

        if (body.data != null) {
            System.out.println("🚨 BUG DETECTED 🚨");
            System.out.println("Training still created ID : " +
                    body.data.createProgram.id);
        }

        Assert.assertNull(body.data,
                "BUG: Training created even when title empty");
    }


}
