package com.mood.mood.service;

import com.mood.mood.Repository.CommentRepository;
import com.mood.mood.Repository.EstablishmentRepository;
import com.mood.mood.dto.out.CommentDetails;
import com.mood.mood.dto.out.EstablishmentDetails;
import com.mood.mood.model.Comment;
import com.mood.mood.model.Establishment;
import com.mood.mood.repository.EstablishementImageRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class EstablishmentService implements IEstablishmentService {

    @Autowired //used to tell spring this bean requires to be injected
    private EstablishementRe establishmentRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;

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
     * */
    private EstablishmentDetails convertEstablishmentEntityToDto(Establishment establishment) {
        EstablishmentDetails establishmentDetails = new EstablishmentDetails();
        establishmentDetails.setName(establishment.getName());
        establishmentDetails.setDescription(establishment.getDescription());
        establishmentDetails.setCategory(establishment.getCategory());
        establishmentDetails.setImages(establishment.getImages());

        List<Comment> comments = commentRepository.findByEstablishment(establishment);
        List<CommentDetails> commentsOfEstablishment = comments.stream()
                                                                .map(comment -> commentService.convertCommentEntityToDto(comment))
                                                                .collect(Collectors.toList());
        establishmentDetails.setComments(commentsOfEstablishment);
        return establishmentDetails;
    }

    public List<EstablishmentDetails> getEstablishmentByNameLike(String name) {
        return  establishmentRepository.findByNameLikeIgnoreCase(name)
                .stream()
                .map(this::convertEstablishmentEntityToDto)
                .collect(Collectors.toList());
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
