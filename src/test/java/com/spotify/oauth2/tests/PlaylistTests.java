package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;

import org.testng.Assert;
import utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify Oauth 2.0")
@Feature("Playlist API")
public class PlaylistTests extends BaseTest {

    @Test(description = "should be able to create a playlist")
    public void ShouldBeAbleToCreateAPlaylist(){
        //1. post new request
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Test
    public void ShouldBeAbleToGetAPlaylist(){
        //2. get new request
        Playlist requestPlaylist = playlistBuilder("Updated Playlist Name", "Updated playlist description", true);
        Response response = PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
        Assert.assertEquals(response.statusCode(),StatusCode.CODE_200.code);
        System.out.println("response : ------------------------------------   "+response.as(Playlist.class));
        System.out.println("request : --------------------------------------  "+requestPlaylist);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Test
    public void ShouldBeAbleToUpdateAPlaylist(){
        //3. update new request
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.update(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        System.out.println("response.statusCode() : --------------   "+response.statusCode());
        System.out.println("StatusCode.CODE_200 : --------------------------------------  "+StatusCode.CODE_200);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
    }

    @Story("Create a playlist story")
    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithName(){
        //4. post new request
        Playlist requestPlaylist = playlistBuilder("", generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_400);
        assertError(response.as(Error.class), StatusCode.CODE_400);
    }

    @Story("Create a playlist story")
    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken(){
        //5. post new request
        String invalid_token = "12345";
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.post(invalid_token, requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_401);
        assertError(response.as(Error.class), StatusCode.CODE_401);
    }

    @Step
    public Playlist playlistBuilder(String name, String description, boolean _public){
        return Playlist.builder().
                name(name).
                description(description).
                _public(_public).
                build();
    }

    @Step
    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist){
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
    }

    @Step
    public void assertStatusCode(int actualStatusCode, StatusCode statusCode){
        assertThat(actualStatusCode, equalTo(statusCode.code));
    }

    @Step
    public void assertError(Error responseErr, StatusCode statusCode){
        assertThat(responseErr.getError().getStatus(), equalTo(statusCode.code));
        assertThat(responseErr.getError().getMessage(), equalTo(statusCode.msg));
    }


}
