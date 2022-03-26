package com.mood.mood.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoCoordinates implements Serializable {
    private String id;
    private String label;
    private Double score;
    private String housenumber;
    private String type;
    private String name;
    private String postcode;
    private String citycode;
    private double x;
    private double y;
    private String city;
    private String context;
    private Double importance;
    private String street;
    private String district;
    private String oldcitycode;
    private String oldcity;
    private int distance;

    private Double longitude;
    private Double latitude;
}
