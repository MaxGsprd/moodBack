package com.mood.mood.service;

import com.mood.mood.Repository.EstablishmentRepository;
import com.mood.mood.dto.out.EstablishmentDetails;
import com.mood.mood.model.Establishment;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class EstablishmentService implements IEstablishmentService {

    @Autowired //used to tell spring this bean require to be injected
    private EstablishmentRepository establishmentRepository;

//    public Establishment getEstablishment(final int id) {
//        return establishementRepository.findById(id);
//    }

//    @Override
//    public List<Establishment> getAllEstablishments() throws Exception {
//        try {
//            List<Establishment> establishments = establishementRepository.findAll();
//            return establishments;
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    }
    @Override
    public List<EstablishmentDetails> getAllEstablishments(){
        return establishmentRepository.findAll()
                .stream()
                .map(this::convertEstablishmentEntityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Convert Establishment JPA entity to establishmentDetails (DTO out)
     * */
    private EstablishmentDetails convertEstablishmentEntityToDto(Establishment establishment) {
        EstablishmentDetails establishmentDetails = new EstablishmentDetails();
        establishmentDetails.setName(establishment.getName());
        establishmentDetails.setDescription(establishment.getDescription());
        return establishmentDetails;
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
