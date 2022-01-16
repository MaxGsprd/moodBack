package com.mood.mood.service;

import com.mood.mood.dto.out.EstablishmentDetails;
import com.mood.mood.model.Establishment;
import org.hibernate.QueryTimeoutException;

import java.util.List;

public interface IEstablishmentService {
    List<EstablishmentDetails> getAllEstablishments() throws Exception;
    EstablishmentDetails getEstablishmentById(final int id) throws Exception;
    List<EstablishmentDetails> getEstablishmentByNameLike(String name) throws Exception;
    List<EstablishmentDetails> getAllEstablishmentsByCategoryId(int id) throws Exception;

}
