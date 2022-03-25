package com.mood.mood.service;

import com.mood.mood.model.Localisation;
import com.mood.mood.repository.LocalisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocalisationService {

    @Autowired
    private static LocalisationRepository localisationRepository;

    public static Optional<Localisation> getLocationByLatitudeAndLongitude(Double latitude, Double longitude) {
        return localisationRepository.findByLatitudeAndLongitude(latitude, longitude);
    }

    public List<Localisation> getAllLocalisation() {
        return localisationRepository.findAll();
    }

    public Long saveLocalisation(Localisation Localisation) {
        localisationRepository.save(Localisation);
        return 0L;
    }

}
