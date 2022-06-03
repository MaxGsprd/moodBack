package com.mood.mood.repository;

import com.mood.mood.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByValue(Integer value);
    Note findById(int noteId);
    List<Note> findAllByEstablishmentId(int establishmentId);
    Note findByUserIdAndEstablishmentId(int establishmentId, int userId);
}
