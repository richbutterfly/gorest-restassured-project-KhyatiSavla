package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        Map<String, Object> qParams = new HashMap<>();
        qParams.put("page", 1);
        qParams.put("per_page", 20);
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParams(qParams)
                .when()
                .get("/users")
                .then().statusCode(200);
        // response.log().all(); to print console

    }

    // 1. Verify the if the total record is 20
    @Test
    public void test001() {
        List<Integer> totalRecord = response.extract().path("id");
        Assert.assertEquals(totalRecord.size(),20);

    }

    //2. Verify the if the name of id = 5914062 is equal to ”Tara Panicker”
    @Test
    public void test002() {
        response.body("find{it.id == 5914062}.name", equalTo("Tara Panicker"));

    }

    //3. Check the single ‘Name’ in the Array list (Ramaa Banerjee)
    @Test
    public void test003() {
        response.body("name", hasItem("Ramaa Banerjee"));

    }

    //4. Check the multiple ‘Names’ in the ArrayList (Hiranya Gowda, Shivakari Bandopadhyay, Adhiraj Adiga )
    @Test
    public void test004() {
        response.body("name", hasItems("Hiranya Gowda", "Shivakari Bandopadhyay", "Adhiraj Adiga"));
    }


    //5. Verify the email of userid = 5914042 is equal “Menka Gandhi”
    @Test
    public void test005() {

        response.body("find{it.id == 5914042}.email", equalTo("menka_gandhi@brakus.example"));
    }


    //6. Verify the status is “Active” of username is “Vasundhara Gandhi I”
    @Test
    public void test006() {
        response.body("find{it.name == 'Vasundhara Gandhi I'}.status", equalTo("active"));

    }

    //7. Verify the Gender = male of user name is “Heema Kaniyar”
    @Test
    public void test007() {
        response.body("find{it.name == 'Heema Kaniyar'}.gender", equalTo("male"));
    }
}