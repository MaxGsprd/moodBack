package com.mood.mood.service;

import com.mood.mood.dto.in.NoteForm;
import com.mood.mood.dto.out.NotesAverage;
import com.mood.mood.model.Establishment;
import com.mood.mood.model.Note;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface INoteService {
    Establishment noteEstablishment(@ModelAttribute NoteForm noteForm, int establishment_id, int user_id) throws Exception;
    List<Note> getNotesByValue(int value) throws Exception;
    List<Note> getNotesByEstablishment(int establishment_id) throws Exception;
    NotesAverage getEstablishmentAverage(int establishment_id) throws Exception;
    Note getNoteByEstablishmentAndUser(int establishment_id, int user_id) throws Exception;
    void deleteNoteById(int id) throws Exception;
    Note updateNote(final int id, NoteForm noteForm) throws Exception;
}
