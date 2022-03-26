package com.mood.mood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InvitationEvenementTest {

    private InvitationEvenement invitationNoArgs;
    private InvitationEvenement invitationRequiredArgs;

    @BeforeEach
    void setUp() {
        invitationNoArgs = new InvitationEvenement();
        invitationRequiredArgs = new InvitationEvenement(
                new User(),
                new Group(),
                new User(),
                LocalDate.now(),
                new Establishment(),
                0

        );
    }

    @Test
    void constructorTest() {
        assertEquals(invitationNoArgs.getClass().getSimpleName(), "InvitationEvenement");
        assertEquals(invitationNoArgs.getId(), 0);

        assertEquals(invitationRequiredArgs.getClass().getSimpleName(), "InvitationEvenement");
        assertEquals(invitationRequiredArgs.getId(), 0);
    }

    @Test
    void setInvitationDate() {
        LocalDate invitationDate = LocalDate.now();
        invitationRequiredArgs.setInvitationDate(invitationDate);
        assertEquals(invitationRequiredArgs.getInvitationDate(), invitationDate);
    }

    @Test
    void setEstablishment() {
        Establishment establishment = new Establishment();
        invitationRequiredArgs.setEstablishment(establishment);
        assertEquals(invitationRequiredArgs.getEstablishment(), establishment);
    }

    @Test
    void setStatus() {
        invitationRequiredArgs.setStatus(1);
        assertEquals(invitationRequiredArgs.getStatus(), 1);
    }

}