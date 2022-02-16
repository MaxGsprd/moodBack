package com.mood.mood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    private Group groupNoArgs;
    private Group groupAllArgs;

    @BeforeEach
    void setUp() {
        LocalDateTime createdDate = LocalDateTime.of(2021, 06, 05, 10, 15);
        LocalDateTime updatedDate = LocalDateTime.of(2021, 11, 8, 10, 15);
        List<User> users = new ArrayList<User>();
        groupNoArgs = new Group();
        groupAllArgs = new Group(
                0,
                "groupTitle",
                createdDate,
                updatedDate,
                users
        );
    }

    @Test
    void constructorTest() {
        assertEquals(groupNoArgs.getClass().getSimpleName(), "Group");
        assertEquals(groupNoArgs.getId(), 0);

        assertEquals(groupAllArgs.getClass().getSimpleName(), "Group");
        assertEquals(groupAllArgs.getId(), 0);
    }

    @Test
    void setId() {
        groupAllArgs.setId(0);
        assertEquals(groupNoArgs.getId(), 0);
    }

    @Test
    void setTitle() {
        groupAllArgs.setTitle("groupTitleTest");
        assertEquals(groupAllArgs.getTitle(),"groupTitleTest");
    }

    @Test
    void setCreatedDate() {
        LocalDateTime createdDateTest = LocalDateTime.of(2021, 06, 05, 10, 15);
        groupAllArgs.setCreatedDate(createdDateTest);
        assertEquals(groupAllArgs.getCreatedDate(), createdDateTest);
    }

    @Test
    void setUpdatedDate() {
        LocalDateTime updatedDateTest = LocalDateTime.of(2021, 11, 8, 10, 15);
        groupAllArgs.setUpdatedDate(updatedDateTest);
        assertEquals(groupAllArgs.getUpdatedDate(), updatedDateTest);
    }

    @Test
    void addUser() {
        User userTest = new User();
        groupAllArgs.addUser(userTest);
        assertTrue(groupAllArgs.getUsers().contains(userTest));
    }

    @Test
    void removeUser() {
        User userOne = new User();
        userOne.setFirstname("John");
        userOne.setName("Doe");

        User userTwo = new User();
        userTwo.setFirstname("Jane");
        userTwo.setName("Doe");

        groupAllArgs.addUser(userOne);
        groupAllArgs.addUser(userTwo);

        groupAllArgs.removeUser(userTwo);

        assertTrue(groupAllArgs.getUsers().contains(userOne));
        assertFalse(groupAllArgs.getUsers().contains(userTwo));
    }
}