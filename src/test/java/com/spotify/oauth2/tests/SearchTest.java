package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.SearchApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SearchTest {

    @Test
    public void ShouldBeAbleToGetAPlaylist(){
        Response response= SearchApi.get();
        assertStatusCode(response.getStatusCode(),StatusCode.CODE_200);
    }
    @Step
    public void assertStatusCode(int actualStatusCode, StatusCode statusCode){
        assertThat(actualStatusCode, equalTo(statusCode.code));
    }
}
