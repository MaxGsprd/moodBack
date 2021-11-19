package com.mood.mood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstablishmentTest {

    private Establishment establishmentNoArgs;
    private Establishment establishmentAllArgs;


    @BeforeEach
    void setUp() {
        Localisation localisation = new Localisation();
        Category category = new Category();

        List<EstablishmentImage> establishmentImageList = new ArrayList<EstablishmentImage>();
        List<Note> notes = new ArrayList<Note>();
        List<Comment> comments = new ArrayList<Comment>();

        establishmentNoArgs = new Establishment();
        establishmentAllArgs = new Establishment(
                0,
                "establishmentName",
                "establishmentDescription",
                true,
                localisation,
                category,
                establishmentImageList,
                notes,
                comments
        );
    }

    @Test
    void constructorTest() {
        assertEquals(establishmentNoArgs.getClass().getSimpleName(), "Establishment");
        assertEquals(establishmentNoArgs.getId(), 0);

        assertEquals(establishmentAllArgs.getClass().getSimpleName(), "Establishment");
        assertEquals(establishmentAllArgs.getId(), 0);
    }

    @Test
    void setId() {
        establishmentAllArgs.setId(8);
        assertEquals(establishmentAllArgs.getId(), 8);
    }

    @Test
    void setName() {
        establishmentAllArgs.setName("establishmentNameTest");
        assertEquals(establishmentAllArgs.getName(),"establishmentNameTest");
    }

    @Test
    void setDescription() {
        establishmentAllArgs.setDescription("establishmentDescriptionTest");
        assertEquals(establishmentAllArgs.getDescription(),"establishmentDescriptionTest");
    }

    @Test
    void setStatus() {
        establishmentAllArgs.setStatus(true);
        assertEquals(establishmentAllArgs.getStatus(),true);
    }

    @Test
    void setLocalisation() {
        Localisation localisationTest = new Localisation();
        establishmentAllArgs.setLocalisation(localisationTest);
        assertEquals(establishmentAllArgs.getLocalisation(), localisationTest);
    }

    @Test
    void setCategory() {
        Category categoryTest = new Category();
        establishmentAllArgs.setCategory(categoryTest);
        assertEquals(establishmentAllArgs.getCategory(), categoryTest);
    }

    @Test
    void addImage() {
       EstablishmentImage imageTest = new EstablishmentImage();
       establishmentAllArgs.addImage(imageTest);
       assertTrue(establishmentAllArgs.getImages().contains(imageTest));
    }

    @Test
    void addComment() {
       Comment commentTest = new Comment();
       establishmentAllArgs.addComment(commentTest);
       assertTrue(establishmentAllArgs.getComments().contains(commentTest));
    }

    @Test
    void addNote() {
        Note noteTest = new Note();
        establishmentAllArgs.addNote(noteTest);
        assertTrue(establishmentAllArgs.getNotes().contains(noteTest));
    }
}