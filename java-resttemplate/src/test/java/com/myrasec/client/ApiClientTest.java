package com.myrasec.client;

import com.myrasec.client.api.EuCaptchaApi;
import com.myrasec.client.model.VerifyRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

class ApiClientTest {

    private RestTemplate restTemplate;
    private MockRestServiceServer server;

    private static final String VERIFY_URL = "https://api.eu-captcha.eu/v1/verify";

    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplate();
        server       = MockRestServiceServer.createServer(restTemplate);
    }

    private EuCaptchaApi makeApi(int maxAttempts) {
        ApiClient client = new ApiClient(restTemplate);
        client.setMaxAttemptsForRetry(maxAttempts);
        client.setWaitTimeMillis(0);
        return new EuCaptchaApi(client);
    }

    private VerifyRequest validRequest() {
        return new VerifyRequest()
                .sitekey("1c87e240-0000-0000-0000-23ac9f99da68")
                .secret("LqFgQA==")
                .clientIp("5.6.7.8")
                .clientToken("XVtHUQ==")
                .clientUserAgent("Mozilla/5.0");
    }

    // -------------------------------------------------------------------------
    // Retry on 5xx
    // -------------------------------------------------------------------------

    @Test
    void retryOn5xxSucceedsOnSecondAttempt() {
        server.expect(requestTo(VERIFY_URL)).andRespond(withServerError());
        server.expect(requestTo(VERIFY_URL)).andRespond(withSuccess("{\"success\":true}", MediaType.APPLICATION_JSON));

        assertTrue(makeApi(2).verifyClientToken(validRequest()).getSuccess());
        server.verify();
    }

    @Test
    void retriesUpToMaxAttemptsBeforeSuccess() {
        server.expect(requestTo(VERIFY_URL)).andRespond(withServerError());
        server.expect(requestTo(VERIFY_URL)).andRespond(withServerError());
        server.expect(requestTo(VERIFY_URL)).andRespond(withSuccess("{\"success\":true}", MediaType.APPLICATION_JSON));

        assertTrue(makeApi(3).verifyClientToken(validRequest()).getSuccess());
        server.verify();
    }

    @Test
    void retriesExhaustedThrows() {
        server.expect(requestTo(VERIFY_URL)).andRespond(withServerError());
        server.expect(requestTo(VERIFY_URL)).andRespond(withServerError());

        assertThrows(HttpServerErrorException.class, () -> makeApi(2).verifyClientToken(validRequest()));
        server.verify();
    }

    // -------------------------------------------------------------------------
    // Retry on 429
    // -------------------------------------------------------------------------

    @Test
    void retryOn429SucceedsOnSecondAttempt() {
        server.expect(requestTo(VERIFY_URL)).andRespond(withStatus(HttpStatus.TOO_MANY_REQUESTS));
        server.expect(requestTo(VERIFY_URL)).andRespond(withSuccess("{\"success\":true}", MediaType.APPLICATION_JSON));

        assertTrue(makeApi(2).verifyClientToken(validRequest()).getSuccess());
        server.verify();
    }

    // -------------------------------------------------------------------------
    // No retry on other 4xx
    // -------------------------------------------------------------------------

    @Test
    void noRetryOn400() {
        // Only one expectation: a second call would fail the server, proving no retry happened.
        server.expect(requestTo(VERIFY_URL)).andRespond(withBadRequest());

        assertThrows(HttpClientErrorException.class, () -> makeApi(3).verifyClientToken(validRequest()));
        server.verify();
    }

    @Test
    void noRetryOn404() {
        server.expect(requestTo(VERIFY_URL)).andRespond(withStatus(HttpStatus.NOT_FOUND));

        assertThrows(HttpClientErrorException.class, () -> makeApi(3).verifyClientToken(validRequest()));
        server.verify();
    }

    // -------------------------------------------------------------------------
    // Configuration
    // -------------------------------------------------------------------------

    @Test
    void customBasePathIsUsed() {
        ApiClient client = new ApiClient(restTemplate);
        client.setBasePath("https://custom.example.com/v1");
        client.setWaitTimeMillis(0);
        EuCaptchaApi api = new EuCaptchaApi(client);

        server.expect(requestTo("https://custom.example.com/v1/verify"))
              .andExpect(method(HttpMethod.POST))
              .andRespond(withSuccess("{\"success\":true}", MediaType.APPLICATION_JSON));

        api.verifyClientToken(validRequest());
        server.verify();
    }
}
