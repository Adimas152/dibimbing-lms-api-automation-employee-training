package qa.models.responses.program;

public class AddProgramResponse {

  public Data data;

  public static class Data {
    public CreateProgram createProgram;
  }

  public static class CreateProgram {
    public String id;
  }
}
