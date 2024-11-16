package api;

import api.endpoints.Routes;
import api.payload.User;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.*;

public class TestCases {

    public static String Token;
    public static String empID;
    Faker faker;
    User userPayload;

    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("test-output/SparkReport/MySparkReport.html");

    @BeforeTest
    public void beforeTest() {
        spark.config().setDocumentTitle("Report");
        extent.attachReporter(spark);
    }
    @AfterTest
    public void tearDown(){
        extent.flush();
    }

//    @BeforeMethod
//    public void getToken(){
//
//        String payload = "{\n" +
//                "\"username\": \"np3@eigital.com\",\n" +
//                "\"password\": \"@Password123\"\n" +
//                "}";
//
//
//        RequestBuilder requestBuilder = new RequestBuilder("post", Routes.post_url,payload);
//
//        RestResponse restResponse = RestResponse.getRestResponse(requestBuilder);
//
//        if(restResponse.getStatusCode()==200){
//            JsonPath jsonPath = new JsonPath(restResponse.getResponse().asString());
//            Token = jsonPath.getString("response.token");
//            empID=jsonPath.getString("response.employeeId");
//        }
   // }

    @BeforeClass
    public void setup()
    {
        faker=new Faker();
        userPayload=new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

    }

    @Test
    public void testZero(){
        RequestBuilder requestBuilder = new RequestBuilder("post",Routes.post_url);

        RestResponse restResponse = RestResponse.getRestResponse(requestBuilder);
        restResponse.response.then().log().all();
    }


    @Test
    public void testOne(){
        ExtentTest test = extent.createTest("verify the Token").assignAuthor("Noushad").assignCategory("smoke Test");
        test.info("Resquest Builder Responde");

        RequestBuilder requestBuilder = new RequestBuilder("get",Routes.get_url, userPayload.getUsername());

        RestResponse restResponse = RestResponse.getRestResponse(requestBuilder);
        System.out.println(restResponse.getStatusCode());
        System.out.println(restResponse.getResponse().asString());
        if(restResponse.getStatusCode()==200){
            System.out.println(restResponse.getResponse().asString());
            JsonPath jsonPath = new JsonPath(restResponse.getResponse().asString());
            /*Token = jsonPath.getString("response.token");
            empID=jsonPath.getString("response.employeeId");*/
        }
    }
    @Test
    public void rahulsetty() {
        ExtentTest test = extent.createTest("varigy rahul setty url").assignAuthor("Noushad").assignCategory("smoke Test");
        test.info("Resquest Builder Responde");
        RequestBuilder requestBuilder = new RequestBuilder("post", Routes.rahul_base_url, userPayload.getUsername());
        RestResponse restResponse = RestResponse.getRestResponse(requestBuilder);
        System.out.println(restResponse.getStatusCode());
    }

    @Test
    public void createPlayList(){


    }


}
