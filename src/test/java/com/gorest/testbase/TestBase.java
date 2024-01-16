package com.gorest.testbase;

import com.gorest.propertyreader.PropertyReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class TestBase {

    @BeforeClass
    public void inIt(){
        RestAssured.baseURI = PropertyReader.getInstance().getProperty("baseURI");
        RestAssured.basePath = PropertyReader.getInstance().getProperty("basePath");
    }
}