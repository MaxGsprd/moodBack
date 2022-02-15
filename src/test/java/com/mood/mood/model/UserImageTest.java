package com.mood.mood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserImageTest {

    private UserImage imageNoArgs;
    private UserImage imageRequiredArgs;

    @BeforeEach
    void setUp() {
        imageNoArgs = new UserImage();

        User user = new User();
        imageRequiredArgs = new UserImage(
                "data name",
                "bytes".getBytes(),
                0L,
                "mimeType",
                user
        );
    }

    @Test
    void constructorTest() {
        assertEquals(imageNoArgs.getClass().getSimpleName(), "UserImage");
        assertEquals(imageNoArgs.getId(), 0);

        assertEquals(imageRequiredArgs.getClass().getSimpleName(), "UserImage");
        assertEquals(imageRequiredArgs.getId(), 0);
    }

    @Test
    void setUser() {
        User newUser = new User();
        imageNoArgs.setUser(newUser);
        assertEquals(imageNoArgs.getUser(), newUser);
    }
}