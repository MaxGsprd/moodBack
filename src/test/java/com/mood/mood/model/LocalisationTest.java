package com.mood.mood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocalisationTest {

    private Localisation localisationNoArgs;
    private Localisation localisationAllArgs;

    @BeforeEach
    void setUp() {
        localisationNoArgs = new Localisation();
        localisationAllArgs = new Localisation(
                1,
                0.1234,
                4.321
        );
    }

    @Test
    void constructorTest() {
        assertEquals(localisationNoArgs.getClass().getSimpleName(), "Localisation");
        assertEquals(localisationNoArgs.getId(), 0);

        assertEquals(localisationAllArgs.getClass().getSimpleName(), "Localisation");
        assertEquals(localisationAllArgs.getId(), 1);
    }

    @Test
    void setId() {
        localisationAllArgs.setId(8);
        assertEquals(localisationAllArgs.getId(), 8);
    }

    @Test
    void setLongitude() {
        localisationAllArgs.setLongitude(4.321);
        assertEquals(localisationAllArgs.getLongitude(), 4.321);
    }

    @Test
    void setLatitude() {
        localisationAllArgs.setLatitude(1.234);
        assertEquals(localisationAllArgs.getLatitude(), 1.234);
    }
}