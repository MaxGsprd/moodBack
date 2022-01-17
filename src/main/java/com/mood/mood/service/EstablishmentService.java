package com.mood.mood.service;

import com.mood.mood.repository.CommentRepository;
import com.mood.mood.repository.EstablishmentRepository;
import com.mood.mood.dto.in.EstablishmentForm;
import com.mood.mood.dto.out.EstablishmentDetails;
import com.mood.mood.model.Establishment;
import lombok.Data;
import org.hibernate.QueryTimeoutException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class EstablishmentService implements IEstablishmentService {

    @Autowired //used to tell spring this bean requires to be injected
    private EstablishmentRepository establishmentRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ModelMapper modelMapper;

    public EstablishmentDetails getEstablishmentById(final int id) {
        Establishment establishment =  establishmentRepository.findById(id);
        EstablishmentDetails establishmentDetails = convertEstablishmentEntityToDto(establishment);
       return establishmentDetails;
    }

    public List<EstablishmentDetails> getAllEstablishmentsByCategoryId(final int id) {
        return establishmentRepository.findByCategoryId(id)
                .stream()
                .map(this::convertEstablishmentEntityToDto)
                .collect(Collectors.toList());
    }

    public List<EstablishmentDetails> getAllEstablishments(){
        return establishmentRepository.findAll()
                .stream()
                .map(this::convertEstablishmentEntityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Convert Establishment JPA entity to establishmentDetails (DTO out)
     */
    private EstablishmentDetails convertEstablishmentEntityToDto(Establishment establishment) {
        return modelMapper.map(establishment, EstablishmentDetails.class);
    }

    /**
     * Convert EstablishmentForm Dto in to establishment entity
     */
    private Establishment dtoToEntity(EstablishmentForm establishmentForm) {
        return modelMapper.map(establishmentForm, Establishment.class);
    }

    /**
     * Find establishment by name with Like query
     */
    public List<EstablishmentDetails> getEstablishmentByNameLike(String name) {
        return  establishmentRepository.findByNameLikeIgnoreCase(name)
                .stream()
                .map(this::convertEstablishmentEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Establishment createEstablishment(EstablishmentForm establishmentForm) throws QueryTimeoutException {
        Establishment createdEstablishment = dtoToEntity(establishmentForm);
        establishmentRepository.save(createdEstablishment);
        return createdEstablishment;
    }



//    public void deleteEstablishment(final Long id) {
//        establishementRepository.deleteById(id);
//    }
//
//    public Establishment createEstablishment(Establishment establishment) {
//        Establishment createdEstablishment = establishementRepository.save(establishment);
//        return createdEstablishment;
//    }
}
