package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.SEARCH;
import static com.spotify.oauth2.api.TokenManager.getToken;

public class SearchApi {

    public static Response get(){
        return RestResource.get(SEARCH+"?q=remaster%2520track%3ADoxy%2520artist%3AMiles%2520Davis&type=playlist" , getToken());
    }
}
