package com.mood.mood.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalisationDetails {

    private String housenumber; // addressNumber
    @NonNull
    private String street; // addressName
    @NonNull
    private String postcode; // postalCode
    @NonNull
    private String city; // town
    @NonNull
    private String longitude; // coordinates[1]
    @NonNull
    private String latitude; // coordinates[0]
}
