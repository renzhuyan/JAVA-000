package lesson04.question02.client;

import java.io.IOException;

public class HttpClientApplication {

    public static final String REQUEST_URL = "http://localhost:8808/test";

    public static void main(String[] args) throws IOException {
        String getResult = HttpClientUtil.httpGet(REQUEST_URL);
        String postResult = HttpClientUtil.httpPost(REQUEST_URL,null);
        System.out.println("getResult:"+getResult);
        System.out.println("postResult:"+postResult);
    }
}
