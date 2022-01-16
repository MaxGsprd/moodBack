package com.mood.mood.service;

import com.mood.mood.dto.out.CommentDetails;
import com.mood.mood.model.Establishment;

import java.util.List;

public interface ICommentService {
    List<CommentDetails> getAllCommentsByEstablishment(Establishment establishment) throws Exception;
}
