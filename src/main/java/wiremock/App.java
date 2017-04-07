package wiremock;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        TestClient testClient = new TestClient();
        try {
            testClient.get("/some/thing");
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
