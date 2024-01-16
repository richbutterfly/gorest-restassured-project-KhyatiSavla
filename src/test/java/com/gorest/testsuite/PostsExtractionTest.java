package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        Map<String, Object> qParams = new HashMap<>();
        qParams.put("page", 1);
        qParams.put("per_page", 25);
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParams(qParams)
                .when()
                .get("/posts")
                .then().statusCode(200);
        // response.log().all(); to print console

    }
    // 1. Extract the title
    @Test
    public void test001() {

        List<String> title = response.extract().path("title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of titles are : " + title);
        System.out.println("------------------End of Test---------------------------");

    }
    //2. Extract the total number of record

    @Test
    public void test002() {

        int totalNoOfRecords = response.extract().path("size()");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of records are : " + totalNoOfRecords);
        System.out.println("------------------End of Test---------------------------");

    }
    //3. Extract the body of 15th record
    @Test
    public void test003() {

        String bodyOf15thRecord = response.extract().path("[14].body");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of 15th record is : " + bodyOf15thRecord);
        System.out.println("------------------End of Test---------------------------");

    }
    //4. Extract the user_id of all the records

    @Test
    public void test004() {

        List<Integer> userIdsOfAllRecords = response.extract().path("user_id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The user_id of all the records are : " + userIdsOfAllRecords);
        System.out.println("------------------End of Test---------------------------");

    }
    //5. Extract the title of all the records

    @Test
    public void test005() {

        List<String> titleOfAllRecords = response.extract().path("title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all the records are : " + titleOfAllRecords);
        System.out.println("------------------End of Test---------------------------");

    }

    //6. Extract the title of all records whose user_id = 5914249
    @Test
    public void test006() {

        List<?> titleOfRecord = response.extract().path("findAll{it.user_id == 5914249}.title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all records whose user_id = 5914243 is : " + titleOfRecord);
        System.out.println("------------------End of Test---------------------------");

    }
    //7. Extract the body of all records whose id = 93950

    @Test
    public void test007() {

        List<?> bodyOfRecord = response.extract().path("findAll{it.id == 93950}.body");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of all records whose id = 93950 is : " + bodyOfRecord);
        System.out.println("------------------End of Test---------------------------");

    }
}
