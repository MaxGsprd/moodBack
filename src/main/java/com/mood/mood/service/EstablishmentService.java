package com.mood.mood.service;

import com.mood.mood.Repository.EstablishementRepository;
import com.mood.mood.dto.out.EstablishmentDetails;
import com.mood.mood.model.Establishment;
import org.hibernate.QueryTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@Service
public class EstablishmentService implements IEstablishmentService {
    @Autowired
    EstablishementRepository establishementRepository;

    @Override
    public List<Establishment> getAllEstablishments() throws Exception {
        try {
            List<Establishment> establishments = establishementRepository.findAll();
            return establishments;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }



}
