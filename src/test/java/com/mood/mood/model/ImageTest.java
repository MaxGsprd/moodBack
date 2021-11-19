package com.mood.mood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.util.Base64Utils.*;

class ImageTest {

    private Image imageNoArgs;
    private Image imageRequiredArgs;

    @BeforeEach
    void setUp() {
        imageNoArgs = new Image();
        imageRequiredArgs = new Image(
                "data name",
                "bytes".getBytes(),
                "dataImage64",
                "mimeType"
        );
    }

    @Test
    void constructorTest() {
        assertEquals(imageNoArgs.getClass().getSimpleName(), "Image");
        assertEquals(imageNoArgs.getId(), 0);

        assertEquals(imageRequiredArgs.getClass().getSimpleName(), "Image");
        assertEquals(imageRequiredArgs.getId(), 0);
    }

    @Test
    void setId() {
        imageNoArgs.setId(8);
        assertEquals(imageNoArgs.getId(), 8);
    }

    @Test
    void setDataName() {
        imageNoArgs.setDataName("new dataName");
        assertEquals(imageNoArgs.getDataName(), "new dataName");
    }

    @Test
    void setData64() {
        byte[] bytes = "new Bytes".getBytes();
        imageNoArgs.setData64(bytes);
        assertEquals(imageNoArgs.getData64(), bytes);
    }

    @Test
    void setDataImage64() {
        imageNoArgs.setDataImage64("new dataImage64");
        assertEquals(imageNoArgs.getDataImage64(), "new dataImage64");
    }

    @Test
    void setMimeType() {
        imageNoArgs.setMimeType("new mimeType");
        assertEquals(imageNoArgs.getMimeType(), "new mimeType");
    }
}