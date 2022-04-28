package com.mood.mood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    private Comment commentNoArgs;
    private Comment commentAllArgs;

    @BeforeEach
    void setUp() {
        LocalDateTime createdDate = LocalDateTime.of(2021, 06, 05, 10, 15);
        User user = new User();
        Establishment establishment = new Establishment();

        commentNoArgs = new Comment();
        commentAllArgs = new Comment(
                0,
                "commentTestTitle",
                "commentTestContent",
                createdDate,
                true,
                0,
                establishment,
                user
        );
    }

    @Test
    void constructorTest() {
        assertEquals(commentNoArgs.getClass().getSimpleName(), "Comment");
        assertEquals(commentNoArgs.getId(), 0);

        assertEquals(commentAllArgs.getClass().getSimpleName(), "Comment");
        assertEquals(commentAllArgs.getId(), 0);
    }

    @Test
    void setId() {
        commentAllArgs.setId(8);
        assertEquals(commentAllArgs.getId(), 8);
    }

    @Test
    void setTitle() {
        commentAllArgs.setTitle("titleTest");
        assertEquals(commentAllArgs.getTitle(), "titleTest");
    }

    @Test
    void setContent() {
        commentAllArgs.setContent("contentTest");
        assertEquals(commentAllArgs.getContent(), "contentTest");
    }

    @Test
    void setCreatedDate() {
        LocalDateTime createdDateTest = LocalDateTime.of(2021, 06, 05, 10, 15);
        commentAllArgs.setCreatedDate(createdDateTest);
        assertEquals(commentAllArgs.getCreatedDate(), createdDateTest);
    }

    @Test
    void setStatus() {
        commentAllArgs.setStatus(true);
        assertEquals(commentAllArgs.getStatus(), true);
    }

    @Test
    void setGroupType() {
        commentAllArgs.setGroupType(0);
        assertEquals(commentAllArgs.getGroupType(), 0);
    }

    @Test
    void setEstablishment() {
        Establishment establishmentTest = new Establishment();
        commentAllArgs.setEstablishment(establishmentTest);
        assertEquals(commentAllArgs.getEstablishment(), establishmentTest);
    }

    @Test
    void setUser() {
        User userTest = new User();
        commentAllArgs.setUser(userTest);
        assertEquals(commentAllArgs.getUser(), userTest);
    }

    @Test
    void preSave() {
        LocalDateTime now = LocalDateTime.now();
        commentAllArgs.preSave();
        assertTrue(now.isBefore(commentAllArgs.getCreatedDate()) || now.isEqual(commentAllArgs.getCreatedDate()));
    }

}