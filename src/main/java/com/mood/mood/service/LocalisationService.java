package com.mood.mood.service;

import com.mood.mood.repository.LocalisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalisationService {

    @Autowired
    private static LocalisationRepository localisationRepository;



}
