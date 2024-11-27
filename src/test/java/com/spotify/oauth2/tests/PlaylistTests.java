package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;

import utils.ConfigReader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests extends BaseTest {

    public Playlist playlistBuilder(String name, String description, boolean _public){
        return Playlist.builder().
                name(name).
                description(description).
                _public(_public).
                build();
    }

    @Test(description = "should be able to create a playlist")
    public void ShouldBeAbleToCreateAPlaylist(){
        //1. post new request
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_201.code));
        assertThat(response.as(Playlist.class).getName(),equalTo(requestPlaylist.getName()));
        assertThat(response.as(Playlist.class).getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(response.as(Playlist.class).get_public(), equalTo(requestPlaylist.get_public()));
    }

    @Test
    public void ShouldBeAbleToGetAPlaylist(){
        //2. get new request
        Playlist requestPlaylist = playlistBuilder("Updated Playlist Name", "Updated playlist description", true);
        Response response = PlaylistApi.get(ConfigReader.getPropertyValue("get_playlist_id"));
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.code));
        assertThat(response.as(Playlist.class).getName(),equalTo(requestPlaylist.getName()));
        assertThat(response.as(Playlist.class).getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(response.as(Playlist.class).get_public(), equalTo(requestPlaylist.get_public()));
    }

    @Test
    public void ShouldBeAbleToUpdateAPlaylist(){
        //3. update new request
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.update(ConfigReader.getPropertyValue("update_playlist_id"), requestPlaylist);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.code));
    }

    @Story("Create a playlist story")
    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithName(){
        //4. post new request
        Playlist requestPlaylist = playlistBuilder("", generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_400.code));
        assertThat(response.as(Error.class).getError().getStatus(),equalTo(StatusCode.CODE_400.code));
        assertThat(response.as(Error.class).getError().getMessage(),equalTo(StatusCode.CODE_400.msg));
    }

    @Story("Create a playlist story")
    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken(){
        //5. post new request
        String invalid_token = "12345";
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.post(invalid_token, requestPlaylist);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_401.code));
        assertThat(response.as(Error.class).getError().getMessage(),equalTo(StatusCode.CODE_401.msg));
    }

    @Test
    public void addItemsToTheTrack(){
//        6. add items to track
//        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
//        Response response = PlaylistApi.postItemsPlaylist(requestPlaylist);
//        assertThat(response.statusCode(), equalTo(StatusCode.CODE_201.code));
//        assertThat(response.as(Playlist.class).getName(),equalTo(requestPlaylist.getName()));
//        assertThat(response.as(Playlist.class).getDescription(), equalTo(requestPlaylist.getDescription()));
//        assertThat(response.as(Playlist.class).get_public(), equalTo(requestPlaylist.get_public()));
    }
}
