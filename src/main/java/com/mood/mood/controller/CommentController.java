package com.mood.mood.controller;

import com.mood.mood.dto.in.CommentForm;
import com.mood.mood.dto.out.CommentDetails;
import com.mood.mood.model.Comment;
import com.mood.mood.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class CommentController {
    private static Logger LOGGER = Logger.getLogger(CommentController.class.getName());
    @Autowired
    ICommentService commentService;

    @GetMapping("/comments")
    public ResponseEntity<?> getAllComments() throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get all comments");
        try {
            List<CommentDetails> commentDetails = commentService.getAllComments();
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(commentDetails);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Aucun commentaire trouvé " ));
        }
    }

    @GetMapping("/commentsByEstablishment/{establishmentId}")
    public ResponseEntity<?> getCommentByEstablishment(@PathVariable int establishmentId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get all comments by establishment :ID");
        try {
            List<CommentDetails> comments = commentService.getAllCommentsByEstablishmentId(establishmentId);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(comments);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Aucun commentaire trouvé " ));
        }

    }

    @GetMapping("/commentsByStatus/{status}")
    public ResponseEntity<?> getCommentByStatus(@PathVariable Boolean status) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get all comments by :STATUS");
        try {
            List<CommentDetails> comments = commentService.getAllCommentsByStatus(status);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(comments);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Aucun commentaire par statue trouvé "));
        }
    }

    @GetMapping("/commentsByUser/{userId}")
    public ResponseEntity<?> getCommentsByUserId(@PathVariable int userId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get all comments by :UserID");
        try {
            List<CommentDetails> comments = commentService.getAllCommentsByUserId(userId);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(comments);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Commentaire recherché par utilisateur intouvable! "));
        }

    }

    @GetMapping("/commentsByGroupType/{groupType}")
    public ResponseEntity<?> getCommentsByGroupType(@PathVariable int groupType) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get all comments by :groupType");
        try {
            List<CommentDetails> comments = commentService.getAllCommentsByGroupType(groupType);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(comments);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Commentaire recherché par type grouper intouvable! "));
        }
    }

    @GetMapping("/allCommentsSortedByLatestCreatedDate")
    public ResponseEntity<?> getCommentByLatestCreatedDate() throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get all comments DESC order by :CreatedDate");
        try {
            List<CommentDetails> comments = commentService.getAllCommentsByCreatedDateLatest();
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(comments);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Commentaire par ordre décroissante introuvable! "));
        }
    }

    @GetMapping("/allCommentsSortedByOldestCreatedDate")
    public ResponseEntity<?> getCommentByOldestCreatedDate() throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get all comments ASC order by :CreatedDate");
        try {
            List<CommentDetails> comments = commentService.getAllCommentsByCreatedDateOldest();
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(comments);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Commentaire par ordre croissante introuvable! "));
        }
    }

    @PostMapping("/newComment/establishment/{establishment_id}/user/{user_id}")
    public ResponseEntity<?> createEstablishment(@Valid @RequestBody CommentForm commentForm,
                                                       @PathVariable("establishment_id") int establishment_id,
                                                       @PathVariable("user_id") int user_id) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Post new comment :establishment_ID, : user_ID");
        try {
            Comment createdComment = commentService.createComment(commentForm, establishment_id, user_id);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error: the establishment couldn't be created " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("**ERROR ** - Impossible d'ajouté ce commentaire!"));
        }
    }

    @DeleteMapping("comment/{id}")
    @Secured({"ROLE_USER", "ROLE_EDITOR"})
    public  ResponseEntity<?> deleteComment(@PathVariable int id) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Delete comment :ID");
        try {
            commentService.deleteCommentById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(String.format("Commentaire supprimer !"));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error: The comment deletion request couldn't be executed. " + ex.getMessage(), ex.getCause());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("**ERROR ** - Impossible de supprimer ce commentaire!"));
        }
    }

    @PutMapping("comment/{id}")
    @Secured({"ROLE_USER", "ROLE_EDITOR"})
    public ResponseEntity<?> updateComment(@PathVariable("id") int id, @RequestBody CommentForm commentForm) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Update comment :ID, :commentForm");
        try {
            CommentDetails updatedComment = commentService.updateComment(id, commentForm);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(updatedComment);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating establishment " + e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("**ERROR ** - Impossible de modifier ce commentaire!"));
        }
    }
}
