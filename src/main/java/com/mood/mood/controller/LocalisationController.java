package com.mood.mood.controller;

import com.mood.mood.dto.in.LocalisationForm;
import com.mood.mood.dto.out.LocalisationCoordinates;
import com.mood.mood.dto.out.LocalisationDetails;
import com.mood.mood.model.Image;
import com.mood.mood.service.LocalisationService;
import com.mood.mood.util.LocalisationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/localisation")
public class LocalisationController {
    private static Logger LOGGER = Logger.getLogger(invitationController.class.getName());

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
        LOGGER.log(Level.INFO, "**START** - Get Address from external API by full address code");
        try {
            String full_address = localisationUtil.formatToSendRequest(address);

            Object result = restTemplate.getForObject(BASE_URI_STRING+"/search/?q="+full_address+"&limit=5", Object.class);
            //assert result != null;
            return Arrays.asList(result) ;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return (List<Object>) ResponseEntity.status(HttpStatus.BAD_REQUEST).body("**ERROR** -- Couldn't get objet from external API");
        }
    }

    @GetMapping("/getAddressFromRequest")
    public LocalisationCoordinates getAddressFromRequest(@RequestBody LocalisationForm address)  throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Address from external API from localisation form address");
        try {
            String full_address = localisationUtil.formatToSendRequest(address);

            Object result = restTemplate.getForObject(BASE_URI_STRING+"/search/?q="+full_address+"&limit=5", Object.class);
            //assert result != null;
            LocalisationCoordinates coordonates = localisationUtil.getSearchCoordinatesFromRequest(result);
            return coordonates ;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR** -- Couldn't get objet from external API from localisation form" + ex.getMessage(), ex);
            return null;
        }
    }


    @GetMapping("/getAddressFromLatLon")
    public LocalisationDetails getAddressFromLatLon(@RequestParam String latitude, @RequestParam String longitude)
            throws Exception{
        LOGGER.log(Level.INFO, "**START** - Get Address from external API from lat long");
        try {
            Object result = restTemplate.getForObject(BASE_URI_STRING + "/reverse/?lon=" + longitude + "&lat=" + latitude + "&type=street", Object.class);

            LocalisationDetails address = localisationUtil.getReverseCoordinate(result);

            return (LocalisationDetails) address;
        }catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR** -- Couldn't get objet from external API from lat long" + ex.getMessage(), ex);
            return null;
        }
    }


}

