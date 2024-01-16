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

public class PostsAssertionTest {

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

    // 1. Verify the if the total record is 25
    @Test
    public void test001() {
        List<Integer> totalRecord = response.extract().path("id");
        Assert.assertEquals(totalRecord.size(),25);

    }
    //2. Verify the if the title of id = 93943 is equal to ”Textus nihil voluptatem autem totam consectetur accusantium.”
    @Test
    public void test002() {
        response.body("find{it.id == 93943}.title", equalTo("Textus nihil voluptatem autem totam consectetur accusantium."));

    }
    //3. Check the single user_id in the Array list (5914181)
    @Test
    public void test003() {
        response.body("user_id", hasItem(5914181));

    }


    //4. Check the multiple ids in the ArrayList (5914181, 5914161, 5914156)

    @Test
    public void test004() {
        response.body("user_id", hasItems(5914181, 5914161, 5914156));

    }


    //5. Verify the body of userid = 5914068 is equal "Optio velit assumenda. Curis repellendus vulgus. Acerbitas vel advoco. Ventus debitis volaticus. Crepusculum cenaculum omnis. Angustus aut desipio. Commodi bellicus concedo. Denique decretum depulso. Depono vacuus cattus. Desidero sit ut. Argentum vilis claudeo. Coadunatio dolores cibo. Apostolus dolor virga. Vorago voluptatum enim."

    @Test
    public void test005() {

        response.body("find{it.user_id == 5914068}.body", equalTo("Optio velit assumenda. Curis repellendus vulgus. Acerbitas vel advoco. Ventus debitis volaticus. Crepusculum cenaculum omnis. Angustus aut desipio. Commodi bellicus concedo. Denique decretum depulso. Depono vacuus cattus. Desidero sit ut. Argentum vilis claudeo. Coadunatio dolores cibo. Apostolus dolor virga. Vorago voluptatum enim."));
    }
}
