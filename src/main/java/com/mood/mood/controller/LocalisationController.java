package com.mood.mood.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mood.mood.model.Localisation;
import com.mood.mood.service.LocalisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
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

    @GetMapping("/getAddressFromLatLon/{latitude:.+},{longitude:.+}")
    public List<Object> getAddressFromLatLon(@PathVariable Double latitude, @PathVariable Double longitude)
            throws Exception{
        Optional<Localisation> localisation = LocalisationService.getLocationByLatitudeAndLongitude(latitude, longitude);
        RestTemplate restTemplate = new RestTemplate();
        Object[] result = restTemplate.getForObject(BASE_URI_STRING+"reverse/?lon="+longitude+"&lat="+latitude+"&type=street", Object[].class);

        return Arrays.asList(result);
    }

}

