package qa.models.responses.program;

public class AddContentResponse {

    public Data data;

    public static class Data {
        public CreateContent createContent;
    }

    public static class CreateContent {
        public String id;
    }
}