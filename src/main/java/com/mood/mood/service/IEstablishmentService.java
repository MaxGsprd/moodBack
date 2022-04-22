package com.mood.mood.service;

import com.mood.mood.dto.in.EstablishmentForm;
import com.mood.mood.dto.out.EstablishmentDetails;
import com.mood.mood.model.Establishment;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface IEstablishmentService {
    List<EstablishmentDetails> getAllEstablishments() throws Exception;
    List<EstablishmentDetails> getAllEstablishmentsByNotesAverages() throws Exception;
    EstablishmentDetails getEstablishmentById(final int id) throws Exception;
    List<EstablishmentDetails> getEstablishmentByNameLike(String name) throws Exception;
    List<EstablishmentDetails> getAllEstablishmentsByCategoryId(int id) throws Exception;
    Establishment createEstablishment(@ModelAttribute EstablishmentForm establishmentForm) throws Exception;
    void deleteEstablishmentById(int id) throws Exception;
    EstablishmentDetails updateEstablishment(final int id, EstablishmentForm establishmentForm) throws Exception;
    List<EstablishmentDetails> getEstablishmentsByStatus(Boolean status) throws Exception;

    List<Establishment> getEstablishmentWithInDisatance(double lat, double lon, double km) throws Exception;

    List<Establishment> getEstablishmentWithLocalisation() throws Exception;
}
