package qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.client.AuthSession;
import qa.config.CredentialsConfig;
import qa.models.responses.login.LoginResponse;
import qa.services.AuthService;
import qa.utils.ApiResponse;

public class LoginServiceTest {

  @Test
  public void userAdminSuccessfullyLogIn() {
    String email = CredentialsConfig.EMAIL;
    String password = CredentialsConfig.PASSWORD;
    String companyId = CredentialsConfig.COMPANY_ID;

    ApiResponse<LoginResponse> loginResponse = AuthService.postLogin(
        email,
        password,
        companyId
    );

    LoginResponse responseBody = loginResponse.getResponseBody();

    // Json path -> "data.login.user.email"

    Assert.assertEquals(responseBody.data.login.user.email, email);
    Assert.assertEquals(responseBody.data.login.user.companyId, companyId);
    Assert.assertEquals(responseBody.data.login.user.role, "admin");

    // Verify session cookie is active and valid for next requests
    Assert.assertTrue(AuthSession.isSessionActive());
  }
}
