package com.task.feature;

import org.junit.BeforeClass;

import io.restassured.RestAssured;

/**
 * @author nikolay.tashev on 26/01/2018.
 */
public class IntegrationBaseTest {

    @BeforeClass
    public static void setup() {
        String port = System.getProperty("SERVER_PORT");
        if (port == null) {
            RestAssured.port = 8001;
        } else {
            RestAssured.port = Integer.valueOf(port);
        }


        String basePath = System.getProperty("SERVER_BASE_PATH");
        if (basePath == null) {
            basePath = "/task/api/v1/";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("SERVER_HOST");
        if (baseHost == null) {
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;

    }
}
