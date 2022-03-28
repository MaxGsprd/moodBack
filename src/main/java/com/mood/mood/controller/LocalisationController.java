package com.mood.mood.controller;

import com.mood.mood.dto.in.LocalisationForm;
import com.mood.mood.dto.out.LocalisationCoordinates;
import com.mood.mood.dto.out.LocalisationDetails;
import com.mood.mood.service.LocalisationService;
import com.mood.mood.util.LocalisationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/localisation")
public class LocalisationController {

    private static final String BASE_URI_STRING = "https://api-adresse.data.gouv.fr/";
    private static final String GET_REQUEST = "GET";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LocalisationService localisationService;

    @Autowired
    private LocalisationUtil localisationUtil;

    @GetMapping("/getAddressFromString")
    public List<Object> getAddressFromString(@RequestBody LocalisationForm address)  throws Exception {
        try {
            String full_address = localisationUtil.formatToSendRequest(address);

            Object result = restTemplate.getForObject(BASE_URI_STRING+"/search/?q="+full_address+"&limit=5", Object.class);
            //assert result != null;
            return Arrays.asList(result) ;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

    @GetMapping("/getAddressFromRequest")
    public LocalisationCoordinates getAddressFromRequest(@RequestBody LocalisationForm address)  throws Exception {
        try {
            String full_address = localisationUtil.formatToSendRequest(address);

            Object result = restTemplate.getForObject(BASE_URI_STRING+"/search/?q="+full_address+"&limit=5", Object.class);
            //assert result != null;
            LocalisationCoordinates coordonates = localisationUtil.getSearchCoordinatesFromRequest(result);
            return coordonates ;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }


    @GetMapping("/getAddressFromLatLon")
    public LocalisationDetails getAddressFromLatLon(@RequestParam String latitude, @RequestParam String longitude)
            throws Exception{;
        Object result = restTemplate.getForObject(BASE_URI_STRING+"/reverse/?lon="+longitude+"&lat="+latitude+"&type=street", Object.class);

        LocalisationDetails address = localisationUtil.getReverseCoordinate(result);

        return (LocalisationDetails) address;
    }


}

