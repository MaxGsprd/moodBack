package com.mood.mood.service;

import com.mood.mood.dto.in.EstablishmentForm;
import com.mood.mood.dto.out.EstablishmentDetails;
import com.mood.mood.model.Establishment;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface IEstablishmentService {
    List<EstablishmentDetails> getAllEstablishments() throws Exception;
    EstablishmentDetails getEstablishmentById(final int id) throws Exception;
    List<EstablishmentDetails> getEstablishmentByNameLike(String name) throws Exception;
    List<EstablishmentDetails> getAllEstablishmentsByCategoryId(int id) throws Exception;
    Establishment createEstablishment(@ModelAttribute EstablishmentForm establishmentForm) throws Exception;
//    EstablishmentDetails convertEstablishmentEntityToDto(Establishment establishment);
//    Establishment establishmentDtoToEntity(EstablishmentForm establishmentForm);
}
