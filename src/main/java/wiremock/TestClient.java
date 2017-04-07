package wiremock;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class TestClient {

    public void TestClient() {

    }

    public HttpResponse<String> get(String path) throws UnirestException {
        HttpResponse<String> helloResponse = Unirest.get("http://localhost:8080/" + path).asObject(String.class);
        System.out.println(helloResponse.getBody());
        return helloResponse;
    }
}
