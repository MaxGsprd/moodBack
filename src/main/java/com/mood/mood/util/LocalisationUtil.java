package com.mood.mood.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mood.mood.controller.LocalisationController;
import com.mood.mood.dto.in.LocalisationForm;
import com.mood.mood.model.GeoCoordinates;
import com.mood.mood.repository.LocalisationRepository;
import com.mood.mood.service.LocalisationService;
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



    public GeoCoordinates getRegisterAddress(LocalisationForm address) throws Exception {
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
        LinkedHashMap<String, Object> result = (LinkedHashMap<String, Object>) add.get(0);

        /**
         * From object extract features to arraylist
         */
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList features = objectMapper.convertValue(result.get("features"), ArrayList.class);

        /**
         * Get properties where all information are stores and map to GeoCoordinate MODEL
         */
        LinkedHashMap<String, Object> feature = (LinkedHashMap<String, Object>) features.get(0);
        GeoCoordinates coordonate = objectMapper.convertValue(feature.get("properties"), GeoCoordinates.class);

        return (GeoCoordinates) coordonate;
    }


}
