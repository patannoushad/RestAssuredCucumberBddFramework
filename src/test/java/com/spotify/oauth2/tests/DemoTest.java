package com.spotify.oauth2.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DemoTest {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String access_token="BQB1Yw_ySnCyAe9p_6vFC51uoo6cCYgO6Gl6x_rBBLjBjeDkv1-ztkxw2Usk_R1WkKsQd7zwNg5ND3AJoblKKMn40ZAIB3gaAj1jyIKfhjqx6-a-ctiHdQPUPp54MXHxLdHg97rOoGE0dE-kL9J8_0ljZ4XNlI0d12HVpGrB7INY33HbC1qCAJF2yrKEMaZRMSpEM80fXDx1LrQaEpuT6Td5sx39TLEP1IBR4Ekv78shDLs0wQQ2O3eAbfkCvQE-1BTO9g_2RO6TuMSr9ryyU-Os";

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder= new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com")
                .setBasePath("/v1")
                .addHeader("Authorization","Bearer "+access_token)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        requestSpecification=requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        responseSpecification=responseSpecBuilder.build();
    }

    @Test
    public void ShouldBeAbleToCreatePlaylist(){
        String payload="{\n" +
                "    \"name\": \"New Playlist\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";

        given(requestSpecification)
                .body(payload)
                .when().post("/users/31zghzhjvfg3ao3x6px4wogw6sc4/playlists")
                .then().spec(responseSpecification)
                .assertThat()
                .statusCode(201)
                .body("name",equalTo("New Playlist"),
                        "description",equalTo("New playlist description"),
                        "public",equalTo(false));
    }
}
