package com.mood.mood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private Category categoryNoArgs;
    private Category categoryAllArgs;

    @BeforeEach
    void setUp() {
        categoryNoArgs = new Category();
        categoryAllArgs = new Category(1, "title", "test decription");
    }

    @Test
    void constructorTest() {
        assertEquals(categoryNoArgs.getClass().getSimpleName(), "Category");
        assertEquals(categoryNoArgs.getId(), 0);

        assertEquals(categoryAllArgs.getClass().getSimpleName(), "Category");
        assertEquals(categoryAllArgs.getId(), 1);
    }

    @Test
    void setId() {
        categoryAllArgs.setId(8);
        assertEquals(categoryAllArgs.getId(), 8);
    }

    @Test
    void setTitle() {
        categoryAllArgs.setTitle("test title");
        assertEquals(categoryAllArgs.getTitle(), "test title");
    }

    @Test
    void setDescription() {
        categoryAllArgs.setDescription("test desc v2");
        assertEquals(categoryAllArgs.getDescription(), "test desc v2");
    }
}