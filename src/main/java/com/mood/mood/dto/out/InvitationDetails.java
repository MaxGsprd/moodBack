package com.mood.mood.dto.out;

import com.mood.mood.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvitationDetails {
    private String invitationDate;
    private int status;
    private EstablishmentDetails establishment;
    // UserDetails crée une boucle
    private User organizer;
    private List<User> receivers;
}