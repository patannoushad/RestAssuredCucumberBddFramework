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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayListStpDef extends BaseTest {
    Playlist requestPlaylist;
    Response response;
    public Playlist playlistBuilder(String name, String description, boolean _public){
        return Playlist.builder().
                name(name).
                description(description).
                _public(_public).
                build();
    }

    @Given("user pass the payload")
    public void user_pass_the_payload() {

        requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);

        requestPlaylist = Playlist.builder()
                .name(FakerUtils.generateName())
                .description(FakerUtils.generateDescription())
                ._public(false)
                .build();
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
//        new PlaylistTests().assertStatusCode(response.getStatusCode(),StatusCode.CODE_400);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_201.code));
        assertThat(response.as(Playlist.class).getName(),equalTo(requestPlaylist.getName()));
        assertThat(response.as(Playlist.class).getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(response.as(Playlist.class).get_public(), equalTo(requestPlaylist.get_public()));
    }
}
