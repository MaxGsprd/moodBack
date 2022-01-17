package com.mood.mood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@RestController
@RequestMapping("/localisation")
public class LocalisationController {

    private static final String url = "https://api-adresse.data.gouv.fr/";

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/getAddressFromString")
    public List<Object> getAddressFromString(String address,String cp)  throws Exception {
        try {
            Object result = restTemplate.getForObject(url+"search/?q="+address+"&postcode"+cp+"&limit=15", Object.class);
            return Arrays.asList(result);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

    @GetMapping("/getAddressFromLatLon/{address}")
    public List<Object> getAddressFromLatLon() {
        RestTemplate restTemplate = new RestTemplate();
        Object[] result = restTemplate.getForObject(url+"reverse/?lon=2.37&lat=48.357&type=street", Object[].class);

        return Arrays.asList(result);
    }
}

