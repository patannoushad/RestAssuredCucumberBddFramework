package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Arrays;
import java.util.List;
import static io.restassured.RestAssured.given;


public class RestResponse {

        Response response;
        Headers headers;
        int statusCode;
        long responseTime;
        private static RequestBuilder requestBuilder;
        private static RequestSpecification requestSpec;

        public static RestResponse getRestResponse(RequestBuilder RequestBuilder) {
            requestBuilder = RequestBuilder;
            requestSpec = buildDefaultRequestSpec();
            return getRestAssuredResponse();
        }

        public static RestResponse getRestResponse(RequestBuilder RequestBuilder, RequestSpecification requestSpecification) {
            requestBuilder = RequestBuilder;
            requestSpec = requestSpecification;
            return getRestAssuredResponse();
        }

        private static RequestSpecification buildDefaultRequestSpec() {
            RequestSpecBuilder builder = new RequestSpecBuilder();
            Headers requestHeaders = requestBuilder.getHeaders();

            if(requestHeaders !=null) {
                for (Header header : requestHeaders) {
                    builder.addHeader(header.getName(), header.getValue());
                }
            }

            if(requestBuilder.getAccept()!=null) {
                builder.addHeader("Accept", requestBuilder.getAccept());
            }

            if(requestBuilder.getContentType()!=null) {
                builder.addHeader("Content-Type", requestBuilder.getContentType());
            }

            /*if(requestBuilder.getautheCode()!=null) {
                builder.addHeader("Authorization", requestBuilder.getautheCode());
            }*/

            if(requestBuilder.getCookie() != null) {
                builder.addHeader("Cookie", requestBuilder.getCookie());
            }

            if(!requestBuilder.getUrl().contains("https://")) {
                builder.setPort(80);
            }

            else {
                builder.setPort(443);
            }

            return builder.build();
        }

        private static RestResponse getRestAssuredResponse() {
            RestResponse RestResponse = new RestResponse();
            Response restResponseObject = null;
            List<String> updateMethods = Arrays.asList( "post", "delete", "put", "patch") ;


//            if(updateMethods.contains(requestBuilder.getMethod().toLowerCase())) {
//                restResponseObject = performUpdateRequest();
//            }
//            else

            if(requestBuilder.getMethod().equalsIgnoreCase("post" )) {
                restResponseObject =
                        given().log().all()
                                .spec(requestSpec)
                                .when()
                            //    .log().all()
                                .body(requestBuilder.getBody())
                                .post(requestBuilder.getUrl())
                                .then()
                                .extract()
                                .response();
            }
            else if(requestBuilder.getMethod().equalsIgnoreCase("get")) {

                restResponseObject =
                        given()
                                .when()
                                .get(requestBuilder.getUrl())
                                .then()
                                .extract()
                                .response();
            }

            RestResponse.response = restResponseObject;
            RestResponse.headers = restResponseObject.headers();
            RestResponse.statusCode = restResponseObject.getStatusCode();
            RestResponse.responseTime = restResponseObject.getTime();

            return RestResponse;
        }

        private static Response performUpdateRequest(){
            Response restResponseObject;
            if (requestBuilder.getBody() != null) {
                restResponseObject =
                        given()
                                .spec(requestSpec)
                                .body(requestBuilder.getBody())
                                .when()
                                .request(requestBuilder.getMethod(), requestBuilder.getUrl())
                                .then()
                                .extract()
                                .response();
            } else {
                restResponseObject =
                        given()
                                .spec(requestSpec)
                                .when()
                                .request(requestBuilder.getMethod(), requestBuilder.getUrl())
                                .then()
                                .extract()
                                .response();
            }

            return restResponseObject;
        }

        public Response getResponse() {    return response;  }

        public  Headers getHeaders() {
            return headers;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public long getResponseTime() {
            return responseTime;
        }

}
