package qa.models.responses.division;

public class AddDivisionResponse {

    public Data data;

    public static class Data {
        public CreateDivision createDivision;
    }

    public static class CreateDivision {
        public String id;
        public String code;
        public String name;
        public String description;
    }
}
