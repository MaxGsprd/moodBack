package com.mood.mood.repository;

import com.mood.mood.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, Integer> {
    List<Comment> findAll();
    List<Comment> findByStatus(Boolean status);
    List<Comment> findByUserId(int userId);
    List<Comment> findByEstablishmentId(int establishmentId);
    List<Comment> findByGroupType(Integer type);
    void deleteById(int id);
}