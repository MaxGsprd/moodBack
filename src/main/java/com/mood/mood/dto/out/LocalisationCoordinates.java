package com.mood.mood.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalisationCoordinates {

    @NonNull
    private Double longitude; // first parameters or [0]
    @NonNull
    private Double latitude; //second parameters or [1]
}
