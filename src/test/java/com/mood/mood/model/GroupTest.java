package com.mood.mood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    private Group groupNoArgs;
    private Group groupsAllArgs;

    @BeforeEach
    void setUp() {
        LocalDateTime createdDate = LocalDateTime.of(2021, 06, 05, 10, 15);
        LocalDateTime updatedDate = LocalDateTime.of(2021, 11, 8, 10, 15);
        List<User> users = new ArrayList<User>();
        groupNoArgs = new Group();
        groupsAllArgs = new Group(
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

        assertEquals(groupsAllArgs.getClass().getSimpleName(), "Group");
        assertEquals(groupsAllArgs.getId(), 0);
    }

    @Test
    void setId() {
        groupsAllArgs.setId(0);
        assertEquals(groupNoArgs.getId(), 0);
    }

    @Test
    void setTitle() {
        groupsAllArgs.setTitle("groupTitleTest");
        assertEquals(groupsAllArgs.getTitle(),"groupTitleTest");
    }

    @Test
    void setCreatedDate() {
        LocalDateTime createdDateTest = LocalDateTime.of(2021, 06, 05, 10, 15);
        groupsAllArgs.setCreatedDate(createdDateTest);
        assertEquals(groupsAllArgs.getCreatedDate(), createdDateTest);
    }

    @Test
    void setUpdatedDate() {
        LocalDateTime updatedDateTest = LocalDateTime.of(2021, 11, 8, 10, 15);
        groupsAllArgs.setUpdatedDate(updatedDateTest);
        assertEquals(groupsAllArgs.getUpdatedDate(), updatedDateTest);
    }

    @Test
    void addUser() {
        User userTest = new User();
        groupsAllArgs.addUser(userTest);
        assertTrue(groupsAllArgs.getUsers().contains(userTest));
    }
}