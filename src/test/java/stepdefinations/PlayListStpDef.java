package stepdefinations;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.tests.BaseTest;
import com.spotify.oauth2.tests.PlaylistTests;
import com.spotify.oauth2.utils.FakerUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;

public class PlayListStpDef extends BaseTest {
    Playlist requestPlaylist;
    Response response;

    @Given("user pass the payload")
    public void user_pass_the_payload() {
        System.out.println(requestPlaylist);
        requestPlaylist = Playlist.builder()
                .name(FakerUtils.generateName())
                .description(FakerUtils.generateDescription())
                ._public(false)
                .build();
        System.out.println(requestPlaylist);
    }
    @When("user hit the endpoint")
    public void user_hit_the_endpoint() {
        response = PlaylistApi.post(requestPlaylist);
        System.out.println(response);
    }
    @Then("user should get the response")
    public void user_should_get_the_response() {
//        assertStatusCode(response.statusCode(), StatusCode.CODE_201);
//        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
//        Assert.assertEquals(response.getStatusCode(),StatusCode.CODE_201);
        new PlaylistTests().assertStatusCode(response.getStatusCode(),StatusCode.CODE_400);
        System.out.println("response  :  ------------- " +response.getStatusCode());
        System.out.println("status  :  ------------- " +StatusCode.CODE_400);

    }
}
