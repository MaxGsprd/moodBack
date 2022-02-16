package com.mood.mood.service;

import com.mood.mood.dto.in.NoteForm;
import com.mood.mood.dto.out.NotesAverage;
import com.mood.mood.model.Establishment;
import com.mood.mood.model.Note;
import com.mood.mood.model.User;
import com.mood.mood.repository.EstablishmentRepository;
import com.mood.mood.repository.NoteRepository;
import com.mood.mood.repository.UserRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class NoteService implements INoteService {

    @Autowired
    private final NoteRepository noteRepository;
    @Autowired
    private final EstablishmentRepository establishmentRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ModelMapper modelMapper;

    /**
     * give and create Note linked to an establishment
     * */
    public Establishment noteEstablishment(@ModelAttribute NoteForm noteForm, int establishment_id,  int user_id) throws Exception {
        try {
            Note note = this.convertNoteDtoToEntity(noteForm, establishment_id, user_id);
            Establishment establishment = establishmentRepository.findById(establishment_id);
            noteRepository.save(note);
            return establishment;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public List<Note> getNotesByValue(int value) throws Exception {
        try {
            List<Note> notes = noteRepository.findByValue(value);
            return notes;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }


    public List<Note> getNotesByEstablishment(int establishment_id) throws Exception {
        try {
            List<Note> notes = noteRepository.findAllByEstablishmentId(establishment_id);
            return notes;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public Note getNoteByEstablishmentAndUser(int establishment_id, int user_id) throws Exception {
        try {
            Note note = noteRepository.findByUserIdAndEstablishmentId(establishment_id, user_id);
            return note;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public NotesAverage getEstablishmentAverage(int establishment_id) throws Exception {
        try {
            List<Note> notes = noteRepository.findAllByEstablishmentId(establishment_id);
            NotesAverage average = this.notesAverage(notes);
            return average;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    /**
     * @param noteList list of notes of the establishment
     * compute and return note average of establishment
     * */
    public NotesAverage notesAverage(List<Note> noteList) {
        NotesAverage notesAverage = new NotesAverage();
        float sum = 0;
         for (Note note : noteList) {
             sum += note.getValue();
         }
         float average = sum / noteList.size();
         notesAverage.setNote(average);
         return notesAverage;
    }


    /**
     * Convert NoteFrom (Dto in) to Note entity
     */
    private Note convertNoteDtoToEntity(NoteForm noteForm, int establishment_id,  int user_id) {
        Note note = new Note();
        note.setValue(noteForm.getValue());
        Optional<User> user = userRepository.findById(user_id);
        note.setUser(user.get());
        Establishment establishment = establishmentRepository.findById(establishment_id);
        note.setEstablishment(establishment);
        return note;
    }

    /**
     * @param id establishment id to be deleted
     */
    public void deleteNoteById(int id) throws Exception {
        try {
            Note note = noteRepository.findById(id);
            noteRepository.deleteNoteById(note.getId());
        } catch (Exception e) {
            throw new Exception("Error : This note couldn't be found, " + e.getMessage(), e.getCause());
        }
    }
}
