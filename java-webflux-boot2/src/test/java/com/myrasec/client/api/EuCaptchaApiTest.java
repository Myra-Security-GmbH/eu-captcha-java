package com.myrasec.client.api;

import com.myrasec.client.model.VerifyRequest;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static org.junit.jupiter.api.Assertions.*;

class EuCaptchaApiTest {

    private final EuCaptchaApi api = new EuCaptchaApi();

    @Test
    void nullRequestThrowsWebClientResponseException() {
        assertThrows(WebClientResponseException.class, () -> api.verifyClientToken(null).block());
    }

    @Test
    void nullSitekeyOnRequestThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                new VerifyRequest().setSitekey(null));
    }
}
