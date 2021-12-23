package com.mood.mood.service;

import com.mood.mood.dto.out.EstablishmentDetails;
import com.mood.mood.model.Establishment;
import org.hibernate.QueryTimeoutException;

import java.util.List;

public interface IEstablishmentService {
    List<Establishment> getAllEstablishments() throws Exception;
}
