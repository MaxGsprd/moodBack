package com.mood.mood.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Localisation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid", strategy = "uuid2")
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
