package com.mood.mood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User userNoArgs;
    private User userAllArgs;

    @BeforeEach
    void setUp() {
        userNoArgs = new User();

        LocalDate birthDate = LocalDate.of(
                1997,
                06,
                04
        );
        List<Group> groups = new ArrayList<Group>();
        List<Note> notes = new ArrayList<Note>();
        List<Comment> comments = new ArrayList<Comment>();
        userAllArgs = new User(
                1,
                "Doe",
                "John",
                birthDate,
                "john.doe@gmail.com",
                "password",
                "01 23 45 67 89",
                new Localisation(),
                new Role(),
                new Category(),
                new UserImage(),
                groups,
                notes,
                comments
        );
    }

    @Test
    void constructorTest() {
        assertEquals(userNoArgs.getClass().getSimpleName(), "User");
        assertEquals(userNoArgs.getId(), 0);

        assertEquals(userAllArgs.getClass().getSimpleName(), "User");
        assertEquals(userAllArgs.getId(), 1);
    }

    @Test
    void setId() {
        userAllArgs.setId(8);
        assertEquals(userAllArgs.getId(), 8);
    }

    @Test
    void setName() {
        userAllArgs.setName("O'Doe");
        assertEquals(userAllArgs.getName(), "O'Doe");
    }

    @Test
    void setFirstname() {
        userAllArgs.setFirstname("Jane");
        assertEquals(userAllArgs.getFirstname(), "Jane");
    }

    @Test
    void setBirthdate() {
        LocalDate newBirthDate = LocalDate.of(
                1999,
                11,
                18
        );
        userAllArgs.setBirthdate(newBirthDate);
        assertEquals(userAllArgs.getBirthdate(), newBirthDate);
    }

    @Test
    void setEmail() {
        userAllArgs.setEmail("jane.odoe@gmail.com");
        assertEquals(userAllArgs.getEmail(), "jane.odoe@gmail.com");
    }

    @Test
    void setPassword() {
        userAllArgs.setPassword("newPassword");
        assertEquals(userAllArgs.getPassword(), "newPassword");
    }

    @Test
    void setPhone() {
        userAllArgs.setPhone("09 87 65 43 21");
        assertEquals(userAllArgs.getPhone(), "09 87 65 43 21");
    }

    @Test
    void setLocalisation() {
        Localisation newLocalisation = new Localisation();
        userAllArgs.setLocalisation(newLocalisation);
        assertEquals(userAllArgs.getLocalisation(), newLocalisation);
    }

    @Test
    void setRole() {
        Role newRole = new Role();
        userAllArgs.setRole(newRole);
        assertEquals(userAllArgs.getRole(), newRole);
    }

    @Test
    void setMood() {
        Category newMood = new Category();
        userAllArgs.setMood(newMood);
        assertEquals(userAllArgs.getMood(), newMood);
    }

    @Test
    void setImage() {
        UserImage newImage = new UserImage();
        userAllArgs.setImage(newImage);
        assertEquals(userAllArgs.getImage(), newImage);
    }

    @Test
    void addComment() {
        Comment comment = new Comment();
        userAllArgs.addComment(comment);
        assertTrue(userAllArgs.getComments().contains(comment));
    }

    @Test
    void addNote() {
        Note note = new Note();
        userAllArgs.addNote(note);
        assertTrue(userAllArgs.getNotes().contains(note));
    }

    @Test
    void addGroup() {
        Group group = new Group();
        userAllArgs.addGroup(group);
        assertTrue(userAllArgs.getGroups().contains(group));
    }
}