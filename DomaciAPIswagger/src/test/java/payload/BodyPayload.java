package payload;

public class BodyPayload {

    public static final String USERNAME = "Student" + System.currentTimeMillis();
    public static final String PASSWORD = "Password123!";

    // Create user payload
    public static String createUserPayload() {
        return "{\n" +
                "  \"userName\": \"" + USERNAME + "\",\n" +
                "  \"password\": \"" + PASSWORD + "\"\n" +
                "}";
    }

    // Generate token payload
    public static String generateTokenPayload() {
        return "{\n" +
                "  \"userName\": \"" + USERNAME + "\",\n" +
                "  \"password\": \"" + PASSWORD + "\"\n" +
                "}";
    }

    // Base URI
    public static String baseURI() {
        return "https://demoqa.com";
    }
}
