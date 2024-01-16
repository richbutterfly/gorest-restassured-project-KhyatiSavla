package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    static int userId;
    static String name = TestUtils.getRandomValue() + "sanket";
    static String email = TestUtils.getRandomValue() + "sanket@gmail.com";
    static String gender = "male";
    static String status = "active";

    @Test
    public void test01_VerifyUserCreatedSuccessfully() { // create one user

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .header("Authorization", "Bearer 03fce5af5bfff118402a50a1961ef191f6ab065e46dcdf6f5d96ea18c9496704")
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .post("/users");
        response.prettyPrint();
        response.then().statusCode(201);

        userId = response.then().extract().path("id");
        System.out.println("User Id is: " + userId);

    }

    @Test
    public void test02_VerifyUserReadSuccessfully() {

        Response response = given()
                .header("Authorization", "Bearer 03fce5af5bfff118402a50a1961ef191f6ab065e46dcdf6f5d96ea18c9496704")
                .contentType(ContentType.JSON)
                .when()
                .get("/users/" + userId);
        response.prettyPrint();
        response.then().statusCode(200);


    }

    @Test
    public void test03_VerifyUserUpdateSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .header("Authorization", "Bearer 03fce5af5bfff118402a50a1961ef191f6ab065e46dcdf6f5d96ea18c9496704")
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .put("/users/" + userId);
        response.prettyPrint();
        response.then().statusCode(200);

    }

    @Test
    public void test04_VerifyUserDeleteSuccessfully() {
        Response response = given()
                .header("Authorization", "Bearer 03fce5af5bfff118402a50a1961ef191f6ab065e46dcdf6f5d96ea18c9496704")
                .contentType(ContentType.JSON)
                .when()
                .delete("/users/" + userId);
        response.prettyPrint();
        response.then().statusCode(204);

    }


}


