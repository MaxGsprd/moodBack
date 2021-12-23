package com.mood.mood.repository;

import com.mood.mood.model.Establishment;
import com.mood.mood.model.Note;
import com.mood.mood.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    Note findByValue(Integer value);
    Note findByEstablishment(Establishment establishment);
    Note findByUserAndEstablishment(User user, Establishment establishment);
}
