package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        Map<String,Object> qParams = new HashMap<>();
        qParams.put("page", 1);
        qParams.put("per_page",20);
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParams(qParams)
                .when()
                .get("/users")
                .then().statusCode(200);
        // response.log().all(); to print console

    }

    // 1. Extract the All Ids

    @Test
    public void test001() {

        List<Integer> listOfIds = response.extract().path("id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");

    }
    //2. Extract the all Names
    @Test
    public void test002() {

        List<Integer> listOfIds = response.extract().path("name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all names are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");

    }

    //3. Extract the name of 5th object

    @Test
    public void test003() {

        String fifthName = response.extract().path("name[4]");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th object is : " + fifthName);
        System.out.println("------------------End of Test---------------------------");

    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004() {

        List<String> allObjectNames = response.extract().path("findAll{it.status == 'inactive'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all object whose status = inactiveare : " + allObjectNames);
        System.out.println("------------------End of Test---------------------------");

    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void test005() {

        List<String> genderOfAllObject = response.extract().path("findAll{it.status == 'active'}.gender");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gender of all the object whose status = active are : " + genderOfAllObject);
        System.out.println("------------------End of Test---------------------------");

    }


    //6. Print the names of the object whose gender = female
    @Test
    public void test006() {

        List<String> namesOfAllObject = response.extract().path("findAll{it.gender == 'female'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the object whose gender = female are : " + namesOfAllObject);
        System.out.println("------------------End of Test---------------------------");

    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007() {

        List<String> emailsOfAllObject = response.extract().path("findAll{it.status == 'inactive'}.email");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the emails of the object where status = inactive are : " + emailsOfAllObject);
        System.out.println("------------------End of Test---------------------------");

    }


    //8. Get the ids of the object where gender = male
    @Test
    public void test008() {

        List<String> IdsOfAllObject = response.extract().path("findAll{it.gender == 'male'}.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ids of the object where gender = male are : " + IdsOfAllObject);
        System.out.println("------------------End of Test---------------------------");

    }
    //9. Get all the status

    @Test
    public void test009() {

        List<String> allStatus = response.extract().path("status");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of status are : " + allStatus);
        System.out.println("------------------End of Test---------------------------");

    }

    //10. Get email of the object where name = Chandini Prajapat
    @Test
    public void test010() {

        List<String> emailOfTheObject = response.extract().path("findAll{it.name == 'Chandini Prajapat'}.email");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("email of the object where name = Chandini Prajapat is : " + emailOfTheObject);
        System.out.println("------------------End of Test---------------------------");

    }

    //11. Get gender of id = 5914133
    @Test
    public void test011() {

        List<String> gender = response.extract().path("findAll{it.id == 5914133}.gender");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("gender of id = 5914133 is : " + gender);
        System.out.println("------------------End of Test---------------------------");

    }
}
