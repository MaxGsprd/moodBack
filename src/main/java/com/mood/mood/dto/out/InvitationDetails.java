package com.mood.mood.dto.out;

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
    private UserDetails organizer;
    private List<UserDetails> receivers;
}
