
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SwaggerAPI {

    //USER
    public static final String USERNAME = "Student" + System.currentTimeMillis();
    public static final String PASSWORD = "Password123!";
    public String userId;
    public String token;

    //Base URI
    public static String baseURI() {
        return "https://demoqa.com";
    }

    //PAYLOAD
    public static String createUserPayload(String username, String password) {
        return "{\n" +
                "  \"userName\": \"" + username + "\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "}";
    }

    public static String generateTokenPayload(String username, String password) {
        return "{\n" +
                "  \"userName\": \"" + username + "\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "}";
    }

    //ACCOUNT TESTS

    @Test(priority = 1)
    public void createUserTest() {
        RestAssured.baseURI = baseURI();
        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(createUserPayload(USERNAME, PASSWORD))
                .when()
                .post("/Account/v1/User")
                .then().log().all()
                .assertThat().statusCode(201)
                .extract().response().asString();

        JsonPath js = new JsonPath(response);
        userId = js.getString("userID");
        Assert.assertNotNull(userId, "UserID should not be null");
        System.out.println("Created userID: " + userId);
    }

    @Test(priority = 2)
    public void generateTokenTest() {
        RestAssured.baseURI = baseURI();
        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(generateTokenPayload(USERNAME, PASSWORD))
                .when()
                .post("/Account/v1/GenerateToken")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = new JsonPath(response);
        token = js.getString("token");
        Assert.assertNotNull(token, "Token should not be null");
        System.out.println("Generated token: " + token);
    }

    @Test(priority = 3)
    public void authorizedTest() {
        RestAssured.baseURI = baseURI();
        given().log().all()
                .header("Content-Type", "application/json")
                .body(generateTokenPayload(USERNAME, PASSWORD))
                .when()
                .post("/Account/v1/Authorized")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test(priority = 4)
    public void getUserTest() {
        RestAssured.baseURI = baseURI();
        given().log().all()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/Account/v1/User/" + userId)
                .then().log().all()
                .assertThat().statusCode(200);
    }

    //BOOKSTORE TESTS

    @Test(priority = 5)
    public void getAllBooksTest() {
        RestAssured.baseURI = baseURI();
        given().log().all()
                .when()
                .get("/BookStore/v1/Books")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test(priority = 6)
    public void addBookTest() {
        RestAssured.baseURI = baseURI();
        String payload = "{\n" +
                "  \"userId\": \"" + userId + "\",\n" +
                "  \"collectionOfIsbns\": [{\"isbn\": \"9781449325862\"}]\n" +
                "}";

        given().log().all()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post("/BookStore/v1/Books")
                .then().log().all()
                .assertThat().statusCode(201);
    }

    @Test(priority = 7)
    public void getBookByISBNTest() {
        RestAssured.baseURI = baseURI();
        given().log().all()
                .when()
                .get("/BookStore/v1/Book?ISBN=9781449325862")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test(priority = 8)
    public void updateBookTest() {
        RestAssured.baseURI = baseURI();
        String payload = "{\n" +
                "  \"userId\": \"" + userId + "\",\n" +
                "  \"isbn\": \"9781449331818\"\n" +
                "}";

        given().log().all()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .put("/BookStore/v1/Books/9781449325862")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test(priority = 9)
    public void deleteBookByISBNTest() {
        RestAssured.baseURI = baseURI();
        String payload = "{\n" +
                "  \"userId\": \"" + userId + "\",\n" +
                "  \"isbn\": \"9781449331818\"\n" +
                "}";

        given().log().all()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .delete("/BookStore/v1/Book")
                .then().log().all()
                .assertThat().statusCode(204);
    }

    @Test(priority = 10)
    public void deleteUserTest() {
        RestAssured.baseURI = baseURI();
        given().log().all()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/Account/v1/User/" + userId)
                .then().log().all()
                .assertThat().statusCode(204);
    }
}
