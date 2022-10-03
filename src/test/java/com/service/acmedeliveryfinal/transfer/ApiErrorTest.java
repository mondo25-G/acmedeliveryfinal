package com.service.acmedeliveryfinal.transfer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class ApiErrorTest {

    @Test
    void testConstructor() {
        ApiError actualApiError = new ApiError("The characteristics of someone or something", 1, "Path");
        String actualToStringResult = actualApiError.toString();
        assertEquals("The characteristics of someone or something", actualApiError.getDescription());
        assertEquals(1, actualApiError.getHttpStatus().intValue());
        assertEquals("Path", actualApiError.getPath());
        assertEquals("ApiError(description=The characteristics of someone or something, httpStatus=1, path=Path)",
                actualToStringResult);
    }


    @Test
    void testEquals() {
        assertNotEquals(new ApiError("The characteristics of someone or something", 1, "Path"), null);
        assertNotEquals(new ApiError("The characteristics of someone or something", 1, "Path"),
                "Different type to ApiError");
    }


    @Test
    void testEquals2() {
        ApiError apiError = new ApiError("The characteristics of someone or something", 1, "Path");
        ApiError apiError1 = new ApiError("The characteristics of someone or something", 1, "Path");

        assertEquals(apiError, apiError1);
        int expectedHashCodeResult = apiError.hashCode();
        assertEquals(expectedHashCodeResult, apiError1.hashCode());
    }


    @Test
    void testEquals3() {
        ApiError apiError = new ApiError("The characteristics of someone or something", 1, null);
        assertNotEquals(apiError, new ApiError("The characteristics of someone or something", 1, "Path"));
    }


    @Test
    void testEquals4() {
        ApiError apiError = new ApiError(null, 1, "Path");
        ApiError apiError1 = new ApiError(null, 1, "Path");

        assertEquals(apiError, apiError1);
        int expectedHashCodeResult = apiError.hashCode();
        assertEquals(expectedHashCodeResult, apiError1.hashCode());
    }






}

