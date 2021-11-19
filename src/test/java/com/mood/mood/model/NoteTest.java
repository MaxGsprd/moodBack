package com.mood.mood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteTest {

    private Note noteNoArgs;
    private Note noteAllArgs;

    @BeforeEach
    void setUp() {
        noteNoArgs = new Note();

        User user = new User();
        Establishment establishment = new Establishment();
        noteAllArgs = new Note(1, 4, establishment, user);
    }

    @Test
    void constructorTest() {
        assertEquals(noteNoArgs.getClass().getSimpleName(), "Note");
        assertEquals(noteNoArgs.getId(), 0);

        assertEquals(noteAllArgs.getClass().getSimpleName(), "Note");
        assertEquals(noteAllArgs.getId(), 1);
    }

    @Test
    void setId() {
        noteAllArgs.setId(8);
        assertEquals(noteAllArgs.getId(), 8);
    }

    @Test
    void setValue() {
        noteAllArgs.setValue(3);
        assertEquals(noteAllArgs.getValue(), 3);
    }

    @Test
    void setEstablishment() {
        Establishment newEstablishment = new Establishment();
        noteAllArgs.setEstablishment(newEstablishment);
        assertEquals(noteAllArgs.getEstablishment(), newEstablishment);
    }

    @Test
    void setUser() {
        User newUser = new User();
        noteAllArgs.setUser(newUser);
        assertEquals(noteAllArgs.getUser(), newUser);
    }
}