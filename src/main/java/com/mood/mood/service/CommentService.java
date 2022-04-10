package com.mood.mood.service;

import com.mood.mood.dto.in.CommentForm;
import com.mood.mood.model.User;
import com.mood.mood.repository.CommentRepository;
import com.mood.mood.dto.out.CommentDetails;
import com.mood.mood.model.Comment;
import com.mood.mood.model.Establishment;
import com.mood.mood.repository.EstablishmentRepository;
import com.mood.mood.repository.UserRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Service
public class CommentService implements ICommentService{

    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final EstablishmentRepository establishmentRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ModelMapper modelMapper;


    public List<CommentDetails> getAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(this::convertCommentEntityToDto)
                .collect(Collectors.toList());
    }

    public List<CommentDetails> getAllCommentsByCreatedDateLatest() {
        return commentRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"))
                .stream()
                .map(this::convertCommentEntityToDto)
                .collect(Collectors.toList());
    }

    public List<CommentDetails> getAllCommentsByCreatedDateOldest() {
        return commentRepository.findAll(Sort.by(Sort.Direction.ASC, "createdDate"))
                .stream()
                .map(this::convertCommentEntityToDto)
                .collect(Collectors.toList());
    }

    public List<CommentDetails> getAllCommentsByEstablishmentId(int establishmentId) {
        return commentRepository.findByEstablishmentId(establishmentId)
                .stream()
                .map(this::convertCommentEntityToDto)
                .collect(Collectors.toList());
    }

    public List<CommentDetails> getAllCommentsByStatus(Boolean status) {
        return commentRepository.findByStatus(status)
                .stream()
                .map(this::convertCommentEntityToDto)
                .collect(Collectors.toList());
    }

    public List<CommentDetails> getAllCommentsByUserId(int userId) {
        return commentRepository.findByUserId(userId)
                .stream()
                .map(this::convertCommentEntityToDto)
                .collect(Collectors.toList());
    }

    public List<CommentDetails> getAllCommentsByGroupType(int groupType) {
        return commentRepository.findByGroupType(groupType)
                .stream()
                .map(this::convertCommentEntityToDto)
                .collect(Collectors.toList());
    }

    /**
     * @param commentForm comment DTO in
     * @return true if success, false otherwise
     */
    public Comment createComment(CommentForm commentForm, int establishment_id, int user_id) throws Exception {
        try {
            Comment comment = commentDtoToEntity(commentForm);
            Establishment establishment = establishmentRepository.findById(establishment_id);
            comment.setEstablishment(establishment);
            Optional<User> user = userRepository.findById(user_id);
            comment.setUser(user.get());
            commentRepository.save(comment);
            return comment;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    /**
     * @param id comment id to be deleted
     */
    public void deleteCommentById(int id) throws Exception {
        try {
            Optional<Comment> comment = commentRepository.findById(id);
            assert comment.isPresent();
            commentRepository.deleteById(comment.get().getId());
        } catch (Exception e) {
            throw new Exception("Error : This establishment couldn't be found, " + e.getMessage(), e.getCause());
        }
    }

    /**
     * @param id comment id to be updated
     */
    public CommentDetails updateComment(int id, CommentForm commentForm) throws Exception {
        try {
            Optional<Comment> comment = commentRepository.findById(id);
            assert comment.isPresent();
            comment.get().setTitle(commentForm.getTitle());
            comment.get().setContent(commentForm.getContent());
            commentRepository.save(comment.get());
            return convertCommentEntityToDto(comment.get());
        } catch (Exception e) {
            throw new Exception("Error : This establishment couldn't be found, " + e.getMessage(), e.getCause());
        }
    }

    /**
     * Convert Comment JPA entity to establishmentDetails (DTO out)
     * */
    public CommentDetails convertCommentEntityToDto(Comment comment) {
        return modelMapper.map(comment, CommentDetails.class);
    }

    /**
     * Convert CommentForm (Dto in) to Comment entity
     */
    private Comment commentDtoToEntity(CommentForm commentForm) {
        Comment comment = new Comment();
        comment.setTitle(commentForm.getTitle());
        comment.setContent(commentForm.getContent());
        comment.setGroupType(commentForm.getGroupType());
        comment.preSave();
        comment.setStatus(false);
        return comment;
    }
}
