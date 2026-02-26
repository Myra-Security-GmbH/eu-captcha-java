package com.myrasec.client.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myrasec.client.ApiClient;
import com.myrasec.client.model.VerifyRequest;
import com.myrasec.client.model.VerifyResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.http.client.MockClientHttpRequest;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class EuCaptchaApiTest {

    private MockRestServiceServer server;
    private EuCaptchaApi api;

    private static final ObjectMapper MAPPER     = new ObjectMapper();
    private static final String       VERIFY_URL = "https://api.eu-captcha.eu/v1/verify";

    @BeforeEach
    void setUp() {
        RestTemplate restTemplate = new RestTemplate();
        server = MockRestServiceServer.createServer(restTemplate);
        api    = new EuCaptchaApi(new ApiClient(restTemplate));
    }

    private VerifyRequest validRequest() {
        return new VerifyRequest()
                .sitekey("1c87e240-0000-0000-0000-23ac9f99da68")
                .secret("LqFgQA==")
                .clientIp("5.6.7.8")
                .clientToken("XVtHUQ==")
                .clientUserAgent("Mozilla/5.0");
    }

    /** Enqueue a single POST /verify expectation with the given JSON response body. */
    private void expectVerify(String responseBody) {
        server.expect(requestTo(VERIFY_URL))
              .andExpect(method(HttpMethod.POST))
              .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));
    }

    /** Matcher that deserialises the request body as JSON and asserts one field. */
    private static RequestMatcher jsonField(String fieldName, Object expectedValue) {
        return request -> {
            byte[] body = ((MockClientHttpRequest) request).getBodyAsBytes();
            @SuppressWarnings("unchecked")
            Map<String, Object> map = MAPPER.readValue(body, Map.class);
            assertEquals(expectedValue, map.get(fieldName), "JSON field '" + fieldName + "'");
        };
    }

    // -------------------------------------------------------------------------
    // Null guards
    // -------------------------------------------------------------------------

    @Test
    void nullRequestThrowsHttpClientErrorException() {
        assertThrows(HttpClientErrorException.class, () -> api.verifyClientToken(null));
    }

    @Test
    void nullSitekeyOnRequestThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                new VerifyRequest().setSitekey(null));
    }

    // -------------------------------------------------------------------------
    // Response deserialization
    // -------------------------------------------------------------------------

    @Test
    void successResponseReturnsSuccessTrue() {
        expectVerify("{\"success\":true}");
        assertTrue(api.verifyClientToken(validRequest()).getSuccess());
    }

    @Test
    void failureResponseReturnsSuccessFalse() {
        expectVerify("{\"success\":false}");
        assertFalse(api.verifyClientToken(validRequest()).getSuccess());
    }

    @Test
    void trainTrueResponseIsDetected() {
        expectVerify("{\"success\":true,\"train\":true}");
        VerifyResponse r = api.verifyClientToken(validRequest());
        assertTrue(r.isTrainingMode());
        assertTrue(r.getSuccess());
    }

    @Test
    void trainFalseResponseIsNotTrainingMode() {
        expectVerify("{\"success\":true,\"train\":false}");
        assertFalse(api.verifyClientToken(validRequest()).isTrainingMode());
    }

    @Test
    void trainAbsentMeansNotTrainingMode() {
        expectVerify("{\"success\":true}");
        assertFalse(api.verifyClientToken(validRequest()).isTrainingMode());
    }

    // -------------------------------------------------------------------------
    // Request body
    // -------------------------------------------------------------------------

    @Test
    void requestBodyContainsSitekey() {
        server.expect(requestTo(VERIFY_URL))
              .andExpect(jsonField("sitekey", "1c87e240-0000-0000-0000-23ac9f99da68"))
              .andRespond(withSuccess("{\"success\":true}", MediaType.APPLICATION_JSON));
        api.verifyClientToken(validRequest());
        server.verify();
    }

    @Test
    void requestBodyContainsSecret() {
        server.expect(requestTo(VERIFY_URL))
              .andExpect(jsonField("secret", "LqFgQA=="))
              .andRespond(withSuccess("{\"success\":true}", MediaType.APPLICATION_JSON));
        api.verifyClientToken(validRequest());
        server.verify();
    }

    @Test
    void requestBodyContainsClientIp() {
        server.expect(requestTo(VERIFY_URL))
              .andExpect(jsonField("client_ip", "5.6.7.8"))
              .andRespond(withSuccess("{\"success\":true}", MediaType.APPLICATION_JSON));
        api.verifyClientToken(validRequest());
        server.verify();
    }

    @Test
    void requestBodyContainsClientToken() {
        server.expect(requestTo(VERIFY_URL))
              .andExpect(jsonField("client_token", "XVtHUQ=="))
              .andRespond(withSuccess("{\"success\":true}", MediaType.APPLICATION_JSON));
        api.verifyClientToken(validRequest());
        server.verify();
    }

    @Test
    void requestBodyContainsClientUserAgent() {
        server.expect(requestTo(VERIFY_URL))
              .andExpect(jsonField("client_user_agent", "Mozilla/5.0"))
              .andRespond(withSuccess("{\"success\":true}", MediaType.APPLICATION_JSON));
        api.verifyClientToken(validRequest());
        server.verify();
    }

    // -------------------------------------------------------------------------
    // HTTP mechanics
    // -------------------------------------------------------------------------

    @Test
    void requestIsPostedToVerifyEndpoint() {
        server.expect(requestTo(VERIFY_URL))
              .andExpect(method(HttpMethod.POST))
              .andRespond(withSuccess("{\"success\":true}", MediaType.APPLICATION_JSON));
        api.verifyClientToken(validRequest());
        server.verify();
    }

    @Test
    void requestContentTypeIsJson() {
        server.expect(requestTo(VERIFY_URL))
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andRespond(withSuccess("{\"success\":true}", MediaType.APPLICATION_JSON));
        api.verifyClientToken(validRequest());
        server.verify();
    }
}
