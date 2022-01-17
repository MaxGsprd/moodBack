package com.mood.mood.dto.out;

import com.mood.mood.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDetails {
    @NonNull
    private String title;
    @NonNull
    // List<UserDetails> crée une boucle
    private List<User> users;

    private List<InvitationDetails> invitations;
}
