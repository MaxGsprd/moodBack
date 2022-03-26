package com.mood.mood.controller;

import com.mood.mood.dto.out.LocalisationDetails;
import com.mood.mood.model.GeoCoordinates;
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

    @PostMapping("/test")
    public ResponseEntity<String> localisationTest() throws Exception {//@RequestBody
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Found Link test");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }


    @GetMapping("/getAddressFromString}")
    public List<Object> getAddressFromString(String full_address)  throws Exception {
        try {
            Object result = restTemplate.getForObject("https://api-adresse.data.gouv.fr/search/?q="+full_address+"&limit=5", Object.class);
            //assert result != null;
            return Arrays.asList(result);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

    @GetMapping("/getAddressFromLatLon")
    public LocalisationDetails getAddressFromLatLon(@RequestParam String latitude, @RequestParam String longitude)
            throws Exception{;
        Object result = restTemplate.getForObject("https://api-adresse.data.gouv.fr/reverse/?lon="+longitude+"&lat="+latitude+"&type=street", Object.class);

        LocalisationDetails address = localisationUtil.getReverseCoordinate(result);

        return (LocalisationDetails) address;
    }


}

