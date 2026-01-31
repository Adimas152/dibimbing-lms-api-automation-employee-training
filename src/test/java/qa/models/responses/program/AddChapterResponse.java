package qa.models.responses.program;

public class AddChapterResponse {

    public Data data;

    public static class Data {
        public CreateChapter createChapter;
    }

    public static class CreateChapter {
        public String id;
    }
}
