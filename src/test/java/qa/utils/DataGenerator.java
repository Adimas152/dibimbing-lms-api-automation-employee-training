package qa.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

public class DataGenerator {

    private static final Random RANDOM = new Random();

    public static String randomEmployeeName() {
        String[] first = {"Adi", "Budi", "Cahyo", "Dewi", "Eka", "Fajar", "Gita", "Hadi"};
        String[] last = {"Saputra", "Wijaya", "Santoso", "Pratama", "Putra", "Nugroho"};

        return first[RANDOM.nextInt(first.length)] + " " +
                last[RANDOM.nextInt(last.length)] + " " +
                timestamp();
    }

    public static String randomEmployeeId() {
        return "TCID-" + UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 6)
                .toUpperCase();
    }

    public static String randomPhoneNumber() {
        return "08" + (100000000 + RANDOM.nextInt(900000000));
    }

    public static String randomEmail() {
        return "auto" + timestamp() + "@gmail.com";
    }

    public static String randomInvalidEmail() {
        return "auto" + timestamp() + "@gmail";
    }

    private static String timestamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
    }

    public static String randomTrainingName() {
        return "Training-" + System.currentTimeMillis();
    }

    public static String randomTrainingDescription() {
        return "Auto Training Desc " + System.currentTimeMillis();
    }

    public static String randomChapterName() {
        return "Chapter Auto " + System.currentTimeMillis();
    }

    public static String randomDescription() {
        return "Desc Auto " + System.currentTimeMillis();
    }


}
