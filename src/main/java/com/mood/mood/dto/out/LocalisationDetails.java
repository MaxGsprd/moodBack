package com.mood.mood.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalisationDetails {

    private String addressNumber;
    @NonNull
    private String addressName;
    @NonNull
    private String postalCode;
    @NonNull
    private String ciy;
    @NonNull
    private String longitude;
    @NonNull
    private String latitude;
}
