package api;

import io.restassured.http.Headers;

public class RequestBuilder {

        String method;
        String url;
        String body;
        String autheCode;
        String basicAuth;
        String cookie;
        Headers headers;
        String accept;
        String contentType;
        String multiPartfile;

        //Constructors
        public RequestBuilder(){ }

    //Constructor with (Method,  URL
    public RequestBuilder(String Method, String URL){
        if(!body.equals("")) {
            this.setBody(body);
        }
        this.setMethod(Method);
        this.setUrl(URL);
        this.setAccept("application/json");
        this.setContentType("application/json");
    }


    //Constructor with (Method, Correlation ID, URL, Basic Auth, Request Body, additional headers
        public RequestBuilder(String Method, String URL, String body){
            if(!body.equals("")) {
                this.setBody(body);
            }
            this.setMethod(Method);
            this.setUrl(URL);
            this.setAccept("application/json");
            this.setContentType("application/json");
        }

        //Constructor with (Method, URL, Request Body, additional headers
        public RequestBuilder(String Method, String URL, String token,String body){
            if(!body.equals("")) {
                this.setBody(body);
            }
            this.setMethod(Method);
            this.setUrl(URL);
            this.setautheCode(token);
            this.setAccept("application/json");
            this.setContentType("application/json");
        }


        public String getAccept() { return accept; }

        public void setAccept(String accept) { this.accept = accept; }

        public String getContentType() { return contentType; }

        public void setContentType(String contentType) { this.contentType = contentType; }

        public io.restassured.http.Headers getHeaders() {
            return headers;
        }

        public void setHeaders(Headers headers) {
            this.headers = headers;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getmultiPartfile() {
            return multiPartfile;
        }

        public void setmultiPartfile(String multiPartfile) {
            this.multiPartfile = multiPartfile;
        }

        public String getautheCode() {
            return autheCode;
        }

        public void setautheCode(String autheCode) {
            this.autheCode = autheCode;
        }

        public String getBasicAuth() {
            return basicAuth;
        }

        public void setBasicAuth(String basicAuth) {
            this.basicAuth = basicAuth;
        }

        public String getCookie() {
            return cookie;
        }

        public void setCookie(String cookie) {
            this.cookie = cookie;
        }
}
