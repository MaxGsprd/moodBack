package com.mood.mood.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvitationEvenementDetails {
    private LocalDate invitationDate;
    private int status;
    private EstablishmentDetails establishment;
    // UserDetails crée une boucle
    private UserDetails organizer;
    private List<UserDetails> receivers;
}
