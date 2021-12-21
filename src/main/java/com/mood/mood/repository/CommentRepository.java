package com.mood.mood.repository;

import com.mood.mood.model.Comment;
import com.mood.mood.model.Establishment;
import com.mood.mood.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, Integer> {
    List<Comment> findByCreatedDate(LocalDateTime date); // filtre from send date to now()
    List<Comment> findByStatus(Boolean status);
    List<Comment> findByUser(User User);
    List<Comment> findByEstablishment(Establishment establishment);
    List<Comment> findByGroupType(Integer type);
}
