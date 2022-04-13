package com.mood.mood.service;

import com.mood.mood.dto.out.CommentDetails;
import com.mood.mood.model.*;
import com.mood.mood.repository.CategoryRepository;
import com.mood.mood.repository.CommentRepository;
import com.mood.mood.repository.EstablishmentRepository;
import com.mood.mood.dto.in.EstablishmentForm;
import com.mood.mood.dto.out.EstablishmentDetails;
import com.mood.mood.repository.NoteRepository;
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
    private final EstablishmentRepository establishmentRepository;
    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final CommentService commentService;
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final NoteRepository noteRepository;
    @Autowired
    private final NoteService noteService;
    @Autowired
    private final ModelMapper modelMapper;

    public EstablishmentDetails getEstablishmentById(final int id) {
        Establishment establishment = establishmentRepository.findById(id);
        return convertEstablishmentEntityToDto(establishment);
    }

    public List<EstablishmentDetails> getAllEstablishmentsByCategoryId(final int id) {
        return establishmentRepository.findByCategoryId(id)
                .stream()
                .map(this::convertEstablishmentEntityToDto)
                .collect(Collectors.toList());
    }

    public List<EstablishmentDetails> getAllEstablishments() {
        return establishmentRepository.findAll()
                .stream()
                .map(this::convertEstablishmentEntityToDto)
                .collect(Collectors.toList());
    }


    /**
     * Find establishment by name with Like query
     */
    public List<EstablishmentDetails> getEstablishmentByNameLike(String name) throws Exception {
        try {
            return establishmentRepository.findByNameLikeIgnoreCase(name)
                    .stream()
                    .map(this::convertEstablishmentEntityToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    /**
     * @param establishmentForm establishment DTO in
     * @return true if success, false otherwise
     */
    public Establishment createEstablishment(EstablishmentForm establishmentForm) throws Exception {
        try {
            Establishment createdEstablishment = establishmentDtoToEntity(establishmentForm);
            establishmentRepository.save(createdEstablishment);
            return createdEstablishment;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    /**
     * @param id establishment id to be deleted
     */
    public void deleteEstablishmentById(int id) throws Exception {
        try {
            Establishment establishment = establishmentRepository.findById(id);
            establishmentRepository.deleteById(establishment.getId());
        } catch (Exception e) {
            throw new Exception("Error : This establishment couldn't be found, " + e.getMessage(), e.getCause());
        }
    }

    /**
     * @param id estblishment id to be updated
     */
    public EstablishmentDetails updateEstablishment(int id, EstablishmentForm establishmentForm) throws Exception {
        try {
            Establishment establishment = establishmentRepository.findById(id);
            establishment.setName(establishmentForm.getName());
            establishment.setDescription(establishmentForm.getDescription());
            int categoryId = establishmentForm.getCategory();
            Category category = categoryRepository.findById(categoryId).orElse(null);
            assert category != null;
            establishment.setCategory(category);
            establishmentRepository.save(establishment);
            return convertEstablishmentEntityToDto(establishment);
        } catch (Exception e) {
            throw new Exception("Error : This establishment couldn't be found, " + e.getMessage(), e.getCause());
        }
    }

    /**
     * @param status
     * @return list of EstablishmentDetails
     * @throws Exception
     */
    public List<EstablishmentDetails> getEstablishmentsByStatus(Boolean status) throws Exception {
        return establishmentRepository.findByStatus(status)
                                        .stream()
                                        .map(this::convertEstablishmentEntityToDto)
                                        .collect(Collectors.toList());
    }


    /**
     * Convert Establishment entity to establishmentDetails (DTO out)
     */
    private EstablishmentDetails convertEstablishmentEntityToDto(Establishment establishment) {
        EstablishmentDetails establishmentDetails = new EstablishmentDetails();
        establishmentDetails.setName(establishment.getName());
        establishmentDetails.setDescription(establishment.getDescription());
        List<Note> notes = establishment.getNotes();
        establishmentDetails.setNote(noteService.notesAverage(notes));
        List<Comment> comments = establishment.getComments();
        List<CommentDetails> commentDetails = comments.stream().map(comment ->commentService.convertCommentEntityToDto(comment)).collect(Collectors.toList());
        establishmentDetails.setComments(commentDetails);
        establishmentDetails.setCategory(establishment.getCategory());
        return establishmentDetails;
    }

    /**
     * Convert EstablishmentForm (Dto in) to establishment entity
     */
    private Establishment establishmentDtoToEntity(EstablishmentForm establishmentForm) {
        Establishment establishment = new Establishment();
        establishment.setName(establishmentForm.getName());
        establishment.setDescription(establishmentForm.getDescription());
        int categoryId = establishmentForm.getCategory();
        Category category = categoryRepository.findById(categoryId).orElse(null);
        assert category != null;
        establishment.setCategory(category);
        establishment.setStatus(false);
        return establishment;
    }
}
