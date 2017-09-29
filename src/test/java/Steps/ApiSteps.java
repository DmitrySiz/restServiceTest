package Steps;

import java.io.IOException;

import static Other.Helper.formatToJson;
import static io.restassured.RestAssured.given;

public class ApiSteps {
    private final int OK = 200;
    private final  String URL = "http://localhost:28080/rs/users/";
    private  String headerFirstName = "firstName";
    private  String headerLastName = "lastName";
    private  String headerId = "id";

    public String createUser(String userFirstName,String userLastName) throws IOException {
        String result =
                given()
                    .header(headerFirstName,userFirstName)
                    .header(headerLastName,userLastName)
                .post(URL)
                .then()
                    .statusCode(OK)
                    .extract().body().asString();
        result = formatToJson(result);
        return result;
    }

    public String getUserById(String id){
        String result =
                given()
                    .header(headerId,id)
                .get(URL + id)
                .then()
                    .statusCode(OK)
                    .extract().body().asString();
        result = formatToJson(result);
        return result;
    }

    public String updateUserData(String id,String userFirstName, String userLastName){
        String result =
                given()
                    .header(headerId,id)
                    .header(headerFirstName, userFirstName)
                    .header(headerLastName, userLastName)
                .put(URL + id)
                .then()
                    .statusCode(OK)
                    .extract().body().asString();
        result = formatToJson(result);
        return result;
    }

    public String deleteUser(String id){
        String result =
                given()
                        .header(headerId,id)
                        .delete(URL + id)
                        .then()
                        .statusCode(OK)
                        .extract().body().asString();
        result = formatToJson(result);
        return result;
    }

    public String getAllUsers() throws IOException {
        String result =
                given()
                        .get(URL)
                        .then()
                        .statusCode(OK)
                        .extract().body().asString();
        result = formatToJson(result);
        return result;
    }

    public void createN_Users(String userFirstName,String userLastName, int n) throws IOException {
        for (int i = 0; i < n;i++){
            given()
                    .header(headerFirstName, userFirstName)
                    .header(headerLastName, userLastName)
                    .post(URL)
                    .then()
                    .statusCode(OK);
        }
    }

    public void deleteN_Users(int n) throws IOException {
        for (int i = 1; i <= n;i++){
            given()
                    .header(headerId,i)
                    .delete(URL+i)
                    .then()
                    .statusCode(OK);
        }
    }
}
