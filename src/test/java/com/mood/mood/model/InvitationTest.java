package com.mood.mood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class InvitationTest {

    private Invitation invitationNoArgs;
    private Invitation invitationRequiredArgs;

    @BeforeEach
    void setUp() {
        invitationNoArgs = new Invitation();
        invitationRequiredArgs = new Invitation(
                new User(),
                new Group(),
                new User(),
                1
        );
    }

    @Test
    void constructorTest() {
        assertEquals(invitationNoArgs.getClass().getSimpleName(), "Invitation");
        assertEquals(invitationNoArgs.getId(), 0);

        assertEquals(invitationRequiredArgs.getClass().getSimpleName(), "Invitation");
        assertEquals(invitationRequiredArgs.getId(), 0);
    }

    @Test
    void setId() {
        invitationRequiredArgs.setId(8);
        assertEquals(invitationRequiredArgs.getId(), 8);
    }

    @Test
    void setOrganizer() {
        User organizer = new User();
        invitationRequiredArgs.setOrganizer(organizer);
        assertEquals(invitationRequiredArgs.getOrganizer(), organizer);
    }

    @Test
    void setGroup() {
        Group newGroup = new Group();
        invitationRequiredArgs.setGroup(newGroup);
        assertEquals(invitationRequiredArgs.getGroup(), newGroup);
    }

    @Test
    void setReveiver() {
        User receiver = new User();
        invitationRequiredArgs.setReceiver(receiver);
        assertEquals(invitationRequiredArgs.getReceiver(), receiver);
    }

    @Test
    void setStatus() {
        invitationRequiredArgs.setStatus(2);
        assertEquals(invitationRequiredArgs.getStatus(), 2);
    }
}