package com.mood.mood.service;

import com.mood.mood.dto.in.CommentForm;
import com.mood.mood.dto.in.EstablishmentForm;
import com.mood.mood.dto.out.CommentDetails;
import com.mood.mood.dto.out.EstablishmentDetails;
import com.mood.mood.model.Comment;
import com.mood.mood.model.Establishment;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ICommentService {
    List<CommentDetails> getAllComments() throws Exception;
    List<CommentDetails> getAllCommentsByEstablishmentId(int establishmentId) throws Exception;
    List<CommentDetails> getAllCommentsByStatus(Boolean status) throws Exception;
    List<CommentDetails> getAllCommentsByUserId(int userId) throws Exception;
    List<CommentDetails> getAllCommentsByGroupType(int groupType) throws Exception;
    List<CommentDetails> getAllCommentsByCreatedDateLatest() throws Exception;
    List<CommentDetails> getAllCommentsByCreatedDateOldest() throws Exception;
    Comment createComment(@ModelAttribute CommentForm commentForm, int establishment_id, int user_id) throws Exception;
    void deleteCommentById(int id) throws Exception;
    CommentDetails updateComment(final int id, CommentForm commentForm) throws Exception;
}
