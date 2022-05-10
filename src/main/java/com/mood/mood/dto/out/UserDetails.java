package com.mood.mood.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String firstname;
    @NonNull
    private String birthDate;
    @NonNull
    private String email;
    @NonNull
    private String phone;
    @NonNull
    private String role;
    @NonNull
    private LocalisationDetails address;
    @NonNull
    private int imageID;
    @NonNull
    private int category;
    @NonNull
    private List<GroupDetails> group;
}
