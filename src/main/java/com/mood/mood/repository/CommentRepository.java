package com.mood.mood.repository;

import com.mood.mood.model.Comment;
import com.mood.mood.model.Establishment;
import com.mood.mood.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, Integer> {
    List<Comment> findAll();
    List<Comment> findByStatus(Boolean status);
    List<Comment> findByUser(User user);
    List<Comment> findByEstablishment(Establishment establishment);
    List<Comment> findByGroupType(Integer type);
}