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

    private String street; // addressName
    @NonNull
    private String postcode; // postalCode
    @NonNull
    private String city; // town

    private Double longitude; // coordinates[1]
    private Double latitude; // coordinates[0]
}
