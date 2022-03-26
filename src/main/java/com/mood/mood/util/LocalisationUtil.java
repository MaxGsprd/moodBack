package com.mood.mood.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mood.mood.controller.LocalisationController;
import com.mood.mood.dto.in.LocalisationForm;
import com.mood.mood.dto.out.LocalisationDetails;
import com.mood.mood.model.GeoCoordinates;
import com.mood.mood.repository.LocalisationRepository;
import com.mood.mood.service.LocalisationService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class LocalisationUtil {

    @Autowired
    private LocalisationController localisationController;

    @Autowired
    private LocalisationRepository repository;

    @Autowired
    private LocalisationService localisationService;

    /**
     * traitement des informations de localisation avant insertion ou extraction
     * @return
     */
        // récupération de l'adresse envoyé

        // envoyé un requête a localisation controller

        // renvoyé la latitude et la longitude a un autre service



    public GeoCoordinates getSearchCoordinates(LocalisationForm address) throws Exception {
        String addressNum = address.getAddressNumber();
        String addressName = address.getAddressName().replace(" ","+"); // replace all blanc space by '+'
        String addressPostal = address.getPostalCode();

        /**
         * format of string to send request
         */
        String full_address = addressNum+"+"+addressName+"&postcode="+addressPostal;

        /**
         * Reponse of array list object from API
         */
        List<Object> add =  localisationController.getAddressFromString(full_address);

        /**
         * Remove first array of response
         */
        LinkedHashMap<String, Object> result = (LinkedHashMap<String, Object>) add.get(0);

        GeoCoordinates coordonate = responseTreat(result);

        return (GeoCoordinates) coordonate;
    }

    public LocalisationDetails getReverseCoordinate(Object response){


        GeoCoordinates coordonate = responseTreat(response);

        LocalisationDetails responseOut = new LocalisationDetails();
        responseOut.setHousenumber(coordonate.getHousenumber());
        responseOut.setStreet(coordonate.getName());
        responseOut.setPostcode(coordonate.getPostcode());
        responseOut.setCity(coordonate.getCity());
        responseOut.setLatitude(coordonate.getLatitude());
        responseOut.setLongitude(coordonate.getLongitude());

        System.out.println(coordonate);

        return responseOut;
    }

    //private Object responseTreat(Object response, String type, Class typeClass ){
    @NotNull
    private GeoCoordinates responseTreat(Object response){

        LinkedHashMap<String, Object> result = (LinkedHashMap<String, Object>) response;

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList array = objectMapper.convertValue(result.get("features"), ArrayList.class);

        /**
         * Get properties where all information are stores and map to GeoCoordinate MODEL
         */
        LinkedHashMap<String, Object> feature = (LinkedHashMap<String, Object>) array.get(0);

        /**
         * Coordinate Latitude et longitude
         */
        Object geometry = objectMapper.convertValue(feature.get("geometry"), Object.class);
        LinkedHashMap<String, Object> geo = (LinkedHashMap<String, Object>) geometry;

        ArrayList coordinates = objectMapper.convertValue(geo.get("coordinates"), ArrayList.class);

        /**
         * Set information to GeoCoordinate Class
         */
        GeoCoordinates addressProperties = objectMapper.convertValue(feature.get("properties"), GeoCoordinates.class);

        addressProperties.setLongitude((Double) coordinates.get(0));
        addressProperties.setLatitude((Double) coordinates.get(1));

        return addressProperties;

    }


}
