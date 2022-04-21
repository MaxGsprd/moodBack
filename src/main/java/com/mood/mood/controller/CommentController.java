package com.mood.mood.controller;

import com.mood.mood.dto.in.CommentForm;
import com.mood.mood.dto.out.CommentDetails;
import com.mood.mood.model.Comment;
import com.mood.mood.model.Image;
import com.mood.mood.service.ICommentService;
import org.aspectj.bridge.ICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class CommentController {
    private static Logger LOGGER = Logger.getLogger(CommentController.class.getName());
    @Autowired
    ICommentService commentService;

    @GetMapping("/comments")
    public ResponseEntity<List<CommentDetails>> getAllComments() throws Exception {
        try {
            List<CommentDetails> commentDetails = commentService.getAllComments();
            return ResponseEntity.ok(commentDetails);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    @GetMapping("/commentsByEstablishment/{establishmentId}")
    public ResponseEntity<List<CommentDetails>> getCommentByEstablishment(@PathVariable int establishmentId) throws Exception {
        try {
            List<CommentDetails> comments = commentService.getAllCommentsByEstablishmentId(establishmentId);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return  null;
        }
    }

    @GetMapping("/commentsByStatus/{status}")
    public ResponseEntity<List<CommentDetails>> getCommentByStatus(@PathVariable Boolean status) throws Exception {
        try {
            List<CommentDetails> comments = commentService.getAllCommentsByStatus(status);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return  null;
        }
    }

    @GetMapping("/commentsByUser/{userId}")
    public ResponseEntity<List<CommentDetails>> getCommentsByUserId(@PathVariable int userId) throws Exception {
        try {
            List<CommentDetails> comments = commentService.getAllCommentsByUserId(userId);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return  null;
        }
    }

    @GetMapping("/commentsByGroupType/{groupType}")
    public ResponseEntity<List<CommentDetails>> getCommentsByGroupType(@PathVariable int groupType) throws Exception {
        try {
            List<CommentDetails> comments = commentService.getAllCommentsByGroupType(groupType);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return  null;
        }
    }

    @GetMapping("/allCommentsSortedByLatestCreatedDate")
    public ResponseEntity<List<CommentDetails>> getCommentByLatestCreatedDate() throws Exception {
        try {
            List<CommentDetails> comments = commentService.getAllCommentsByCreatedDateLatest();
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return  null;
        }
    }

    @GetMapping("/allCommentsSortedByOldestCreatedDate")
    public ResponseEntity<List<CommentDetails>> getCommentByOldestCreatedDate() throws Exception {
        try {
            List<CommentDetails> comments = commentService.getAllCommentsByCreatedDateOldest();
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return  null;
        }
    }

    @PostMapping("/newComment/establishment/{establishment_id}/user/{user_id}")
    public ResponseEntity<Comment> createEstablishment(@Valid @RequestBody CommentForm commentForm,
                                                       @PathVariable("establishment_id") int establishment_id,
                                                       @PathVariable("user_id") int user_id) throws Exception {
        try {
            Comment createdComment = commentService.createComment(commentForm, establishment_id, user_id);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error: the establishment couldn't be created " + e.getMessage(), e);
            return  null;
        }
    }

    @DeleteMapping("comment/{id}")
    public  ResponseEntity<Void> deleteComment(@PathVariable int id) throws Exception {
        try {
            commentService.deleteCommentById(id);
            return ResponseEntity.ok(null);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error: The comment deletion request couldn't be executed. " + ex.getMessage(), ex.getCause());
            return null;
        }
    }

    @PutMapping("comment/{id}")
    public ResponseEntity<CommentDetails> updateComment(@PathVariable("id") int id, @RequestBody CommentForm commentForm) throws Exception {
        try {
            CommentDetails updatedComment = commentService.updateComment(id, commentForm);
            return ResponseEntity.ok(updatedComment);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating establishment " + e.getMessage(), e.getCause());
            return null;
        }
    }
}
