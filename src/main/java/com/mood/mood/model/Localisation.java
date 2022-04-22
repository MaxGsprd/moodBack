package com.mood.mood.model;

import com.mood.mood.dto.in.LocalisationForm;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Localisation implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @NonNull
    @Column(nullable = false)
    private Double longitude; // first parameters or [0]

    @NonNull
    @Column(nullable = false)
    private Double latitude; //second parameters or [1]



    public Localisation(@NonNull Double longitude, @NonNull Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

}
