package com.mood.mood.controller;

import com.mood.mood.dto.in.NoteForm;
import com.mood.mood.dto.out.NotesAverage;
import com.mood.mood.model.Establishment;
import com.mood.mood.model.Note;
import com.mood.mood.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class NoteController {

    @Autowired
    INoteService noteService;

    /**
     * create a note linked to the establishment
     */
    @PostMapping("noteEstablishment/establishment/{establishment_id}/user/{user_id}")
    public ResponseEntity<Establishment> noteEstablishment(@Valid @RequestBody NoteForm noteForm,
                                                           @PathVariable("establishment_id") int establishment_id,
                                                           @PathVariable("user_id") int user_id) throws Exception {
        try {
            Establishment notedEstablishment = noteService.noteEstablishment(noteForm, establishment_id, user_id);
            return ResponseEntity.status(HttpStatus.CREATED).body(notedEstablishment);
        } catch (Exception e) {
            throw new Exception("Error: the note couldn't be created " + e.getMessage(), e.getCause());
        }
    }

    /**
     * @param value int
     *              returns all note corresponding to value
     */
    @GetMapping("/noteByValue/{value}")
    public ResponseEntity<List<Note>> getNotesByValue(@PathVariable int value) throws Exception {
        try {
            List<Note> notes = noteService.getNotesByValue(value);
            return ResponseEntity.ok(notes);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    /**
     * @param establishmentId int
     *                        returns all note corresponding to value
     */
    @GetMapping("/noteListByEstablishment/{establishmentId}")
    public ResponseEntity<List<Note>> getNotesByEstablishments(@PathVariable int establishmentId) throws Exception {
        try {
            List<Note> notes = noteService.getNotesByEstablishment(establishmentId);
            return ResponseEntity.ok(notes);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    /**
     * @param establishmentId int
     *                        returns all note corresponding to value
     */
    @GetMapping("/noteAverageByEstablishment/{establishmentId}")
    public ResponseEntity<NotesAverage> getEstablishmentAverage(@PathVariable int establishmentId) throws Exception {
        try {
            NotesAverage average = noteService.getEstablishmentAverage(establishmentId);
            return ResponseEntity.ok(average);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    /**
     * @param establishmentId int, userId int
     *                        returns all note corresponding to value
     */
    @GetMapping("/noteByUserAndEstablishment/establishment/{establishmentId}/user/{userId}")
    public ResponseEntity<Note> getNoteByEstablishmentAndUser(@PathVariable("establishmentId") int establishmentId,
                                                              @PathVariable("userId") int userId) throws Exception {
        try {
            Note note = noteService.getNoteByEstablishmentAndUser(establishmentId, userId);
            return ResponseEntity.ok(note);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @PutMapping("note/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable("id") int id, @RequestBody NoteForm noteForm) throws Exception {
        try {
            Note updatedNote = noteService.updateNote(id, noteForm);
            return ResponseEntity.ok(updatedNote);
        } catch (Exception e) {
            throw new Exception("Error updating note " + e.getMessage(), e.getCause());
        }
    }


    @DeleteMapping("note/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) throws Exception {
        try {
            noteService.deleteNoteById(id);
            HttpHeaders header = new HttpHeaders();
            header.add("Note deleted", "The note has been successfully deleted");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            throw new Exception("Error: this note deletion request couldn't be executed. " + ex.getMessage(), ex.getCause());
        }
    }
}
