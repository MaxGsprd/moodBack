package com.mood.mood.dto.out;

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
    private List<UserDetails> users;

    private List<InvitationDetails> invitations;
}
