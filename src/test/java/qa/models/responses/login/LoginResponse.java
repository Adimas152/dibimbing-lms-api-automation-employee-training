package qa.models.responses.login;

public class LoginResponse {

  public Data data;

  public static class Data {
    public Login login;
  }

  public static class Login {
    public User user;
    public Errors errors;
  }

  public static class User {
    public String id;
    public String named;
    public String email;
    public String role;
    public String companyId;
  }

  public static class Errors {
    public String field;
    public String message;
  }
}
