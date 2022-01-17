package com.mood.mood.service;

import com.mood.mood.model.Category;
import com.mood.mood.repository.CategoryRepository;
import com.mood.mood.repository.CommentRepository;
import com.mood.mood.repository.EstablishmentRepository;
import com.mood.mood.dto.in.EstablishmentForm;
import com.mood.mood.dto.out.EstablishmentDetails;
import com.mood.mood.model.Establishment;
import lombok.Data;
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
    CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    public EstablishmentDetails getEstablishmentById(final int id) {
        Establishment establishment =  establishmentRepository.findById(id);
        return convertEstablishmentEntityToDto(establishment);
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
     * Convert Establishment entity to establishmentDetails (DTO out)
     */
    private EstablishmentDetails convertEstablishmentEntityToDto(Establishment establishment) {
        return modelMapper.map(establishment, EstablishmentDetails.class);
    }

    /**
     * Convert EstablishmentForm (Dto in) to establishment entity
     */
    private Establishment establishmentDtoToEntity(EstablishmentForm establishmentForm) {
        Establishment establishment = new Establishment();
        establishment.setName(establishmentForm.getName());
        establishment.setDescription(establishmentForm.getDescription());
        Category category = categoryRepository.findById(establishmentForm.getCategory());
        establishment.setCategory(category);
        establishment.setStatus(false);
        return establishment;
    }

    /**
     * Find establishment by name with Like query
     */
    public List<EstablishmentDetails> getEstablishmentByNameLike(String name) throws Exception {
        try {
            return  establishmentRepository.findByNameLikeIgnoreCase(name)
                    .stream()
                    .map(this::convertEstablishmentEntityToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public Establishment createEstablishment(EstablishmentForm establishmentForm) throws Exception {
        try {
            Establishment createdEstablishment = establishmentDtoToEntity(establishmentForm);
            establishmentRepository.save(createdEstablishment);
            return createdEstablishment;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
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
