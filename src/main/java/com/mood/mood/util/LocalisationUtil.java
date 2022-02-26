package com.mood.mood.util;

import com.mood.mood.controller.LocalisationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocalisationUtil {

    @Autowired
    private LocalisationController localisationController;

    /**
     * traitement des informations de localisation avant insertion ou extraction
     * @return
     */
        // récupération de l'adresse envoyé

        // envoyé un requête a localisation controller

        // renvoyé la latitude et la longitude


    public List<Object> getRegisterAddress(String addressNum, String address, String cp) throws Exception {
        String full_address = addressNum+"+"+address.replace(" ", "+");
        List<Object> add =  localisationController.getAddressFromString(full_address, cp);

        return add;
    }
}
