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
    private Double longitude; // coordinates[1][y]
    @NonNull
    private Double latitude; // coordinates[0][x]
}
