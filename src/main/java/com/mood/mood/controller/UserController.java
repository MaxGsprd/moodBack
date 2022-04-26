package com.mood.mood.controller;

import com.mood.mood.dto.in.UserForm;
import com.mood.mood.dto.out.UserDetails;
import com.mood.mood.model.User;
import com.mood.mood.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger LOGGER = Logger.getLogger(UserController.class.getName());
    @Autowired
    IUserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserDetails(@PathVariable Integer id) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get User detail by ID");
        try {
            UserDetails user = userService.find(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(user);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR** -- Couldn't get user detail " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Aucun utilisateur trouvé " ));
        }
    }

    @PutMapping("/{id}/updateMood/{mood}")
    public ResponseEntity<?> updateMood(@PathVariable Integer id, @PathVariable Integer mood) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Update User mood");
        try {
            User user = userService.updateMood(id, mood);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(user);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR** -- Couldn't update user mood " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("**ERROR ** - Impossible de modifier le mood de l'utilisateur!" ));
        }
    }

    @PutMapping("/{id}/updateRole/{role}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> updateRole(@PathVariable Integer id, @PathVariable Integer role) throws Exception {
        LOGGER.log(Level.INFO, "**START** - ADMINISTRATOR Update User ROLE");
        try {
            User user = userService.updateRole(id, role);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(user);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR** -- ADMINISTRATOR Couldn't update user ROLE " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("**ERROR ** - Impossible de modifier le Role de l'utilisateur!" ));
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserForm form) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Update User detail");
        try {
            User user = userService.update(id, form);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(user);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR** -- Couldn't update user detail " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("**ERROR ** - Impossible de modifier les informations de l'utilisateur!" ));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Integer id) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Delete User by id");
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(String.format("Utilisatuer supprimer !"));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR** -- Couldn't delete user " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("**ERROR ** - Impossible de supprimer l'utilisateur!"));
        }
    }
}
