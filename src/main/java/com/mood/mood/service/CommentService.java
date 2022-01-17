package com.mood.mood.service;

import com.mood.mood.repository.CommentRepository;
import com.mood.mood.dto.out.CommentDetails;
import com.mood.mood.model.Comment;
import com.mood.mood.model.Establishment;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class CommentService implements ICommentService{

    @Autowired
    private CommentRepository commentRepository;

    public List<CommentDetails> getAllCommentsByEstablishment(Establishment establishment) {
        return commentRepository.findByEstablishment(establishment)
                .stream()
                .map(this::convertCommentEntityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Convert Comment JPA entity to establishmentDetails (DTO out)
     * */
    public CommentDetails convertCommentEntityToDto(Comment comment) {
        CommentDetails commentDetails = new CommentDetails();
        commentDetails.setTitle(comment.getTitle());
        commentDetails.setContent(comment.getContent());
//        commentDetails.setGroupType(comment.getGroupType());
//        commentDetails.setCreatedDate(comment.getCreatedDate());
        return commentDetails;
    }
}
