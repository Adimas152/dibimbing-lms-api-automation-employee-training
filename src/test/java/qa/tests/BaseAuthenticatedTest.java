package qa.tests;

import org.testng.annotations.BeforeSuite;
import qa.services.AuthService;

public abstract class  BaseAuthenticatedTest {

  @BeforeSuite
  public void authenticate() {
    AuthService.postLogin();
  }
}
