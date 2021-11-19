package com.mood.mood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    private Role roleNoArgs;
    private Role roleAllArgs;

    @BeforeEach
    void setUp() {
        roleNoArgs = new Role();
        roleAllArgs = new Role(1, "ROLE_ADMIN");
    }

    @Test
    void constructorTest() {
        assertEquals(roleNoArgs.getClass().getSimpleName(), "Role");
        assertEquals(roleNoArgs.getId(), 0);

        assertEquals(roleAllArgs.getClass().getSimpleName(), "Role");
        assertEquals(roleAllArgs.getId(), 1);
    }

    @Test
    void setId() {
        roleAllArgs.setId(8);
        assertEquals(roleAllArgs.getId(), 8);
    }

    @Test
    void setTitle() {
        roleAllArgs.setTitle("ROLE_USER");
        assertEquals(roleAllArgs.getTitle(), "ROLE_USER");
    }
}