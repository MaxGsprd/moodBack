package com.mood.mood.repository;

import com.mood.mood.model.Establishment;
import com.mood.mood.model.Note;
import com.mood.mood.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByValue(Integer value);
    Note findById(int note_id);
    List<Note> findAllByEstablishmentId(int establishmentId);
    Note findByUserIdAndEstablishmentId(int establishmentId, int userId);
    void deleteById(int note_id);
}
