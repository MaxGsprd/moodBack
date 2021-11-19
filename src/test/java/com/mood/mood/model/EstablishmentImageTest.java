package com.mood.mood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstablishmentImageTest {

    private EstablishmentImage imageNoArgs;
    private EstablishmentImage imageRequiredArgs;

    @BeforeEach
    void setUp() {
        imageNoArgs = new EstablishmentImage();

        Establishment establishment = new Establishment();
        imageRequiredArgs = new EstablishmentImage(
                "data name",
                "bytes".getBytes(),
                "dataImage64",
                "mimeType",
                establishment
        );
    }

    @Test
    void constructorTest() {
        assertEquals(imageNoArgs.getClass().getSimpleName(), "EstablishmentImage");
        assertEquals(imageNoArgs.getId(), 0);

        assertEquals(imageRequiredArgs.getClass().getSimpleName(), "EstablishmentImage");
        assertEquals(imageRequiredArgs.getId(), 0);
    }

    @Test
    void setEstablishment() {
        Establishment newEstablishment = new Establishment();
        imageNoArgs.setEstablishment(newEstablishment);
        assertEquals(imageNoArgs.getEstablishment(), newEstablishment);
    }
}