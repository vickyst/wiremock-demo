package wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Unit test for simple App.
 */
public class Tests {

    private final RestTemplate restTemplate = new RestTemplate();

    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

    @Test
    public void statusIsOK() throws UnirestException {

        stubFor(get(urlEqualTo("/some/path"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Hello world!")));

        TestClient testClient = new TestClient();

        assertThat(testClient.get("/some/path").getStatus(), is(200));

    }

    @Test
    public void responseContainsString() throws UnirestException {

        stubFor(get(urlEqualTo("/some/path"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("foo")));

        final ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/some/path", String.class);
        assertThat(response.getBody(), containsString("foo"));

    }
}
