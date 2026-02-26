package com.myrasec.client.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerifyRequestTest {

    private VerifyRequest buildValid() {
        return new VerifyRequest()
                .sitekey("1c87e240-0000-0000-0000-23ac9f99da68")
                .secret("LqFgQA==")
                .clientIp("5.6.7.8")
                .clientToken("XVtHUQ==")
                .clientUserAgent("Mozilla/5.0");
    }

    @Test
    void builderSetsAllFields() {
        VerifyRequest r = buildValid();
        assertEquals("1c87e240-0000-0000-0000-23ac9f99da68", r.getSitekey());
        assertEquals("LqFgQA==", r.getSecret());
        assertEquals("5.6.7.8", r.getClientIp());
        assertEquals("XVtHUQ==", r.getClientToken());
        assertEquals("Mozilla/5.0", r.getClientUserAgent());
    }

    @Test
    void nullSitekeyThrows() {
        assertThrows(IllegalArgumentException.class, () -> new VerifyRequest().setSitekey(null));
    }

    @Test
    void blankSitekeyThrows() {
        assertThrows(IllegalArgumentException.class, () -> new VerifyRequest().setSitekey("   "));
    }

    @Test
    void nullSecretThrows() {
        assertThrows(IllegalArgumentException.class, () -> new VerifyRequest().setSecret(null));
    }

    @Test
    void nullClientIpThrows() {
        assertThrows(NullPointerException.class, () -> new VerifyRequest().setClientIp(null));
    }

    @Test
    void nullClientTokenThrows() {
        assertThrows(NullPointerException.class, () -> new VerifyRequest().setClientToken(null));
    }

    @Test
    void nullClientUserAgentThrows() {
        assertThrows(NullPointerException.class, () -> new VerifyRequest().setClientUserAgent(null));
    }

    @Test
    void toStringMasksSecret() {
        String s = buildValid().toString();
        assertFalse(s.contains("LqFgQA=="), "Secret must not appear in toString");
        assertTrue(s.contains("[REDACTED]"), "toString must contain [REDACTED]");
    }

    @Test
    void equalityAndHashCode() {
        VerifyRequest a = buildValid();
        VerifyRequest b = buildValid();
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void inequalityOnDifferentSitekey() {
        VerifyRequest a = buildValid();
        VerifyRequest b = buildValid().sitekey("other-key-0000-0000-0000-23ac9f99da68");
        assertNotEquals(a, b);
    }
}
