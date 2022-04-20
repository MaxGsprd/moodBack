package com.mood.mood.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mood.mood.controller.LocalisationController;
import com.mood.mood.dto.in.LocalisationForm;
import com.mood.mood.dto.out.LocalisationCoordinates;
import com.mood.mood.dto.out.LocalisationDetails;
import com.mood.mood.model.GeoCoordinates;
import com.mood.mood.model.Localisation;
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
     * From Register Controller
     *
     * @param address from request sent by user
     * @return Coordinates{Lat, Lon} fom DTO.out LocalisationCoordinates
     * @throws Exception
     */
    public LocalisationCoordinates getSearchCoordinates(LocalisationForm address) throws Exception {


        /**
         * Reponse of array list object from API
         */
        List<Object> add = localisationController.getAddressFromString(address);

        /**
         * Remove first array of response
         */
        LinkedHashMap<String, Object> result = (LinkedHashMap<String, Object>) add.get(0);

        GeoCoordinates coordonate = responseTreat(result);

        LocalisationCoordinates coordinatesFormatDtoOut = new LocalisationCoordinates();

        coordinatesFormatDtoOut.setLatitude(coordonate.getLatitude());
        coordinatesFormatDtoOut.setLongitude(coordonate.getLongitude());


        return (LocalisationCoordinates) coordinatesFormatDtoOut;
    }

    /**
     * @param response [Object] from request sent by user
     *
     *                 request geoLocalisation
     *
     * @return Coordinates{Lat, Lon} fom DTO.out LocalisationCoordinates
     * @throws Exception
     */
    public LocalisationCoordinates getSearchCoordinatesFromRequest(Object response) throws Exception {
        /**
         * Remove first array of response
         */
        LinkedHashMap<String, Object> result = (LinkedHashMap<String, Object>) response;

        GeoCoordinates coordonate = responseTreat(result);

        LocalisationCoordinates coordinatesFormatDtoOut = new LocalisationCoordinates();

        coordinatesFormatDtoOut.setLatitude(coordonate.getLatitude());
        coordinatesFormatDtoOut.setLongitude(coordonate.getLongitude());


        return (LocalisationCoordinates) coordinatesFormatDtoOut;
    }


    /**
     *
     * @param response request send {lat, log}
     * @return
     */
    public LocalisationDetails getReverseCoordinate(Object response) throws InterruptedException{

        GeoCoordinates addressFormatDtoOut = responseTreat(response);

        LocalisationDetails responseOut = new LocalisationDetails();
        responseOut.setHousenumber(addressFormatDtoOut.getHousenumber());
        responseOut.setStreet(addressFormatDtoOut.getName());
        responseOut.setPostcode(addressFormatDtoOut.getPostcode());
        responseOut.setCity(addressFormatDtoOut.getCity());
        responseOut.setLatitude(addressFormatDtoOut.getLatitude());
        responseOut.setLongitude(addressFormatDtoOut.getLongitude());

        return responseOut;

    }

    /**
     * Treat object from response api {URL="https://adresse.data.gouv.fr/"}
     * @param response Objet receiver
     * @return GeoCoordinates : MODEL
     */
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

    public String formatToSendRequest(LocalisationForm address){
        String addressNum = address.getAddressNumber();
        String addressName = address.getAddressName().replace(" ","+"); // replace all blanc space by '+'
        String addressPostal = address.getPostalCode();

        /**
         * format of string to send request
         */
        return addressNum+"+"+addressName+"&postcode="+addressPostal;
    }


}
