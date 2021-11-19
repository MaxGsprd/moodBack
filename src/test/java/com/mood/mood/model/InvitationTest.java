package com.mood.mood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class InvitationTest {

    private Invitation invitationNoArgs;
    private Invitation invitationAllArgs;

    @BeforeEach
    void setUp() {
        invitationNoArgs = new Invitation();
        LocalDateTime today = LocalDateTime.now();
        invitationAllArgs = new Invitation(
                1,
                today.plusHours(2),
                0,
                new Establishment(),
                new User(),
                new Group()
        );
    }

    @Test
    void constructorTest() {
        assertEquals(invitationNoArgs.getClass().getSimpleName(), "Invitation");
        assertEquals(invitationNoArgs.getId(), 0);

        assertEquals(invitationAllArgs.getClass().getSimpleName(), "Invitation");
        assertEquals(invitationAllArgs.getId(), 1);
    }

    @Test
    void setId() {
        invitationAllArgs.setId(8);
        assertEquals(invitationAllArgs.getId(), 8);
    }

    @Test
    void setInvitationDate() {
        LocalDateTime invitaionDate = LocalDateTime.now().plusHours(3);
        invitationAllArgs.setInvitationDate(invitaionDate);
        assertEquals(invitationAllArgs.getInvitationDate(), invitaionDate);
    }

    @Test
    void setStatus() {
        invitationAllArgs.setStatus(2);
        assertEquals(invitationAllArgs.getStatus(), 2);
    }

    @Test
    void setEstablishment() {
        Establishment newEstablishment = new Establishment();
        invitationAllArgs.setEstablishment(newEstablishment);
        assertEquals(invitationAllArgs.getEstablishment(), newEstablishment);
    }

    @Test
    void setOrganizer() {
        User organizer = new User();
        invitationAllArgs.setOrganizer(organizer);
        assertEquals(invitationAllArgs.getOrganizer(), organizer);
    }

    @Test
    void setGroup() {
        Group newGroup = new Group();
        invitationAllArgs.setGroup(newGroup);
        assertEquals(invitationAllArgs.getGroup(), newGroup);
    }
}