package qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.models.responses.user.MeResponse;
import qa.services.UserService;
import qa.utils.ApiResponse;
import qa.utils.Utils;

public class UserServiceTest extends BaseAuthenticatedTest {

  @Test
  public void authenticateUserCanGetMeQuery() {
    ApiResponse<MeResponse> response = UserService.getMe();
    MeResponse responseBody = response.getResponseBody();

    Assert.assertNotNull(responseBody.data.me.id);
    Assert.assertNotNull(responseBody.data.me.name);
    Utils.assertValidEmail(responseBody.data.me.email);
    Assert.assertNotNull(responseBody.data.me.phoneNumber);
    Assert.assertNotNull(responseBody.data.me.role);
  }
}
