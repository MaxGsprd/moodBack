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
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class NoteController {
    private static Logger LOGGER = Logger.getLogger(NoteController.class.getName());
    @Autowired
    INoteService noteService;

    /**
     * create a note linked to the establishment
     */
    @PostMapping("noteEstablishment/establishment/{establishment_id}/user/{user_id}")
    public ResponseEntity<Establishment> noteEstablishment(@Valid @RequestBody NoteForm noteForm,
                                                           @PathVariable("establishment_id") int establishment_id,
                                                           @PathVariable("user_id") int user_id) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Create a note linked to the establishment");
        try {
            Establishment notedEstablishment = noteService.noteEstablishment(noteForm, establishment_id, user_id);
            return ResponseEntity.status(HttpStatus.CREATED).body(notedEstablishment);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR** - : the note couldn't be created " + ex.getMessage(), ex);
            return  null;
        }
    }

    /**
     * @param value int
     *              returns all note corresponding to value
     */
    @GetMapping("/noteByValue/{value}")
    public ResponseEntity<?> getNotesByValue(@PathVariable int value) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get all note");
        try {
            List<Note> notes = noteService.getNotesByValue(value);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(notes);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR** - : the note couldn't be found " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Aucun note trouvé " ));
        }
    }

    /**
     * @param establishmentId int
     *                        returns all note corresponding to value
     */
    @GetMapping("/noteListByEstablishment/{establishmentId}")
    public ResponseEntity<?> getNotesByEstablishments(@PathVariable int establishmentId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get all note for an establishment");
        try {
            List<Note> notes = noteService.getNotesByEstablishment(establishmentId);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(notes);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR** - : Notes for this establishment couldn't be found " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Aucun note trouvé par établissement " ));
        }
    }

    /**
     * @param establishmentId int
     *                        returns all note corresponding to value
     */
    @GetMapping("/noteAverageByEstablishment/{establishmentId}")
    public ResponseEntity<?> getEstablishmentAverage(@PathVariable int establishmentId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get average note for an establishment");
        try {
            NotesAverage average = noteService.getEstablishmentAverage(establishmentId);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(average);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR** - : the Average Notes for this establishment couldn't be found " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Aucune moyenne  de note trouvé pour l'établissement " ));
        }
    }

    /**
     * @param establishmentId int, userId int
     *                        returns all note corresponding to value
     */
    @GetMapping("/noteByUserAndEstablishment/establishment/{establishmentId}/user/{userId}")
    public ResponseEntity<?> getNoteByEstablishmentAndUser(@PathVariable("establishmentId") int establishmentId,
                                                              @PathVariable("userId") int userId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get note for an establishment by user");
        try {
            Note note = noteService.getNoteByEstablishmentAndUser(establishmentId, userId);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(note);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR** - : the Notes a user post for this establishment couldn't be found " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - La note posté par l'utilisateur pour cet établissement est introuvable!" ));
        }
    }

    @PutMapping("note/{id}")
    public ResponseEntity<?> updateNote(@PathVariable("id") int id, @RequestBody NoteForm noteForm) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Update Note");
        try {
            Note updatedNote = noteService.updateNote(id, noteForm);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(updatedNote);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR** -  updating note " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("**ERROR ** - Impossible de modifier la note!" ));
        }
    }


    @DeleteMapping("note/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Delete Note");
        try {
            noteService.deleteNoteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(String.format("Note supprimer !"));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR** - : this note deletion request couldn't be executed. " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("**ERROR ** - Impossible de supprimer le note!"));
        }
    }
}
