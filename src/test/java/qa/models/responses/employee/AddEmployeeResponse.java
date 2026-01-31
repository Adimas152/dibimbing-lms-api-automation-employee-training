package qa.models.responses.employee;

public class AddEmployeeResponse {
  public Data data;

  public static class Data {
    public CreateEmployee createEmployee;
  }

  public static class CreateEmployee {
    public String id;
      public String name;
      public String employeeId;
      public String email;
  }
}
