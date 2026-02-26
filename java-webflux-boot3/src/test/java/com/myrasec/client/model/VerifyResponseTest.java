package com.myrasec.client.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerifyResponseTest {

    @Test
    void defaultsAreNull() {
        VerifyResponse r = new VerifyResponse();
        assertNull(r.getSuccess());
        assertNull(r.getTrain());
    }

    @Test
    void isTrainingModeReturnsFalseWhenNull() {
        assertFalse(new VerifyResponse().isTrainingMode());
    }

    @Test
    void isTrainingModeReturnsFalseWhenFalse() {
        assertFalse(new VerifyResponse().train(false).isTrainingMode());
    }

    @Test
    void isTrainingModeReturnsTrueWhenTrue() {
        assertTrue(new VerifyResponse().train(true).isTrainingMode());
    }

    @Test
    void builderSetsFields() {
        VerifyResponse r = new VerifyResponse().success(true).train(false);
        assertTrue(r.getSuccess());
        assertFalse(r.getTrain());
    }

    @Test
    void equalityAndHashCode() {
        VerifyResponse a = new VerifyResponse().success(true).train(false);
        VerifyResponse b = new VerifyResponse().success(true).train(false);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void inequalityOnDifferentSuccess() {
        VerifyResponse a = new VerifyResponse().success(true);
        VerifyResponse b = new VerifyResponse().success(false);
        assertNotEquals(a, b);
    }

    @Test
    void toStringContainsFields() {
        VerifyResponse r = new VerifyResponse().success(true).train(false);
        String s = r.toString();
        assertTrue(s.contains("success"));
        assertTrue(s.contains("train"));
    }
}
